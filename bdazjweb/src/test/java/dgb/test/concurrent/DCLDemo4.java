package dgb.test.concurrent;

/**
 * @author Dongguabai
 * @date 2018/9/25 23:43
 */
public class DCLDemo4 {

    /*如果perThreadInstance.get（）返回一个非null值，则此线程
            已完成查看初始化所需的同步*/

    private final ThreadLocal perThreadInstance = new ThreadLocal();

    private DCLTestBean instance = null;

    public DCLTestBean getInstance() {
        if (perThreadInstance.get() == null) {
            //回报助手;
            createInstance();
        }
        return instance;
    }


    private final void createInstance() {
        synchronized (this) {
            if (instance == null) {
                instance = new DCLTestBean();
            }
        }
        //任何非null值都可以作为参数
        perThreadInstance.set(perThreadInstance);
    }
}
