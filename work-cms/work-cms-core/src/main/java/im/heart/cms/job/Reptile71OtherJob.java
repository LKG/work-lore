package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71OtherJob    extends  CommonJob{

    @Scheduled(cron = "0 30 9 * * ?")
    void executeJob()throws Exception{
        log.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("00216");
        category.setId(36L);
        category.setName("其他");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/other/1.shtml",category);
        log.info("...........end..........");
    }

}
