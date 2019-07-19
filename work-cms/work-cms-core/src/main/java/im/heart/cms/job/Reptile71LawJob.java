package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71LawJob   extends CommonJob {

    @Scheduled(cron = "0 07 18 * * ?")
    void executeJob()throws Exception{
        log.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02011");
        category.setId(31L);
        category.setName("法律");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/law/1.shtml",category);
        log.info("...........end..........");
    }
}
