package im.heart.cms.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71JiaoyuJob   extends CommonJob {
    //http://www.71.cn/2019/0306/1036178.shtml

    @Scheduled(cron = "0 50 9 * * ?")
    void executeJob()throws Exception{
        log.info("..........begin...........");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/jiaoyu/1.shtml","教育");
        log.info("...........end..........");
    }
}
