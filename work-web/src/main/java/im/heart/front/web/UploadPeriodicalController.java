package im.heart.front.web;

import im.heart.core.CommonConst;
import im.heart.core.utils.StringUtilsEx;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.media.entity.Periodical;
import im.heart.media.entity.PeriodicalLog;
import im.heart.media.parser.PeriodicalParser;
import im.heart.media.service.PeriodicalLogService;
import im.heart.media.service.PeriodicalService;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.vo.FrameUserVO;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Controller
public class UploadPeriodicalController extends AbstractController {
    protected static final Logger logger = LoggerFactory.getLogger(UploadPeriodicalController.class);
    protected static final String apiVer = "/upload";
    protected static final String  FILE_ROOT_PATH= CommonConst.STATIC_UPLOAD_ROOT;

    @Value("${prod.material.file.path:''}")
    private String periodicalFilePath="";
    @Value("${prod.upload.path.root:''}")
    private String uploadFilePath="";

    @Autowired
    private PeriodicalService periodicalService;

    @Autowired
    private PeriodicalLogService periodicalLogService;

    @Autowired
    private PeriodicalParser periodicalParser;

    /**
     *
     * 文件上传
     * @param file
     * @param path
     * @return
     * @throws Exception
     */
    @Override
    protected String uploadFile(MultipartFile file, String path) throws Exception {
        return super.uploadFile(file,path,null);
    }

    @RequestMapping(apiVer + "/material")
    public ModelAndView importBathImg(HttpServletRequest request,
            @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @RequestParam(value = "periodicalCode", required = false) String periodicalCode,
            @RequestParam(value = "categoryId", required = false) BigInteger categoryId,
            @RequestParam(value = "categoryCode", required = false) String categoryCode,
            @RequestParam(value = "originPrice", required = false) BigDecimal originPrice,
            @RequestParam(value = "finalPrice", required = false) BigDecimal finalPrice,
            String filename,
            HttpServletResponse response,ModelMap model) {
        ResponseError responseError = new ResponseError(WebError.AUTH_CREDENTIALS_EXPIRED);
        FrameUserVO user = SecurityUtilsHelper.getCurrentUser();
        if (user == null) {
            responseError = new ResponseError(WebError.INVALID_REQUEST);
            super.fail(model, responseError);
            return new ModelAndView(RESULT_PAGE);
        }
        List<MultipartFile> uploadFileList = super.getFileList(request);
        if (uploadFileList != null && !uploadFileList.isEmpty()) {
            for (MultipartFile file : uploadFileList) {
                String path = File.separator+periodicalFilePath+File.separator + DateTime.now().toString("yyyyMMdd") + File.separator;
                try {
                    String realPath = uploadFilePath+path;
                    String realFileName = this.uploadFile(file, realPath);
                    if (StringUtilsEx.isBlank(filename)) {
                        filename = file.getOriginalFilename();
                    }
                    Periodical periodical = new Periodical();
                    periodical.setRealFilePath(realPath+realFileName);
                    String suffixes = StringUtils.substringAfterLast(realFileName, ".");
                    periodical.setFileHeader(suffixes);
                    periodical.setPeriodicalType(Periodical.PeriodicalType.sharing.code);
                    periodical.setAuthor(user.getNickName());
                    periodical.setUserId(user.getUserId());
                    periodical.setCategoryId(categoryId);
                    periodical.setCategoryCode(categoryCode);
//                    periodical.setCityId(cityId);
//                    periodical.setCityName(cityName);
                    periodical.setPeriodicalName(StringUtils.substringBeforeLast(filename,"."));
                    periodical.setPeriodicalCode(periodicalCode);
                    periodical.setFinalPrice(finalPrice);
                    periodical.setOriginPrice(originPrice);
                    periodical.setDataSize(file.getSize());
                    periodical.setStatus(CommonConst.FlowStatus.initial);
                    String url = StringUtilsEx.replace(path + realFileName, File.separator, "/");
                    String pathUrl="/"+FILE_ROOT_PATH+"/"+url;
                    periodical.setPathUrl(pathUrl);
                    this.periodicalService.save(periodical);
                    this.periodicalParser.addParserTask(periodical,file.getInputStream());
                    PeriodicalLog periodicalLog=new PeriodicalLog();
                    periodicalLog.setUserId(periodical.getUserId());
                    periodicalLog.setPeriodicalId(periodical.getId());
                    periodicalLog.setType("upload");
                    periodicalLog.setLogDesc( "{desc: '文件上传成功,添加解析任务' }");
                    this.periodicalLogService.save(periodicalLog);
                    super.success(model, "url", pathUrl);
                } catch (Exception e) {
                    logger.error(e.getStackTrace()[0].getMethodName(), e);
                    super.fail(model, new ResponseError(WebError.REQUEST_EXCEPTION));
                    return new ModelAndView(RESULT_PAGE);
                }finally {

                }
                return new ModelAndView(VIEW_SUCCESS);
            }
            super.fail(model, responseError);
            return new ModelAndView(RESULT_PAGE);
        }
        super.fail(model, responseError);
        return new ModelAndView(RESULT_PAGE);
    }

}
