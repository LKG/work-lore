package im.heart.shop.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 检测优惠券过期情况
 */
@Component
public class CouponJob {
    protected static final Logger logger = LoggerFactory.getLogger(CouponJob.class);

//    @Autowired
//    private CouponService couponService;
//    @Autowired
//    private CouponUserService couponUserService;

    /**
     * 每隔一个小时检查
     * 注意，因为是相隔一个小时检查，因此导致优惠券真正超时时间可能比设定时间延迟1个小时
     */
    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void checkCouponExpired() {
        logger.info("系统开启任务检查优惠券是否已经过期");
    }

}
