package dgb.test.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Dongguabai
 * @date 2018/8/30 13:17
 */
@Slf4j
public class AtomicExample1 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        //值是0的时候就赋值为2
        count.compareAndSet(0,2);
        count.compareAndSet(0,1);
        count.compareAndSet(1,4);
        count.compareAndSet(2,6);
        count.compareAndSet(5,9);

        log.info("最终结果：{}",count.get());
    }
}
