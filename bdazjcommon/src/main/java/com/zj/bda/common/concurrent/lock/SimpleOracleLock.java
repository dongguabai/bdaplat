package com.zj.bda.common.concurrent.lock;

import com.zj.bda.common.concurrent.support.LockDecorator;
import com.zj.bda.common.concurrent.support.OracleLockEntity;
import com.zj.bda.common.concurrent.support.OracleLockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Oracle分布式锁
 * 注意：1.性能较差
 * 2.可能会出现死锁（比如解锁的时候数据库挂了）
 * 3.代码复杂
 * 4.不可重入
 *
 * @author Dongguabai
 * @date 2018-07-20 10:56
 */
@Component("simpleOracleLock")
@Slf4j
public class SimpleOracleLock extends LockDecorator {

    public static final String LOCK_ID = "1";
    public static final OracleLockEntity LOCK_ORACLE_ENTITY = new OracleLockEntity(LOCK_ID);

    @Autowired
    private OracleLockMapper oracleLockMapper;

    /**
     * 非阻塞式加锁
     *
     * @return
     */
    @Override
    public boolean tryLock() {
        try {
            oracleLockMapper.insert(LOCK_ORACLE_ENTITY);
        } catch (Exception e) {
            log.info("尝试加锁失败！");
            return false;
        }
        return true;
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {
        oracleLockMapper.deleteByPrimaryKey(LOCK_ID);
    }

    /**
     * 阻塞式加锁
     */
    @Override
    public void lock() {
        if (!tryLock()) {
            int randomSleepMillis = new Random().nextInt(100);
            try {
                Thread.sleep(randomSleepMillis);
            } catch (InterruptedException e) {
                log.info("尝试加锁失败，线程sleep{}毫秒！", randomSleepMillis);
            }
            lock();
        }
    }

}
