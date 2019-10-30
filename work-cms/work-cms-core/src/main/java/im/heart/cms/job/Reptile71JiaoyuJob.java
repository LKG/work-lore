package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71JiaoyuJob   extends CommonJob {

    @Scheduled(cron = "0 55 16 * * ?")
    void executeJob()throws Exception{
        logger.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02013");
        category.setId(33L);
        category.setName("教育");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/jiaoyu/1.shtml",category);
        logger.info("...........end..........");
    }
}
