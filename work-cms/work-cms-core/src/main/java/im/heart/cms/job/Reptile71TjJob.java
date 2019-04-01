package im.heart.cms.job;

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
        parseArticleList("http://www.71.cn/acastudies/expcolumn/tj/1.shtml","图解");
        log.info("...........end..........");
    }

}
