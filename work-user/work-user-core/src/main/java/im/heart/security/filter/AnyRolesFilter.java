package im.heart.security.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class AnyRolesFilter extends AccessControlFilter {
	private String unauthorizedUrl = "/unauthorized.jhtml";  
	private String loginUrl = "/login.jhtml";
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String[] roles = (String[]) mappedValue;
		if (roles == null) {
			// 如果没有设置角色参数，默认成功
			return true;
		}
		for (String role : roles) {
			if (getSubject(request, response).hasRole(role)) {
				return true;
			}
		}
		// 跳到onAccessDenied处理
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		// 表示没有登录，重定向到登录页面
		if (subject.getPrincipal() == null) {
			saveRequest(request);
			WebUtils.issueRedirect(request, response, loginUrl);
		} else {
			// 如果有未授权页面跳转过去
			if (StringUtils.hasText(unauthorizedUrl)) {
				WebUtils.issueRedirect(request, response, unauthorizedUrl);
			} else {
				// 否则返回401未授权状态码
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return false;
	}

}
