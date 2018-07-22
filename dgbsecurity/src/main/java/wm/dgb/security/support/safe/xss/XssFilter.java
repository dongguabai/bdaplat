package wm.dgb.security.support.safe.xss;

import com.google.common.collect.Lists;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author Dongguabai
 * @date 2018-07-22 14:04
 */
public class XssFilter implements Filter {

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    protected static List<String> allowed = Lists.newArrayList("/**/*.ico","/assets/**","*.html","/code/image","/session/invalid");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
       HttpServletRequest httpServletRequest = (HttpServletRequest) request;
       String uri = httpServletRequest.getRequestURI();
        for (String allowedPath : allowed) {
            if (pathMatcher.match(allowedPath,uri)){
                chain.doFilter(request,response);
                return;
            }
        }
        chain.doFilter(new XssHttpServletRequest(httpServletRequest),response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
