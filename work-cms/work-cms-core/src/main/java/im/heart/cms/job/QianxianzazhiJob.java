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

    @Autowired
    ArticleService articleService;
    @Scheduled(cron = "0 52 12 * * ?")
    void executeJob()throws Exception{
        logger.info("...........begin..........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("04");
        category.setId(4L);
        category.setName("《前线》杂志");
        parseArticleList("http://www.71.cn/acastudies/qianxianzazhi/1.shtml",category);
        logger.info("...........end..........");
    }

}
