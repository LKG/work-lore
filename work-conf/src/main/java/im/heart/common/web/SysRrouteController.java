package im.heart.common.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import im.heart.core.CommonConst;
import im.heart.core.enums.FileHeader;
import im.heart.core.model.RequestBody;
import im.heart.core.model.RequestParas;
import im.heart.core.utils.*;
import im.heart.core.web.AbstractController;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
public class SysRrouteController extends AbstractController {
	protected static final Logger logger = LoggerFactory.getLogger(SysRrouteController.class);

	/**
	 * 
	 * @功能说明： 判断是否是黑名单ip
	 * @param ip
	 * @return
	 */
	private boolean isRestrict(String ip){
		
		return false;
	}
	
	private static int PACK_LENGTH=10;
	/**
	 * 
	 * @功能说明：处理请求参数
	 * @param input
	 * @return
	 */
	private RequestParas getRequestParameter(InputStream input) {
		RequestParas requestParameters = null;
		try {
			byte[] pack = IOUtils.toByteArray(input);
			// 密钥
			byte[] key = new byte[8];
			if (pack == null || pack.length <= PACK_LENGTH) {
				return null;
			}
			// 有效的数据体
			byte[] body = new byte[pack.length - PACK_LENGTH];
			logger.info("====recv data length {{{" + pack.length + "}}}");
			// 取出密钥
			System.arraycopy(pack, 2, key, 0, key.length);
			// 取出消息体
			System.arraycopy(pack, 10, body, 0, body.length);
			logger.info("解压key：{} " ,new String(key, "UTF-8"));
			// 转换压缩文件流为普通流
			byte[] unpack = FileUtilsEx.uncompressToStringback(body);
			String signBody = DesUtils.parseSignback(unpack, new String(key,"UTF-8"));
			logger.info("解压signBody ：{}" ,signBody);
			requestParameters = JSON.parseObject(signBody, RequestParas.class);
		} catch (IOException e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
		}finally{
			IOUtils.closeQuietly(input);
		}
		return requestParameters;
	}
	/**
	 * 
	 * @功能说明：提取公用参数
	 * @return 
	 */
	protected Map<String, Object> pickCommonParams(RequestParas requestParameters){
		Map<String, Object> params = Maps.newHashMap();
		String cpImei = requestParameters.getCpImei();
		String cpPhoneNum = requestParameters.getCpPhoneNum();
		String cpUid = requestParameters.getCpUid();
		String cpPrt = requestParameters.getCpPrt();
		String cpPltfm = requestParameters.getCpPltfm();
		String cpTouch = requestParameters.getCpTouch();
		String cpCityId = requestParameters.getCpCityId();
		String cpCh = requestParameters.getCpCh();
		String cpModel = requestParameters.getCpModel();
		String cpRatio = requestParameters.getCpRatio();
		String cpTpl = requestParameters.getCpTpl();
		String cpVer = requestParameters.getCpVer();
		String cpResver = requestParameters.getCpResver();
		String cpPubResPath = requestParameters.getCpPubResPath();
		String cpLon = requestParameters.getCpLon();
		String cpLat = requestParameters.getCpLat();
		params.put("cpImei", cpImei);
		params.put("cpPhoneNum", cpPhoneNum);
		params.put("cpUid", cpUid);
		params.put("cpPrt", cpPrt);
		params.put("cpPltfm", cpPltfm);
		params.put("cpTouch", cpTouch);
		params.put("cpCityId", cpCityId);
		params.put("cpCh", cpCh);
		params.put("cpModel", cpModel);
		params.put("cpRatio", cpRatio);
		params.put("cpTpl", cpTpl);
		params.put("cpVer", cpVer);
		params.put("cpResver", cpResver);
		params.put("cpPubResPath", cpPubResPath);
		params.put("cpLon", cpLon);
		params.put("cpLat", cpLat);
		logger.info(">>>>>>FLOW_UP|{}|{}|{}|{}|",cpImei,cpPhoneNum,cpPrt,cpUid);
		return params;
	}

	private String buildAction(HttpServletRequest request,String action){
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("http://").append(request.getServerName()).append(":").append(request.getServerPort());
		strBuff.append(request.getContextPath()).append("/" ).append(action);
		return  strBuff.toString();
	}

	/**
	 * @功能说明：手机端统一入口
	 * @param request
	 * @param response
	 */
	@RequestMapping("/api/v1")
	protected @ResponseBody  void  sysLoadService(HttpServletRequest request,
			HttpServletResponse response) {
		OutputStream outStream = null;InputStream input =null;
		try {
			String ip = BaseUtils.getIpAddr(request);
			logger.info("请求ip:----" + ip);
			if(isRestrict(ip)){
				//返回访问过于频繁，限制访问
				return ;
			}
			outStream = response.getOutputStream();
			input = request.getInputStream();
			RequestParas requestParameters = this.getRequestParameter(input);
			if(requestParameters==null){
				logger.info("缺少请求参数" );
				return ;
			}
			long start = System.currentTimeMillis();
			Map<String, Object> params=this.pickCommonParams(requestParameters);
			params.put("cpIp", ip);
			List<RequestBody> actions = requestParameters.getActions();
			if(actions==null||actions.isEmpty()){
				logger.info("缺少请求参数" );
				return ;
			}
			for (RequestBody body : actions) {
				String action = body.getAction();
				String requuid = body.getRequuid();
				String cpImei = (String) params.get("cpImei");
				String cpPhoneNum = (String) params.get("cpPhoneNum");
				String cpPrt = (String) params.get("cpPrt");
				logger.info(">>>>>>FLOW_UP|{}|{}|{}|{}|{}|",requuid,cpImei,cpPhoneNum,cpPrt);
				String actionUrl = buildAction(request,action);
				// 参数处理
				Map<String, String> paras = body.getParas();
				if (paras != null) {
					if (paras.containsKey("ps")) {
						String value = paras.get("ps");
						paras.remove("ps");
						params.put("size", StringUtilsEx.isNotBlank(value) ? value : CommonConst.Page.DEFAULT_SIZE);
					}
					if (paras.containsKey("pn")) {
						String value = paras.get("pn");
						paras.remove("pn");
						params.put("page", StringUtilsEx.isNotBlank(value) ? String.valueOf(Integer.valueOf(value) + 1): CommonConst.Page.DEFAULT_PAGE);
					}
					params.putAll(paras);
				}
				logger.info(">>>>>>|UUID:{}|IP:{}|REQ_ACTION:{}|REDIRECT_URL:{}|PARAMS:{}" , requuid,ip,
						actionUrl,cpPrt,JSON.toJSONString(params));
				outStream.write(requuid.getBytes());
				byte[] data = OkHttpClientUtils.fetchEntity(actionUrl, params);
				byte[] pack = null;
				// //处理反馈结果,判断反馈结果是否为压缩流，不是压缩流对数据进行压缩后再返回给客户端
				if (FileUtilsEx.isSpecFile(data, FileHeader.ZIP.getValue())) {
					logger.info("outStream  is zip ...");
					pack = data;
				} else {
					logger.info("outStream  is not zip  begin compress to byte ...");
					pack = FileUtilsEx.compressToByte(data);
					logger.info("outStream  compress to byte  end...");
				}
				// 写入长度
				outStream.write(StringUtilsEx.changeByteLength(pack.length).getBytes());
				// 写入数据
				outStream.write(pack);
				long end = System.currentTimeMillis();
				logger.info("====FLOW_DOWN,{},{},{},{},{}|",requuid,cpImei,cpPhoneNum, pack.length, cpPrt);
				logger.info(">>>>>>请求[{}] 耗时[{}]ms",action,(end - start));
				outStream.flush();
			}
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0].getMethodName(), e);
			//	TODO 处理出现异常统一返回错误信息给客户端
//			throw new Exception();
		} finally {
			IOUtils.closeQuietly(outStream);
			IOUtils.closeQuietly(input);
		}
		return ;
	}
	
}
