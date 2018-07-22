package wm.dgb.security.support.safe.csrf.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.Serializable;

/**
 * Csrf返回前台参数
 *
 * @author Dongguabai
 * @date 2018-07-22 1:40
 */
@Setter
@Getter
public class CsrfVO implements Serializable {

    private String token;
    private String headerName;

    public CsrfVO(CsrfToken token) {
        this.setHeaderName(token.getHeaderName());
        this.setToken(token.getToken());
    }
}
