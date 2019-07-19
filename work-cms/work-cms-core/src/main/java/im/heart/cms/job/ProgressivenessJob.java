package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import im.heart.cms.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProgressivenessJob extends CommonJob {
    @Autowired
    ArticleService articleService;

    @Scheduled(cron = "0 22 10 * * ?")
    void executeJob()throws Exception{
        log.info("...........begin..........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("03003");
        category.setId(39L);
        category.setName("先进性教育");
        parseArticleList("http://www.71.cn/towrite/officialdocument/thoughtandstudy/progressiveness/16.shtml",category);
        log.info("...........end..........");
    }

}
