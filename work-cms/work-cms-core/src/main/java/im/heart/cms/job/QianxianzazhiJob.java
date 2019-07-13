package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import im.heart.cms.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QianxianzazhiJob extends CommonJob {
    @Override
    public Integer  getMaxPage(){
        return 3;
    }
    @Autowired
    ArticleService articleService;
    @Scheduled(cron = "0 42 11 * * ?")
    void executeJob()throws Exception{
        log.info("...........begin..........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("04");
        category.setId(4L);
        category.setName("《前线》杂志");
        parseArticleList("http://www.71.cn/acastudies/qianxianzazhi/1.shtml",category);
        log.info("...........end..........");
    }

}
