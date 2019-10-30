package im.heart.front.web;

import im.heart.common.DownloadCacheUtils;
import im.heart.core.CommonConst;
import im.heart.core.enums.Status;
import im.heart.core.utils.BaseUtils;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.media.entity.Periodical;
import im.heart.media.entity.PeriodicalCategory;
import im.heart.media.service.PeriodicalService;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.vo.FrameUserVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class DownloadController extends AbstractController {
    protected static final Logger logger = LoggerFactory.getLogger(DownloadController.class);
    protected static final String apiVer = "/fd";
    @Autowired
    private PeriodicalService periodicalService;
    private Cache downloadRetryCache;
    protected static final String CACHE_NAME = DownloadCacheUtils.CacheConfig.DOWNLOAD_CACHE.keyPrefix;
    @Autowired(required = false)
    private CacheManager cacheManager;

    private static int MAX_DOWN_COUNT=3;


    public Boolean checkDownCount(String username){
        if(this.downloadRetryCache==null){
            this.downloadRetryCache = this.cacheManager.getCache(CACHE_NAME);
        }
        AtomicInteger retryCount = this.downloadRetryCache.get(username,AtomicInteger.class);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
        }
        int count = retryCount.incrementAndGet();
        if (count >= MAX_DOWN_COUNT) {
            return  Boolean.FALSE;
        }
        return  Boolean.TRUE;
    }

    @RequestMapping(value = apiVer+"/{id}")
    protected ModelAndView findById(
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @PathVariable BigInteger id,
            @RequestParam(required = false) String filename,
            @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
            HttpServletRequest request, HttpServletResponse response,
            ModelMap model) {
        FrameUserVO user= SecurityUtilsHelper.getCurrentUser();
        Optional<Periodical> optional = this.periodicalService.findById(id);
        if(optional.isPresent()){
            Periodical po=optional.get();
            if(!checkDownCount(user.getUserName())){
                super.fail(model,new ResponseError(WebError.AUTH_EXCESSIVE_ATTEMPTS));
                return  new ModelAndView(RESULT_PAGE);
            }
            //限制下载
            if(!Status.enabled.equals(po.getCheckStatus())){
                super.fail(model,new ResponseError(WebError.ACCESS_DENIED));
                return  new ModelAndView(RESULT_PAGE);
            }
            //校验用户是否有权限
            boolean isFree=(BigDecimal.ZERO.compareTo(po.getFinalPrice())==0);
            if(isFree||user.getIsExpiry()){
                if(StringUtils.isBlank(filename)){
                    filename=po.getPeriodicalName().concat(".").concat(po.getFileHeader());
                }
                this.periodicalService.addUpdateDownTimesTask(id);
                BaseUtils.setFileDownloadHeader(request,response,filename);
                //文件的真实路径
                response.addHeader("X-Accel-Redirect",po.getRealFilePath());
                response.addHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_OCTET_STREAM_VALUE);
                return  new ModelAndView(RESULT_PAGE);
            }
        }

        super.fail(model,new ResponseError(WebError.ACCESS_DENIED));
        return  new ModelAndView(RESULT_PAGE);
    }
}
