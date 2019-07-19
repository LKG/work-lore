package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71CommunityJob extends  CommonJob{
    @Override
    public Integer  getMaxPage(){
        return 3;
    }
    @Scheduled(cron = "0 12 13 * * ?")
    void executeJob()throws Exception{
        log.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02004");
        category.setId(24L);
        category.setName("社会");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/community/41.shtml",category);
        log.info("...........end..........");
    }

}
