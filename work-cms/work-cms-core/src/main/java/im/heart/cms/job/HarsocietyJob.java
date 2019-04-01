package im.heart.cms.job;

import im.heart.cms.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HarsocietyJob extends CommonJob {
    @Autowired
    ArticleService articleService;
    @Scheduled(cron = "0 01 15 * * ?")
    void executeJob()throws Exception{
        log.info("...........begin..........");
        parseArticleList("http://www.71.cn/towrite/officialdocument/thoughtandstudy/harsociety/1.shtml","和谐社会");
        log.info("...........end..........");
    }

}
