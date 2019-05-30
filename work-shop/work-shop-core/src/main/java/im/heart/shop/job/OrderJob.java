package im.heart.shop.job;

import im.heart.shop.entity.Order;
import im.heart.shop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class OrderJob {
    protected static final Logger logger = LoggerFactory.getLogger(OrderJob.class);
    @Autowired
    OrderService orderService;
    /**
     * 自动取消订单
     * <p>
     * 定时检查订单未付款情况，如果超时 LITEMALL_ORDER_UNPAID 分钟则自动取消订单
     * 定时时间是每次相隔半个小时。
     * <p>
     * TODO
     * 注意，因为是相隔半小时检查，因此导致订单真正超时时间是 [LITEMALL_ORDER_UNPAID, 30 + LITEMALL_ORDER_UNPAID]
     */
    @Scheduled(fixedDelay = 30 * 60 * 1000)
    @Transactional(rollbackFor = Exception.class)
    public void checkOrderUnpaid() {
        logger.info("系统开启任务检查订单是否已经超期自动取消订单");
    }

    /**
     * 自动确认订单
     * <p>
     * 定时检查订单未确认情况，如果超时 LITEMALL_ORDER_UNCONFIRM 天则自动确认订单
     * 定时时间是每天凌晨3点。
     * <p>
     * TODO
     * 注意，因为是相隔一天检查，因此导致订单真正超时时间是 [LITEMALL_ORDER_UNCONFIRM, 1 + LITEMALL_ORDER_UNCONFIRM]
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void checkOrderUnConfirm() {
        logger.info("系统开启任务检查订单是否已经超期自动确认收货");
    }

    /**
     * 可评价订单商品超期
     * <p>
     * 定时检查订单商品评价情况，如果确认商品超时 LITEMALL_ORDER_COMMENT 天则取消可评价状态
     * 定时时间是每天凌晨4点。
     * <p>
     * TODO
     * 注意，因为是相隔一天检查，因此导致订单真正超时时间是 [LITEMALL_ORDER_COMMENT, 1 + LITEMALL_ORDER_COMMENT]
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void checkOrderComment() {
        logger.info("系统开启任务检查订单是否已经超期未评价");
    }
}
