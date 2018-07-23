package wm.dgb.security.support.safe.csrf.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zj.bda.common.util.DesUtil;
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

    @JsonProperty("_tm")
    private String token;
    @JsonProperty("_hm")
    private String headerName;
    @JsonProperty("_wm")
    private String wm;
    @JsonProperty("_zm")
    private String zm;

    public CsrfVO(CsrfToken token) {
        this.setHeaderName(DesUtil.encrypt(token.getHeaderName(),DesUtil.DescEnum.CSRF_TOKEN_KEY) );
        this.setToken(DesUtil.encrypt(token.getToken(),DesUtil.DescEnum.CSRF_TOKEN_KEY));
        this.wm= DesUtil.encrypt(token.getHeaderName().substring(2),DesUtil.DescEnum.CSRF_TOKEN_KEY);
        this.zm= DesUtil.encrypt(token.getToken().substring(2),DesUtil.DescEnum.CSRF_TOKEN_KEY);
    }
}
