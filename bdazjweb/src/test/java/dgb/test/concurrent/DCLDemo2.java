package dgb.test.concurrent;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Dongguabai
 * @date 2018/9/24 9:51
 */
@Data
@AllArgsConstructor
public class DCLDemo2 {

    private String username;
    private String password;

    public static void main(String[] args) {
        DCLDemo2 dCLDemo2 = new DCLDemo2("Tom","123");
    }
}
