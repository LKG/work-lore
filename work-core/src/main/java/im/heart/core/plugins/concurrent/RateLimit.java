package im.heart.core.plugins.concurrent;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author lkg
 * @desc :速率限制注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RateLimit {

    //漏桶算法
    //令牌算法
    //活动窗口算法
    /**
     *
     * @author lkg
     * @desc :速率限制类型
     */
    public static enum RateLimitType {
        /**
         * 传统类型
         */
        CUSTOMER,
        /**
         *  根据 IP地址限制
         */
        IP
    }
    /**
     * 资源名称，用于描述接口功能
     */
    String name() default "";

    /**
     * 资源 key
     */
    String key() default "";

    /**
     * key prefix
     */
    String prefix() default "";

    /**
     * 时间范围，单位秒
     */
    int period();

    /**
     * 限制访问次数
     */
    double count() default 20;

    /**
     * 时间类型，默认毫秒
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS ;

    /**
     * 限制类型
     */
    RateLimitType limitType() default RateLimitType.CUSTOMER;

}
