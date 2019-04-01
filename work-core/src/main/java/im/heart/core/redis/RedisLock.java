package im.heart.core.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author gg
 * @desc 获取redis 锁
 */
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     * @param key
     * @param value 当前时间+ 超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        if (this.redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        String currentValue = this.redisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获得上一个锁的时间
            String olcValue = this.redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(olcValue) && olcValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                this.redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】 解锁异常" + e.getMessage());
        }
    }

}
