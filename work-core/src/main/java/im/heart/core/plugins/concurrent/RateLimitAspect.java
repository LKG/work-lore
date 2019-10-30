package im.heart.core.plugins.concurrent;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.RateLimiter;
import im.heart.core.utils.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 接口限流
 * @author MrBird
 */
@Slf4j
@Aspect
@Component
public class RateLimitAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private RateLimiter rateLimiter;


    @Pointcut("@annotation(im.heart.core.plugins.concurrent.RateLimit)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RateLimit limitAnnotation = method.getAnnotation(RateLimit.class);
        RateLimit.RateLimitType limitType = limitAnnotation.limitType();
        String name = limitAnnotation.name();
        String key;
        String ip = BaseUtils.getIpAddr(request);
        int limitPeriod = limitAnnotation.period();
        Double limitCount = limitAnnotation.count();
        switch (limitType) {
            case IP:
                key = ip;
                break;
            case CUSTOMER:
                key = limitAnnotation.key();
                break;
            default:
                key = StringUtils.upperCase(method.getName());
        }
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnnotation.prefix() + "_", key, ip));
        String luaScript = buildLuaScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
        String currentValue = this.redisTemplate.opsForValue().get(key);
        Number count = redisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
        logger.info("IP:{} 第 {} 次访问key为 {}，描述为 [{}] 的接口", ip, count, keys, name);
        if (count != null && count.intValue() <= limitCount) {
            return point.proceed();
        }
        rateLimiter= RateLimiter.create(limitCount);
        if (rateLimiter.tryAcquire()) {

        }
        throw new LimitAccessException("接口访问超出频率限制");
    }

    /**
     * 限流脚本
     * 调用的时候不超过阈值，则直接返回并执行计算器自加。
     *
     * @return lua脚本
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }
}
