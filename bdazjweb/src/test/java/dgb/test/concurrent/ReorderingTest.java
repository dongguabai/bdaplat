package dgb.test.concurrent;

/**
 * @author Dongguabai
 * @date 2018/9/1 21:42
 */
public class ReorderingTest {

    private int a = 0;
    private Boolean flag = false;

    public void write(){
        a = 1;
        flag = true;
    }

    public void read(){
       if (flag){
           int b = a+1;
           System.out.println(b);
       }
    }
}
