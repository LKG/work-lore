package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71CultureJob extends CommonJob {

    @Scheduled(cron = "0 58 12 * * ?")
    void executeJob()throws Exception{
        logger.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02003");
        category.setId(23L);
        category.setName("文化");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/culture/1.shtml",category);
        logger.info("...........end..........");
    }


}
