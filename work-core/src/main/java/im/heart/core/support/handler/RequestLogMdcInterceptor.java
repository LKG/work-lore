package im.heart.core.support.handler;

import im.heart.core.web.utils.WebUtilsEx;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RequestLogMdcInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(WebUtilsEx.getParametersJson(request));
        log.info(WebUtilsEx.getHeadersJson(request));
        log.info(WebUtilsEx.getParametersJson(request));
        String unqId = RandomStringUtils.randomNumeric(16);
        MDC.put("req.id",unqId);
        MDC.put("req.uri",request.getRequestURI());
        if (request.getParameter("imei") != null) {
            MDC.put("req.imei",request.getParameter("imei"));
        }
        MDC.put("req.beginTime",System.currentTimeMillis() + "");
        return super.preHandle(request, response, handler);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long totalTime = System.currentTimeMillis() - Long.parseLong(MDC.get("req.beginTime"));
        log.info("请求：{}, 耗时：{} ms", MDC.get("req.uri"), totalTime);
        MDC.remove("req.id");
        MDC.remove("req.uri");
        MDC.remove("req.imei");
        MDC.remove("req.beginTime");
        super.afterCompletion(request, response, handler, ex);
    }

}
