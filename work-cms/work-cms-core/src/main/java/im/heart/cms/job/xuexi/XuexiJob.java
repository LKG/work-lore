package im.heart.cms.job.xuexi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import im.heart.cms.entity.Article;
import im.heart.cms.job.CommonJob;
import im.heart.core.utils.OkHttpClientUtils;
import im.heart.core.utils.StringUtilsEx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@Slf4j
public class XuexiJob extends CommonJob {

    @Scheduled(cron = "0 19 15 * * ?")
    void executeJob()throws Exception{
        log.info("...........begin..........");
        parseArticleList("https://www.xuexi.cn/d05cad69216e688d304bb91ef3aac4c6/9a3668c13f6e303932b5e0e100fc248b.html","时评");
        log.info("...........end..........");
    }
    @Override
    public Integer  getMaxPage(){
        return 10;
    }
    @Async
    @Override
    public void parseArticleList(String url,String type){
        try {
            String lists = OkHttpClientUtils.fetchEntityString(url,null);
            String jsonContent1 = StringUtilsEx.substringBetween(lists, "globalCache = ","};");
            JSONObject jsonObject1 = JSON.parseObject(jsonContent1+"}");
            JSONArray list=jsonObject1.getJSONObject("fpcka8ayva4e0001").getJSONArray("list");
            for(Object obj:list){
                JSONObject jsonObject=(JSONObject)obj;
                String _id=jsonObject.getString("_id");
                String static_page_url=jsonObject.getString("static_page_url");
                System.out.println(_id);
                String prefix=StringUtilsEx.substringBeforeLast(static_page_url,"/");
                String id=StringUtilsEx.substringAfterLast(static_page_url,"/");
                id = StringUtilsEx.substringBeforeLast(id, ".html");
                parseArticle(prefix+"/data"+id+".js",type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public  Article parseArticle(String url, String type){
        try {
            Article entity=null;
            String cl=  OkHttpClientUtils.fetchEntityString(url,null);
            String jsonContent = StringUtilsEx.substringBetween(cl, "globalCache = ","};");

            JSONObject jsonContentObj = JSON.parseObject(jsonContent+"}");
            JSONObject pageObj=jsonContentObj.getJSONObject("fp1w9a6h2o1czk01");
            JSONObject detailobj= pageObj.getJSONObject("detail");
            String title= detailobj.getString("name");
            String frst_name= detailobj.getString("frst_name");
            String source= detailobj.getString("source");
            String content= detailobj.getString("content");
            String author= detailobj.getString("author");
            String editor= detailobj.getString("editor");
            entity.setTitle(title);
            System.out.println(detailobj.keySet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws  Exception{
        //https://www.xuexi.cn/03c8b56d5bce4b3619a9d6c2dfb180ef/9a3668c13f6e303932b5e0e100fc248b.html?pageNumber=2
        String listid="9a3668c13f6e303932b5e0e100fc248b";
        String lists=  OkHttpClientUtils.fetchEntityString("https://www.xuexi.cn/d05cad69216e688d304bb91ef3aac4c6/data"+listid+".js",null);
        String jsonContent1 = StringUtilsEx.substringBetween(lists, "globalCache = ","};");
        JSONObject jsonObject1 = JSON.parseObject(jsonContent1+"}");
        System.out.println(jsonObject1.keySet());
        JSONArray list=jsonObject1.getJSONObject("fpcka8ayva4e0001").getJSONArray("list");

       for(Object obj:list){
           JSONObject jsonObject=(JSONObject)obj;
           String _id=jsonObject.getString("_id");
           String static_page_url=jsonObject.getString("static_page_url");
           String prefix=StringUtilsEx.substringBeforeLast(static_page_url,"/");
           String id=StringUtilsEx.substringAfterLast(static_page_url,"/");
           id = StringUtilsEx.substringBeforeLast(id, ".html");
           String url = prefix+"/data"+id+".js";
           String cl=  OkHttpClientUtils.fetchEntityString(url,null);
           String jsonContent = StringUtilsEx.substringBetween(cl, "globalCache = ","};");
           JSONObject jsonContentObj = JSON.parseObject(jsonContent+"}");
           JSONObject pageObj=jsonContentObj.getJSONObject("fp1w9a6h2o1czk01");
           JSONObject detailobj= pageObj.getJSONObject("detail");
           System.out.println(detailobj);
           System.out.println(detailobj.keySet());
//           System.out.println(jsonObject.get("fp8ttetzkclds001").equals(jsonObject.get("fp1w9a6h2o1czk01")));
       }
//        sysQuery, fp1w9a6h2o1czk01, fp8ttetzkclds001
    }
}
