package dgb.test.concurrent.exception;

/**
 * @author Dongguabai
 * @date 2018/9/7 14:24
 */
public class GrumpBoundedBufer<V> extends BaseBoundedBuffer<V>{
    public GrumpBoundedBufer(int size){
        super(size);
    }
    public synchronized void put(V v)throws BufferFullException{
        if(isFull()){//存的时候，如果是满的，就抛异常
            throw new BufferFullException();
        }
        doPut(v);
    }
    public synchronized V take()throws BufferEmptyException{
        if(isEmpty()){//取的时候，如果是空的，就抛异常
            throw new BufferEmptyException();
        }
        return doTake();
    }
}
