package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71PoliticsJob    extends CommonJob {
    @Override
    public Integer  getMaxPage(){
        return 3*300;
    }
    @Scheduled(cron = "0 26 12 * * ?")
    void executeJob()throws Exception{
        log.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02002");
        category.setId(22L);
        category.setName("政治");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/politics/1.shtml",category);
        log.info("...........end..........");
    }

}
