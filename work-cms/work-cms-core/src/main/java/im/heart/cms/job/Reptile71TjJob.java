package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71TjJob    extends  CommonJob{
    //http://www.71.cn/2019/0306/1036178.shtml

    @Scheduled(cron = "0 45 9 * * ?")
    void executeJob()throws Exception{
        log.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("00214");
        category.setId(34L);
        category.setName("图解");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/tj/1.shtml",category);
        log.info("...........end..........");
    }

}
