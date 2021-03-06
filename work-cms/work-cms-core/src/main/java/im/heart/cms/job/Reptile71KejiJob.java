package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71KejiJob   extends  CommonJob{

    @Scheduled(cron = "0 03 18 * * ?")
    void executeJob()throws Exception{
        logger.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02012");
        category.setId(32L);
        category.setName("科技");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/keji/1.shtml",category);
        logger.info("...........end..........");
    }
}
