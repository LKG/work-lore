package im.heart.common.web;

import im.heart.core.CommonConst;
import im.heart.core.utils.StringUtilsEx;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.usercore.entity.FrameUser;
import im.heart.usercore.service.FrameUserService;
import im.heart.usercore.vo.FrameUserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * 
 * @作者 LKG 
 * 文件上传控制
 */
@Controller
public class UploadController extends AbstractController {
	protected static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	protected static final String apiVer = "/upload";
	@Autowired
	private FrameUserService frameUserService;
	@Value("${prod.upload.path.root:''}")
	private String uploadFilePath="";
	protected static final String  IMG_FILE_PATH= CommonConst.STATIC_UPLOAD_ROOT;

	@RequestMapping(apiVer+"/userHeadImg")
	public ModelAndView userHeadImg(HttpServletRequest request, HttpServletResponse response,
                                 ModelMap model) {
		Subject subject = SecurityUtils.getSubject();
		Object po = subject.getPrincipal();
		ResponseError responseError=new ResponseError(WebError.AUTH_CREDENTIALS_EXPIRED);
		if(po instanceof FrameUserVO){
			FrameUser user=(FrameUser)po;
			List<MultipartFile> uploadFileList=super.getFileList(request);
			if(uploadFileList!=null&&!uploadFileList.isEmpty()){
				for(MultipartFile file :uploadFileList){
					String realPath=File.separator+IMG_FILE_PATH+File.separator+DateTime.now().toString("yyyyMMdd")+File.separator;
					String path=uploadFilePath+realPath;
					try {
						String filePath = super.uploadFile(file, path);
						model.put("name", file.getOriginalFilename());
						String url= StringUtilsEx.replace(realPath+filePath, File.separator, "/");
						this.frameUserService.updateFrameUserImgUrl(user.getUserId(), request.getContextPath()+url);
						model.put("url", "/"+IMG_FILE_PATH+"/"+url);
						super.success(model);
						break;
					} catch (Exception e) {
						logger.error(e.getStackTrace()[0].getMethodName(), e);
						super.fail(model,false);
						return new ModelAndView(RESULT_PAGE);
					}
				}
				return new ModelAndView(RESULT_PAGE);
			}
		}
		super.fail(model,responseError);
		return new ModelAndView(RESULT_PAGE);
	}

	
}
