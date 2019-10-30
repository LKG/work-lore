package im.heart.cms.job;

import im.heart.cms.entity.Article;
import im.heart.cms.entity.ArticleCategory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import java.io.IOException;
import java.net.URL;

@Slf4j
public abstract class  AbstractJob {
    Integer DEFAULT_MAX_PAGE=5;
    public abstract Integer getMaxPage();
    @Async
    public void parseArticleList(String url, ArticleCategory category){
        try {
            String pageStr= StringUtils.substringAfterLast(url,"/");
            pageStr=StringUtils.substringBefore(pageStr,".");
            if(Integer.valueOf(pageStr)>this.getMaxPage()){
                logger.error("{},达到最大页 {} ,url:{}：",pageStr,this.getMaxPage(),url);
                return;
            }
            Document listEle= Jsoup.parse(new URL(url),10000);
            Elements articleEle=listEle.select(".articlelist_title a");
            for (Element article:articleEle){
                String articleUrl=article.attr("href");
                try {
                    Thread.sleep(RandomUtils.nextLong(100,800));
                } catch (InterruptedException e) {
                    logger.error(e.getStackTrace()[0].getMethodName(), e);
                }
                parseArticle(articleUrl,category);
            }
            Elements pages=listEle.select(".page_box a.next");
            if(pages.hasClass("disable")){
                return;
            }
            String aUrl=pages.attr("href");
            parseArticleList(aUrl,category);
        } catch (IOException e) {
            logger.error(url);
            logger.error(e.getStackTrace()[0].getMethodName(), e);
        }
    }

    public abstract Article parseArticle(String url,  ArticleCategory category);
}
