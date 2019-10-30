package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71EcologyJob  extends  CommonJob {

    @Scheduled(cron = "0 07 12 * * ?")
    void executeJob()throws Exception{
        logger.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02005");
        category.setId(25L);
        category.setName("生态");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/ecology/1.shtml",category);
        logger.info("...........end..........");
    }
}
