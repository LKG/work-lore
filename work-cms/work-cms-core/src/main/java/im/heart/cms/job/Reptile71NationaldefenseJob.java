package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71NationaldefenseJob extends CommonJob {

    @Scheduled(cron = "0 45 19 * * ?")
    void executeJob() throws Exception {
        logger.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02010");
        category.setId(30L);
        category.setName("国防");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/nationaldefense/1.shtml", category);
    }
}
