package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71InternationalJob   extends CommonJob {
    @Override
    public Integer  getMaxPage(){
        return 3;
    }
    @Scheduled(cron = "0 41 16 * * ?")
    void executeJob()throws Exception{
        log.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02007");
        category.setId(27L);
        category.setName("国际");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/international/1.shtml",category);
        log.info("...........end..........");
    }
}
