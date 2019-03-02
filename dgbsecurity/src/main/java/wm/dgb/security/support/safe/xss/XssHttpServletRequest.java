package wm.dgb.security.support.safe.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;

/**
 * @author Dongguabai
 * @date 2018-07-22 14:03
 */
public class XssHttpServletRequest extends HttpServletRequestWrapper {

    public XssHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return JsoupUtil.cleanXss(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        return this.clearXss(super.getParameterValues(name));
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = super.getParameterMap();
        parameterMap.keySet().forEach(key->{
            parameterMap.put(key, this.clearXss(parameterMap.get(key)));
        });
        return parameterMap;
    }

    private String[] clearXss(String[] source) {
        if(source != null) {
            for(int x = 0,length = source.length;x < length ; x++) {
                source[x] = JsoupUtil.cleanXss(source[x]);
            }
        }
        return source;
    }
}