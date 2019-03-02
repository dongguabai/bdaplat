package wm.dgb.security.support.safe.csrf.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zj.bda.common.encrypt.aes.AesUtil;
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
        this.setHeaderName(AesUtil.encryptCbc(token.getHeaderName(), AesUtil.AesCbcEnum.CSRF_TOKEN_KEY_IVPARAMETER) );
        this.setToken(AesUtil.encryptCbc(token.getToken(),AesUtil.AesCbcEnum.CSRF_TOKEN_KEY_IVPARAMETER));
        this.wm= AesUtil.encryptCbc(token.getHeaderName().substring(2),AesUtil.AesCbcEnum.CSRF_TOKEN_KEY_IVPARAMETER);
        this.zm= AesUtil.encryptCbc(token.getToken().substring(2),AesUtil.AesCbcEnum.CSRF_TOKEN_KEY_IVPARAMETER);
    }
}
