package wm.dgb.security.grace.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.AntPathMatcher;

/**
 * @author Dongguabai
 * @date 2018/8/1 22:55
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AntPathMatcherUtil {

    private static final AntPathMatcher ANTPATHMATCHER = new AntPathMatcher();

    public static boolean match(String pattern, String path){
        return ANTPATHMATCHER.match(pattern,path);
    }
}
