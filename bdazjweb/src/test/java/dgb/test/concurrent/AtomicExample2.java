package dgb.test.concurrent;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author Dongguabai
 * @date 2018/8/30 13:17
 */
@Slf4j
public class AtomicExample2 {
    //                                      引用类                                                           类的Class   字段名你，字段必须被volatile修饰
    private static AtomicIntegerFieldUpdater<AtomicExample2> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample2.class,"count");

    //这里定义一个实例对象
    private static AtomicExample2 atomicExample2 = new AtomicExample2();

    @Getter
    private volatile int count = 100;

    public static void main(String[] args) {
        //如果当前对象的count字段的值是100的时候，更新为120，返回是否更新成功
        if (updater.compareAndSet(atomicExample2, 100, 120)) {
            log.info("更新成功----count:{}",atomicExample2.getCount());
        }
        if (updater.compareAndSet(atomicExample2, 100, 120)) {
            log.info("更新成功----count:{}",atomicExample2.getCount());
        }else {
            log.info("更新失败----count:{}",atomicExample2.getCount());
        }

    }
}
