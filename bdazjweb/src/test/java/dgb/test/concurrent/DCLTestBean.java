package dgb.test.concurrent;


/**
 * @author Dongguabai
 * @date 2018/9/23 22:21
 */

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DCLTestBean {

    private String username;
    private Integer password;

    public DCLTestBean() {
        System.out.println(LocalDateTime.now()+"  |DCLTestBean初始化了。");
    }
}
