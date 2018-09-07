package dgb.test.concurrent.exception;

/**
 * 有界缓存
 * @author Dongguabai
 * @date 2018/9/7 11:31
 */
public abstract class BaseBoundedBuffer<V> {
    private final V[] buff;
    private int tail;
    private int head;
    private int count;
    protected BaseBoundedBuffer(int capacity){
        this.buff = (V[])new Object[capacity];
    }
    protected synchronized final void doPut(V v){//存
        buff[tail] = v;
        tail++;
        if(tail == buff.length){
            tail = 0;
        }
        count++;
    }
    protected synchronized final V doTake(){//get
        V v = buff[head];
        buff[head] = null;
        head++;
        if(head == buff.length){
            head = 0;
        }
        count--;
        return v;
    }
    protected synchronized final boolean isFull(){//是否是满的
        return count == buff.length;
    }
    protected synchronized final boolean isEmpty(){//是否是空的
        return count == 0;
    }
}
