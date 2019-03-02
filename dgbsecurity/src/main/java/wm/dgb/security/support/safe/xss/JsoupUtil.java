package wm.dgb.security.support.safe.xss;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author Dongguabai
 * @date 2018-07-22 13:46
 */
@Slf4j
public class JsoupUtil {
    /**
     *
     一个白名单,里面配置允许出现的html标签,属性,特殊标签的特殊属性,强制添加的属性等等
     */
    private static final Whitelist XSS_LIST = Whitelist.none();

    /**
     *
     一个白名单,准确的说是一个空的白名单,因为里面不允许出现任何html代码
     */
    private static final Whitelist WHITELIST = Whitelist.none();

    /**
     * 过滤所有的HTML元素,一般用于过滤标题的xss代码,标题中不会允许有html代码的存在
     * @param content
     * @return
     */
    public static String cleanHTML(String content) {
        return WHITELIST == null ? null : Jsoup.clean(content, WHITELIST);
    }

    /**
     * 过滤XSS字符,
     * @param content
     * @return
     */
    public static String cleanXss(String content) {
        return content == null ? null : Jsoup.clean(content, XSS_LIST);
    }

    static {
        //加载类时候读取配置的JSON文件
        Resource resource = new ClassPathResource("config/xss-white.json");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))){
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line.trim());
            }

            JSONObject jsonObject = JSON.parseObject(stringBuilder.toString());

            //允许标签
            JSONArray tags = jsonObject.getJSONArray("allow_tags");
            XSS_LIST.addTags(tags.toArray(new String[tags.size()]));
            log.info("允许标签:{}", tags);

            //允许属性
            JSONArray properties = jsonObject.getJSONArray("allow_properties");
            XSS_LIST.addAttributes(":all",properties.toArray(new String[properties.size()]));
            log.info("允许属性:{}",properties);

            //允许特殊属性
            JSONObject specialProperties = jsonObject.getJSONObject("special_properties");
            specialProperties.keySet().stream().forEach(tag -> {
                JSONArray attributes = specialProperties.getJSONArray(tag);
                XSS_LIST.addAttributes(tag,attributes.toArray(new String[attributes.size()]));
                log.info("允许特殊属性:标签={},属性={}",tag,attributes);
            });

            //允许特殊协议
            JSONObject protocols = jsonObject.getJSONObject("protocols");
            protocols.keySet().stream().forEach(tag -> {
                JSONObject protoObject = protocols.getJSONObject(tag);
                protoObject.keySet().stream().forEach(attr -> {
                    JSONArray protocolValues = protoObject.getJSONArray(attr);
                    XSS_LIST.addProtocols(tag,attr,protocolValues.toArray(new String[protocolValues.size()]));
                    log.info("允许特殊协议:标签={},属性={},协议={}",tag,attr,protocolValues);
                });
            });

            //固定属性值,非必须的
            JSONObject fixedProperties = jsonObject.getJSONObject("fixed_properties");
            if(fixedProperties != null && !fixedProperties.isEmpty()) {
                fixedProperties.keySet().stream().forEach(tag -> {
                    JSONObject property = fixedProperties.getJSONObject(tag);
                    if(property != null && !property.isEmpty()) {
                        property.keySet().stream().forEach(attr -> {
                            String value = property.getString(attr);
                            XSS_LIST.addEnforcedAttribute(tag, attr, value);
                            log.info("强制属性:标签={},属性={},值={}",tag,attr,value);
                        });
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("加载XSS过滤白名单异常,请检查文件 xss-white.json");
        }
    }
}
