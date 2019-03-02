package dgb.test.concurrent.exception;

/**
 * @author Dongguabai
 * @date 2018/9/7 14:25
 */
public class SleepyBoundedBufer<V> extends BaseBoundedBuffer<V>{
    public SleepyBoundedBufer(int size){
        super(size);
    }
    public void put(V v) throws InterruptedException {
        while(true){
            synchronized(this){
                if(!isFull()){//如果不是满的，可以存
                    doPut(v);
                    return;
                }
            }
            //如果是满的，休眠1秒钟，然后重试
            Thread.sleep(1000);
        }
    }
    public V take() throws InterruptedException {
        while(true){
            synchronized(this){
                if(!isEmpty()){//如果不是空的，就可以取
                    return doTake();
                }
            }
            //如果是空的，休眠1秒钟，重试
            Thread.sleep(1000);
        }
    }
}
