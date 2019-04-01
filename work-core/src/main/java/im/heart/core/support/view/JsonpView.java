package im.heart.core.support.view;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import im.heart.core.CommonConst.RequestResult;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * 
 * @author gg
 * @desc 扩展FASTJSONView 支持JSONP 格式 hibernate 延时代理不进行序列化
 */
public class JsonpView extends FastJsonJsonView {

	public boolean isUpdateContentLength() {
		return updateContentLength;
	}
	@Override
	public void setUpdateContentLength(boolean updateContentLength) {
		this.updateContentLength = updateContentLength;
	}

	private boolean updateContentLength = false;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jsonCallback = request.getParameter(RequestResult.JSON_CALLBACK);
		SerializeFilter  filter =null;
	/*	if(lazy){
			filter = new FastJosnPropertyFilter();
		}*/
		Object value = super.filterModel(model);
		FastJsonConfig fastJsonConfig =super.getFastJsonConfig();
		String text = JSON.toJSONString(value,filter, fastJsonConfig.getSerializerFeatures());
		if (jsonCallback != null && !StringUtils.isBlank(jsonCallback)) {
			text = jsonCallback + "(" + text + ")";
		}
		byte[] bytes = text.getBytes(fastJsonConfig.getCharset());
		OutputStream stream = this.updateContentLength ? createTemporaryOutputStream()
				: response.getOutputStream();
		stream.write(bytes);

		if (this.updateContentLength) {
			writeToResponse(response, (ByteArrayOutputStream) stream);
		}
	}

}