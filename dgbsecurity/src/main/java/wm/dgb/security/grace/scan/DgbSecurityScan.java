package wm.dgb.security.grace.scan;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Dongguabai
 * @date 2018/8/15 13:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DgbSecurityScannerRegistrar.class)
public @interface DgbSecurityScan {

    String[] basePackages() default {"wm.dgb.security"};
}
