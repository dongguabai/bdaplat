package com.zj.bda.web.util.excel.export;


import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.Pattern;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author 林朝聪
 * @Description copy from maf package com.mi.maf.core.util.GenReportFileUtils
 * @Date 创建于 18-12-21 下午3:52
 */
public class ExcelUtil {

    private ExcelUtil() {}

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // excel样式配置
    private static final int DEFAULT_BYTE_ARRAY_SIZE = 1024 * 1024;
    private static final int DEFAULT_SHELL_INDEX = 0;
    private static final int DEFAULT_TITLE_FONT_SIZE = 17;
    private static final int DEFAULT_CONTENT_FONT_SIZE = 11;
    private static final boolean  DEFAULT_FONT_IS_ITALIC = false;
    private static final int DEFAULT_START_COL = 0;
    private static final int DEFAULT_START_ROW = 0;
    private static final int DEFAULT_START_ROW_HEIGHT = 800;


    /**
     * excel封装类的比较器，用于给excel上的每个字段进行排序
     */
    private static final Comparator<ExcelAnnotation> EXCEL_ANNOTATION_COMPARATOR = (o1, o2) -> {
        int result = o1.id() - o2.id();
        if (result >= 0) {
            return 1;
        }
        return -1;
    };

    /**
     * 生成EXCEL文件
     *
     * @param title             标题
     * @param data              数据集
     * @param type              数据类型
     * @param headMap           元数据
     * @param excludeFieldNames 不包含字段列表
     * @param readOnly          是否只读
     * @return
     * @throws IOException
     */
    public static ByteArrayOutputStream genExcelStream(String title, List<?> data, Class type
            , LinkedHashMap<String, Pair<String, Integer>> headMap, Set<String> excludeFieldNames, boolean readOnly) throws IOException, WriteException, IllegalAccessException {
        TreeMap<ExcelAnnotation, Field> excelAnnotationFieldTreeMap = genExcelAnnotationMap(type, headMap, excludeFieldNames);
        ByteArrayOutputStream outputStream;
        WritableWorkbook workbook = null;
        WritableCellFormat wcfCell;
        try {
            outputStream = new ByteArrayOutputStream(DEFAULT_BYTE_ARRAY_SIZE);
            workbook = Workbook.createWorkbook(outputStream);
            WritableSheet sheet = workbook.createSheet(title, DEFAULT_SHELL_INDEX);
            // 用于标题
            WritableFont titleFont = new WritableFont(WritableFont.ARIAL, DEFAULT_TITLE_FONT_SIZE, WritableFont.BOLD, DEFAULT_FONT_IS_ITALIC, UnderlineStyle.NO_UNDERLINE,
                    jxl.format.Colour.WHITE);
            WritableCellFormat wcfTitle = new WritableCellFormat(titleFont);
            wcfTitle.setBackground(Colour.TEAL, Pattern.SOLID);
            wcfTitle.setBorder(Border.ALL, BorderLineStyle.DOUBLE, Colour.OCEAN_BLUE);
            // 垂直对齐
            wcfTitle.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcfTitle.setAlignment(Alignment.CENTRE);
            // 用于正文
            WritableFont normalFont = new WritableFont(WritableFont.TAHOMA, DEFAULT_CONTENT_FONT_SIZE);
            wcfCell = new WritableCellFormat(normalFont);
            wcfCell.setBorder(Border.ALL, BorderLineStyle.NONE, Colour.GRAY_25);
            // 垂直对齐
            wcfCell.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcfCell.setAlignment(Alignment.CENTRE);
            // 是否换行
            wcfCell.setWrap(true);
            // 标题行
            sheet.addCell(new Label(DEFAULT_START_COL, DEFAULT_START_ROW, title, wcfTitle));
            sheet.mergeCells(DEFAULT_START_COL, DEFAULT_START_ROW,  excelAnnotationFieldTreeMap.size() - 1, 0);
            sheet.setRowView(DEFAULT_START_ROW, DEFAULT_START_ROW_HEIGHT);
            // 内容头
            int i = 0;
            for (ExcelAnnotation excelAnnotation : excelAnnotationFieldTreeMap.keySet()) {
                sheet.setColumnView(i, excelAnnotation.width());
                sheet.addCell(new Label(i, 1, excelAnnotation.name(), wcfCell));
                i++;
            }
            int rowIndex = 2;
            int columnIndex;
            for (Object obj : data) {
                columnIndex = 0;
                for (Map.Entry<ExcelAnnotation, Field> entry : excelAnnotationFieldTreeMap.entrySet()) {
                    sheet.addCell(new Label(columnIndex, rowIndex, getFieldValue(entry.getValue(), obj), wcfCell));
                    columnIndex++;
                }
                rowIndex++;
            }
            //sheet 加密
            if (readOnly) {
                SheetSettings sheetSettings = sheet.getSettings();
                sheetSettings.setPassword(UUID.randomUUID().toString());
                sheetSettings.setProtected(true);
            }
            workbook.write();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (WriteException ignored) { }
            }
        }
        return outputStream;
    }

    private static String getFieldValue(Field field, Object object) throws IllegalAccessException {
        Object value = field.get(object);
        if (value == null) {
            return StringUtils.EMPTY;
        }
        if (value instanceof java.sql.Date) {
            return ((java.sql.Date) value).toLocalDate().format(DATE_FORMATTER);
        } else if (value instanceof java.sql.Timestamp) {
            return ((java.sql.Timestamp) value).toLocalDateTime().format(DATE_TIME_FORMATTER);
        } else if (value instanceof Date) {
            return LocalDateTime.ofInstant(((Date) value).toInstant(), ZoneId.systemDefault()).format(DATE_TIME_FORMATTER);
        } else if (value instanceof LocalDate) {
            return ((LocalDate) value).format(DATE_FORMATTER);
        } else if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).format(DATE_TIME_FORMATTER);
        }
        return value.toString();
    }

    private static TreeMap<ExcelAnnotation, Field> genExcelAnnotationMap(Class type, LinkedHashMap<String, Pair<String, Integer>> headMap, Set<String> excludeFieldNames) {
        TreeMap<ExcelAnnotation, Field> excelAnnotationFieldTreeMap = new TreeMap<>(EXCEL_ANNOTATION_COMPARATOR);
        boolean headMapIsEmpty = headMap == null || headMap.isEmpty();
        int i = 0;
        Map<String, Integer> indexMap = null;
        if (!headMapIsEmpty) {
            indexMap = new HashMap<>(headMap.size());
            for (String s : headMap.keySet()) {
                indexMap.put(s, i);
                i++;
            }
        }
        ExcelAnnotation excelAnnotation;
        String fieldName;
        Field[] fields = type.getDeclaredFields();
        i = 0;
        for (Field field : fields) {
            fieldName = field.getName();
            if (excludeFieldNames != null && excludeFieldNames.contains(fieldName)) {
                continue;
            }
            if (headMapIsEmpty) {
                excelAnnotation = field.getAnnotation(ExcelAnnotation.class);
                if (excelAnnotation == null) {
                    continue;
                }
            } else {
                final Pair<String, Integer> pair = headMap.get(fieldName);
                if (pair == null) {
                    continue;
                }
                final int id = indexMap.get(fieldName);
                excelAnnotation = createExcelAnnotation(id, pair.getLeft(), pair.getRight());
                i++;
            }
            field.setAccessible(true);
            excelAnnotationFieldTreeMap.put(excelAnnotation, field);
        }
        return excelAnnotationFieldTreeMap;
    }

    private static ExcelAnnotation createExcelAnnotation(int id, String name, int width) {
        return new ExcelAnnotation() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return ExcelAnnotation.class;
            }
            @Override
            public int id() {
                return id;
            }
            @Override
            public String name() {
                return name;
            }
            @Override
            public int width() {
                return width;
            }
            @Override
            public String merge() {
                return null;
            }
        };
    }
}
