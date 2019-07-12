package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import im.heart.cms.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScientificdevelopJob extends CommonJob {
    @Autowired
    ArticleService articleService;
    @Override
    public Integer  getMaxPage(){
        return 5;
    }
    @Scheduled(cron = "0 51 14 * * ?")
    void executeJob()throws Exception{
        log.info("...........begin..........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("03002");
        category.setId(38L);
        category.setName("科学发展观");
        parseArticleList("http://www.71.cn/towrite/officialdocument/thoughtandstudy/scientificdevelop/1.shtml",category);
        log.info("...........end..........");
    }

}
