package im.heart.cms.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import im.heart.cms.entity.Article;
import im.heart.cms.entity.ArticleCategory;
import im.heart.cms.service.ArticleService;
import im.heart.core.utils.DateUtilsEx;
import im.heart.core.utils.OkHttpClientUtils;
import im.heart.core.utils.StringUtilsEx;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

@Slf4j
public class CommonJob extends  AbstractJob {
    @Autowired
    ArticleService articleService;
    @Override
    public Integer  getMaxPage(){
        return DEFAULT_MAX_PAGE;
    }

    @Async
    @Override
    public   Article parseArticle(String url, ArticleCategory category){
        Article entity=null;
        try
        {
            URL uri=new URL(url);
            entity= new Article();
            entity.setCategoryCode(category.getCode());
            entity.setCategoryId(category.getId());
            entity.setCategoryName(category.getName());
            Document html= Jsoup.parse(uri,10000);
            String idStr= StringUtils.substringAfterLast(url,"/");
            idStr=StringUtils.substringBefore(idStr,".");
            if (StringUtils.isBlank(idStr)){
                log.info("获取id 失败"+url);
                return null;
            }
            BigInteger id=new BigInteger(idStr);
            boolean exist=this.articleService.existsById(id);
            if (exist){
                log.info("已收录"+url);
                return null;
            }
            entity.setId(id);
            Elements keywordsEle=html.select("meta[name=keywords]");
            String keywords=keywordsEle.attr("content");
            entity.setSeoKeywords(keywords);
            Elements descriptionEle=html.select("meta[name=description]");
            String description=descriptionEle.attr("content");
            entity.setSeoDescription(description);
            Elements article=html.select(".article-main");
            Elements titleEle=article.select(".article-title");
            String title= titleEle.text();
            entity.setTitle(title);
            Elements subtitle=article.select(".article-subtitle");
            entity.setSeoTitle(subtitle.outerHtml());
            Elements editorsEle=article.select(".article-infos .editors");
            String editors=editorsEle.text();
            entity.setAuthor(editors);
            Elements dateEle=article.select(".article-infos .date");
            Elements sourceEle=article.select(".article-infos .source");
            String source=sourceEle.text();

            Elements describeEle=article.select("#describe");
            entity.setSummary(describeEle.outerHtml());
            Elements contentEle=article.select("#article-content");
            //判断是否有分页
            Elements showallcontEle=article.select("#showallcont");
            if(showallcontEle.hasText()){
                String cl= OkHttpClientUtils.fetchEntityString(" http://app.71.cn/?app=article&controller=article&action=fulltext&jsoncallback=jsonp1553093490389&contentid="+id);
                String jsonContent = StringUtilsEx.substringBetween(cl, "jsonp1553093490389(",");");
                JSONObject jsonObject = JSON.parseObject(jsonContent);
                String content=jsonObject.getString("content");
                contentEle.html(content);
            }
            entity.setObtainUrl(url);
            url=StringUtils.substringAfter(url,"//");
            url=StringUtils.substringAfter(url,"/");
            entity.setIsPub(Boolean.TRUE);
            try {
                entity.setPushTime(DateUtilsEx.stringToDate(dateEle.text(),"yyyy-MM-dd HH:mm"));
            } catch (ParseException e) {
                log.error(url);
                log.error(e.getStackTrace()[0].getMethodName(), e);
            }
            if (!contentEle.hasText()){
                log.info("获取内容异常"+url);
                return null;
            }
            entity.setUrl(url);
            entity.setSource(source);
            entity.setContent(contentEle.outerHtml());
            entity.setUserId(BigInteger.ZERO);
            this.articleService.save(entity);
        }catch (MalformedURLException e){
            log.error(url);
            log.error(e.getStackTrace()[0].getMethodName(), e);
        }catch (IOException e){
            log.error(url);
            log.error(e.getStackTrace()[0].getMethodName(), e);
        }catch (Exception e){
            log.error(url);
            log.error(e.getStackTrace()[0].getMethodName(), e);
        }
        return entity;
    }
}
