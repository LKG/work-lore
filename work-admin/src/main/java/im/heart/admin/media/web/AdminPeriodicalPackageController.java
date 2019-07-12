package im.heart.admin.media.web;


import im.heart.common.context.ContextManager;
import im.heart.core.CommonConst;
import im.heart.core.enums.Status;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.frame.entity.FrameTpl;
import im.heart.media.entity.PeriodicalPackage;
import im.heart.media.enums.PackageType;
import im.heart.media.packs.PackageDataService;
import im.heart.media.service.PeriodicalPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class  AdminPeriodicalPackageController extends AbstractController {
	protected static final String apiVer = "/periodical/package";
	protected static final String VIEW_LIST="admin/periodical/package/list";
	protected static final String VIEW_DETAILS="admin/periodical/package/details";
	
	@Autowired
	private PeriodicalPackageService periodicalPackageService;

	
	@Value("${prod.oss.bucketName:''}")
	private String bucketName = "";
	@Value("${prod.oss.site:'oss.aliyuncs.com'}")
	private String ossSite = "oss.aliyuncs.com";
	@RequestMapping(apiVer)
	public ModelAndView home(ModelMap model) {
		super.success(model);
		model.put("bucketName", bucketName);
		model.put("fullUrl", "http://"+bucketName+"."+ossSite);
		return new ModelAndView(VIEW_LIST,model);
	}
	@RequestMapping(value = apiVer + "/success", method = RequestMethod.GET)
	protected ModelAndView success(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
	/**
	 * 
	 * @功能说明：查询所有
	 * @param request
	 * @param response
	 * @param jsoncallback
	 * @param page
	 * @param size
	 * @param sort
	 * @param order
	 * @param token
	 * @param model
	 * @return
	 */
	@RequestMapping(apiVer+"s")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
			@RequestParam(value = "sort", required = false,defaultValue = "modiTime") String sort,
			@RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
			ModelMap model) {
		Specification<PeriodicalPackage> spec= DynamicSpecifications.bySearchFilter(request, PeriodicalPackage.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order,PeriodicalPackage.class);
		Page<PeriodicalPackage> pag = this.periodicalPackageService.findAll(spec, pageRequest);
		super.success(model,pag);
		model.put("bucketName", bucketName);
		model.put("fullUrl", "http://"+bucketName+"."+ossSite);
		return new ModelAndView(VIEW_LIST);
	}
	@RequestMapping(value = apiVer+"/{id}",method = RequestMethod.GET)
	protected ModelAndView findById(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger id,
			HttpServletRequest request,
			ModelMap model) {
		Optional<PeriodicalPackage> optional = this.periodicalPackageService.findById(id);
		if(optional.isPresent()){
			super.success(model, optional.get());
		}
		model.put("bucketName", bucketName);

		return new ModelAndView(VIEW_DETAILS);
	}
	@RequestMapping(value = apiVer+"/{ids}/publish",method = RequestMethod.POST)
	protected ModelAndView publishPackage(
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger[] ids,
			HttpServletRequest request,
			ModelMap model) {
		for(BigInteger id:ids){
			Optional<PeriodicalPackage> optional = this.periodicalPackageService.findById(id);
			if(optional.isPresent()){
				PeriodicalPackage materialPackage=optional.get();
				materialPackage.setCheckStatus(Status.enabled);
				this.periodicalPackageService.save(materialPackage);
			}
		}
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
	@RequestMapping(value = apiVer+"/{ids}/disabled",method = RequestMethod.POST)
	protected ModelAndView disabledPackage(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger[] ids,
			HttpServletRequest request,
			ModelMap model) {
		for(BigInteger id:ids){
			Optional<PeriodicalPackage> optional = this.periodicalPackageService.findById(id);
			if(optional.isPresent()){
				PeriodicalPackage materialPackage=optional.get();
				materialPackage.setCheckStatus(Status.disabled);
				this.periodicalPackageService.save(materialPackage);
			}

		}
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}

	/**
	 * 打包
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value =apiVer+"/generateCity",method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView packageMaterialPrice(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		ResponseError responseError=new ResponseError(WebError.REQUEST_PARAMETER_MISSING);
		PackageType packType=PackageType.city ;
		String periodicalCode="0";
		String cityId="0";
		PeriodicalPackage periodicalPackage=null;
		Optional<PeriodicalPackage> PeriodicalPackageOpt = this.periodicalPackageService.findByAreaAndCodeAndType(cityId, periodicalCode, packType);
		if(PeriodicalPackageOpt.isPresent()){
			periodicalPackage=new PeriodicalPackage();
			periodicalPackage.setPeriodConfigId(new BigInteger("0"));
			periodicalPackage.setCityId(cityId);
			periodicalPackage.setCityName("中国");
			periodicalPackage.setPackageCode(periodicalCode);
			periodicalPackage.setPackageType(packType.value+"");
			periodicalPackage.setPackageName(packType.info);
			this.periodicalPackageService.save(periodicalPackage);
		}else{
			periodicalPackage=PeriodicalPackageOpt.get();
		}
		if(CommonConst.FlowStatus.initial==periodicalPackage.getStatus()){
			responseError=new ResponseError("request_periodical_area_notexit","21502","打包程序正在处理中，请稍后再试");
			super.fail(model,responseError);
			return new ModelAndView(RESULT_PAGE,model);
		}
		PackageDataService packageDataService=(PackageDataService) ContextManager.getBean(packType.code+PackageDataService.BEAN_SUFFIX);
		PeriodicalPackage processedmaterialPackage = packageDataService.addPackageData(periodicalPackage);
		if(CommonConst.FlowStatus.processed!=processedmaterialPackage.getStatus()){
			responseError=new ResponseError("request_periodical_area_notexit","21503","打包失败请检查打包数据或联系管理员");
			super.fail(model,responseError);
			return new ModelAndView(RESULT_PAGE,model);
		}
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS,model);
	}
	
	@RequestMapping(value = apiVer+"/{ids}/generate",method = RequestMethod.POST)
	protected ModelAndView makePackage(
			@RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN , required = false) String token,
			@PathVariable BigInteger[] ids,
			HttpServletRequest request,
			ModelMap model) {
		for(BigInteger id:ids){
			Optional<PeriodicalPackage> optional = this.periodicalPackageService.findById(id);
			if(optional.isPresent()){
				PeriodicalPackage materialPackage =optional.get();
				PackageType packType= PackageType.findPackageType(Integer.valueOf(materialPackage.getPackageType()));
				if(packType!=null){
					PackageDataService packageDataService=(PackageDataService) ContextManager.getBean(packType.code+PackageDataService.BEAN_SUFFIX);
					packageDataService.addPackageData(materialPackage);
				}
			}
		}
		super.success(model);
		return new ModelAndView(VIEW_SUCCESS);
	}
}
