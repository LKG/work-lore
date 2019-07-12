package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71EconomyJob   extends  CommonJob{

    @Scheduled(cron = "0 06 16 * * ?")
    void executeJob()throws Exception{
        log.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02001");
        category.setId(21L);
        category.setName("经济");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/economy/1.shtml",category);
        log.info("...........end..........");
    }

}
