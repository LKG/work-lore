package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71LlJob   extends CommonJob {

    @Scheduled(cron = "0 25 19 * * ?")
    void executeJob()throws Exception{
        log.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("00215");
        category.setId(35L);
        category.setName("理论");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/ll/1.shtml",category);
        log.info("...........end..........");
    }
}
