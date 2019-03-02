package dgb.test.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Dongguabai
 * @date 2018/9/6 15:35
 */
public class CasTest {
    private static final long valueOffset;

    static {
        valueOffset=1L;
    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int i = atomicInteger.addAndGet(1);
        System.out.println(i);
    }
}
