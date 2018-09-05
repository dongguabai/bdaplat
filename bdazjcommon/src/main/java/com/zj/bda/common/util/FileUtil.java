package com.zj.bda.common.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.util.ByteArrayBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DecimalFormat;

public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
    private static final String TAG = "FileUtil";

    /**
     * 创建文件，若文件夹不存在则自动创建文件夹，若文件存在则删除旧文件
     *
     * @param path :待创建文件路径
     */
    public static File createNewFile(String path) {
        File file = new File(path);
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            logger.error(TAG, e.getMessage());
        }
        return file;
    }

    /**
     * 将文件输入流写入文件
     */
    public static boolean writeFileFromInputStream(InputStream inStream, String path) {
        boolean result = true;
        try {
            File file = createNewFile(path);
            FileOutputStream out = new FileOutputStream(file);
            byte[] data = new byte[1024];
            int num = 0;
            while ((num = inStream.read(data, 0, data.length)) != -1) {
                out.write(data, 0, num);
            }
            out.close();
            data = null;
        } catch (Exception e) {
            result = false;
            logger.error(TAG, e.getMessage());
        }
        return result;
    }

    /**
     * 获取文件输入流
     */
    public static InputStream readFileToInputStream(String path) {
        InputStream inputStream = null;
        try {
            File file = new File(path);
            inputStream = new FileInputStream(file);
        } catch (IOException e) {
            logger.error(TAG, e.getMessage());
        }
        return inputStream;
    }

    /**
     * 读取文件字节数组
     */
    public static byte[] readFileToBytes(String path) {
        InputStream inputStream = readFileToInputStream(path);
        if (inputStream != null) {
            byte[] data = new byte[1024];
            ByteArrayBuffer buffer = new ByteArrayBuffer(1024);
            int n = 0;
            try {
                while ((n = inputStream.read(data)) != -1) {
                    buffer.append(data, 0, n);
                }
                data = null;
                inputStream.close();
            } catch (IOException e) {
                logger.error(TAG, e.getMessage());
            }
            return buffer.toByteArray();
        }
        return null;
    }

    /**
     * 读取文件内容
     */
    public static String readFileToString(String path) {
        byte[] dataBytes = readFileToBytes(path);
        if (dataBytes != null) {
            return new String(dataBytes);
        }
        return null;
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String path) {
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String tempString = null;
            while ((tempString = bufferedReader.readLine()) != null) {
                buffer.append(tempString).append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            logger.error(TAG, e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    logger.error(TAG, e.getMessage());
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e) {
                    logger.error(TAG, e.getMessage());
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 根据给出路径自动选择复制文件或整个文件夹
     *
     * @param src  :源文件或文件夹路径
     * @param dest :目标文件或文件夹路径
     */
    public static void copyFiles(String src, String dest) {
        File srcFile = new File(src);
        if (srcFile.exists()) {
            if (srcFile.isFile()) {
                writeFileFromInputStream(readFileToInputStream(src), dest);
            } else {
                File[] subFiles = srcFile.listFiles();
                if (subFiles.length == 0) {
                    File subDir = new File(dest);
                    subDir.mkdirs();
                } else {
                    for (File subFile : subFiles) {
                        String subDirPath = dest + System.getProperty("file.separator") + subFile.getName();
                        copyFiles(subFile.getAbsolutePath(), subDirPath);
                    }
                }
            }
        }
    }

    /**
     * 根据给出路径自动选择删除文件或整个文件夹
     *
     * @param path :文件或文件夹路径
     */
    public static void deleteFiles(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();// 删除文件
        } else {
            File[] subFiles = file.listFiles();
            for (File subfile : subFiles) {
                deleteFiles(subfile.getAbsolutePath());// 删除当前目录下的子目录
            }
            file.delete();// 删除当前目录
        }
    }


    /**
     * 转换文件大小
     */
    public static String FormetFileSize(long size) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String result = "";
        if (size < 1024) {
            result = df.format((double) size) + "B";
        } else if (size < 1048576) {
            result = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            result = df.format((double) size / 1048576) + "M";
        } else {
            result = df.format((double) size / 1073741824) + "G";
        }
        return result;
    }

    /**
     * InputStream转换为String
     *
     * @param inputStream
     * @return
     */
    public static String readInputStream(InputStream inputStream) {
        String theString = null;
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(inputStream, writer, "UTF-8");
            theString = writer.toString();
            return theString;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(writer);
        }
        return theString;
    }

    /**
     * 复制InputStream到File，File不存在就会创建，File要是文件
     *
     * @param source
     * @param destination
     * @throws IOException
     */
    public static void copyInputStreamToFile(InputStream source, File destination) throws IOException {
        FileUtils.copyInputStreamToFile(source, destination);
    }

    /**
     * 读取File到FileInputStream
     * @param file
     * @return
     * @throws IOException
     */
    public static FileInputStream openInputStream(File file) throws IOException {
        return FileUtils.openInputStream(file);
    }

    /**
     * 文件操作工具类（FileUtils）
     使用该工具类的前提是项目里导入commons-io 包
     import org.apache.commons.io.FileUtils;

     List<String> lines=new ArrayList<String>();

     lines.add("欢迎访问:");

     lines.add("www.cxyapi.com");



     *如果没有文件会自动创建，并且将集合元素以行来划分，多次写入会覆盖原有内容。

     FileUtils.writeLines(new File("D:/a/b/cxyapi.txt"),lines);



     *以下方法无效，不知道什么原因，带boolean的这种都无效

     FileUtils.writeLines(new File("D:/a/b/cxyapi.txt"),lines，true);



     *写入一行数据，如果已经有值会替换。

     FileUtils.writeStringToFile(new File("D:/a/b/cxyapi.txt"), "作者：cxy", "UTF-8");





     *读取文件数据，以字符串返回，每行后加一个\r\n在控制台显示，所以可以以下边的办法转为数组。

     String aString = FileUtils.readFileToString(new File("D:/a/b/cxyapi.txt"), "UTF-8");

     String[] a = aString.split("\\r\\n"); //将文件内容按行为单位转换为字符串数组



     * 读取文件数据，以字符串集合返回

     List<String> list = FileUtils.readLines(new File("D:/a/b/cxyapi.txt"), "UTF-8");



     * 该方法会将b文件夹删掉，不管b下是否有内容,如果b不存在也不报错

     FileUtils.deleteDirectory(new File("D:/a/b"));



     * 将a文件夹下内容拷贝到ee文件夹下，如果a不存在则报异常，ee不存在则会新建

     FileUtils.moveDirectory(new File("D:/b/c"), new File("D:/b/mm/ff"));



     * 将cc文件夹拷贝到3文件夹下，与上边不同的是，cc文件夹会存在,也是前者没有会报错，后者没有会新建

     FileUtils.moveDirectoryToDirectory(new File("D:/b/cc"), new File("D:/b/3"), true);



     * 此方法将mm下（不包括mm）所有的文件夹及文件复制到3下，如果有同名文件夹则合并，如果有同名文件则替换

     FileUtils.copyDirectory(new File("D:/b/mm"), new File("D:/b/3"));



     * 此方法将mm下（包括mm）所有的文件夹及文件复制到3下，如果有同名文件夹则合并，如果有同名文件则替换

     FileUtils.copyDirectoryToDirectory(new File("D:/b/mm"), new File("D:/b/3"));





     * （常用）此方法将b文件夹下a.doc拷贝到mm下a.doc,前者不存在会报错，后者不存在会新建，如果后者存在同名文件则替换

     * 另外如果后者为a.xml等不同后缀的文件，程序仍不报错，但文件内容乱码

     FileUtils.copyFile(new File("d:/b/a.doc"), new File("d:/b/mm/a.doc"));

     FileUtils.copyFile(new File("d:/b/a.doc"), new File("d:/b/mm","a.doc"));//与上边功能一样



     * 此方法将b文件夹下的a.doc拷贝到b/3mm文件夹下，如果有同名文件则替换，前者没有报错，后者没有新建

     FileUtils.copyFileToDirectory(new File("d:/b/a.doc"), new File("d:/b/3mm"));



     * 此方法可以将任何网址的后台页面内容拷贝到a.xml文件内，如果后者存在则替换，不存在则新建，前者不存在则报错,网页访问出错（404等）则不报错

     FileUtils.copyURLToFile(new URL("http://www.baidu.com"), new File("d:/b/a.xml"));



     * 判断两个文件内容是否相同，如果两者都不存在仍认定为相同，如果类型不同（文件与文件夹相比则报错），此方法只能比较文件，

     * 不能用于文件夹比较,但是该方法感觉不稳定，总是瞎报错，明明内容一样，非说false。

     boolean b=FileUtils.contentEquals(new File("D:/b/a.doc"), new File("D:/b/mm/c.doc"));



     * 清理3文件夹下所有内容(有了它，再也不需要写递归方法了吧？)

     FileUtils.cleanDirectory(new File("d:/b/3"));



     * 可删除文件，也可以删除文件夹，删除3文件夹，包括子文件及文件夹(有了它，再也不需要写递归方法了吧？)

     FileUtils.forceDelete(new File("d:/b/a.doc"));





     * 删除3文件夹，包括子文件及文件夹(有了它，再也不需要写递归方法了吧？)可删除文件夹也可以删除文件

     FileUtils.deleteDirectory(new File("d:/b/3"));



     * 该文件是否在该时间之前创建(isFileOlder)

     * 该文件是否在该时间之后创建(isFileNewer)

     String string="2016-12-20 10:00:00.023";

     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss：SSS");  //最后三个大写SSS代表毫秒，写1个表示前边都不补0，写两个只有是两位0补全

     b= FileUtils.isFileOlder(new File("D:/b/a.doc"),sdf.parse(string));

     b= FileUtils.isFileNewer(new File("D:/b/a.doc"),sdf.parse(string))



     * 只能针对于目录文件夹，不能查看文件大小(查看文件是file.length() 例如：new File("d:/b/a.xml").length())

     long a = FileUtils.sizeOfDirectory(new File("d:/b")); //单位为字节B


     */
}

