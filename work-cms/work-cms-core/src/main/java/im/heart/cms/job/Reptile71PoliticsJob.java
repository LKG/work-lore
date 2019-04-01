package im.heart.cms.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71PoliticsJob    extends CommonJob {

    @Scheduled(cron = "0 30 9 * * ?")
    void executeJob()throws Exception{
        log.info("..........begin...........");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/politics/1.shtml","政治");
        log.info("...........end..........");
    }

}
