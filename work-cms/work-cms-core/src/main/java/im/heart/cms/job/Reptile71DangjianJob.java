package im.heart.cms.job;

import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Reptile71DangjianJob  extends CommonJob {
    //http://www.71.cn/2019/0306/1036178.shtml
    @Override
    public Integer  getMaxPage(){
        return 3;
    }

    @Scheduled(cron = "0 36 17 * * ?")
    void executeJob()throws Exception{
        log.info("..........begin...........");
        ArticleCategory category=new ArticleCategory();
        category.setCode("02006");
        category.setId(26L);
        category.setName("党建");
        parseArticleList("http://www.71.cn/acastudies/expcolumn/dangjian/1.shtml",category);
        log.info("...........end..........");
    }
}
