package dgb.test.concurrent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Dongguabai
 * @date 2018/10/21 9:38
 */
public class ReadWriteLockCache {

    private static Map<String,Object> CACHE = new HashMap<>();
    private static ReadWriteLock rw = new ReentrantReadWriteLock();
    private static Lock r = rw.readLock();
    private static Lock w = rw.writeLock();

    public static Object get(String key){
        r.lock();
        try {
            return CACHE.get(key);
        }finally {
            r.unlock();
        }
    }

    /**
     * 返回之前的值
     * @param key
     * @param value
     * @return
     */
    public static Object put(String key, Object value){
        w.lock();
        try {
            return CACHE.put(key,value);
        }finally {
            w.unlock();
        }
    }

    public static void clear(){
        w.lock();
        try {
            CACHE.clear();
        }finally {
            w.unlock();
        }
    }

    private static List<String> LIST = new ArrayList<>();

    static void doSth(){
        for (int i = 0; i < 459; i++) {
            LIST.add(String.valueOf(i));
        }
    }

    public static void main(String[] args) {
        doSth();

        int anInt = getInt(LIST.size());

        for (int i = 0; i < anInt; i++) {
            List<String> list = LIST.subList(i * 100, i==anInt-1?LIST.size():(i + 1) * 100);
            System.out.println(LIST.subList(i * 100, i==anInt-1?LIST.size():(i + 1) * 100));
            System.out.println("=====================");
            System.out.println(LIST);
            System.out.println("---------");
        }
    }

    public static int getInt(int i){
        BigDecimal decimal = new BigDecimal(i*1.0/100).setScale(0, BigDecimal.ROUND_CEILING);
        return decimal.intValue();
    }
}
