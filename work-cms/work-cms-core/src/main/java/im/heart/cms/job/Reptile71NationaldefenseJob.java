package im.heart.cms.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71NationaldefenseJob extends CommonJob {


    @Scheduled(cron = "0 48 9 * * ?")
    void executeJob() throws Exception {
        log.info("..........begin...........");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/nationaldefense/1.shtml", "国防");
    }
}
