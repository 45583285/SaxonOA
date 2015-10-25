package sl.interceptor;
import sl.pageModel.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		
		 

		// 获取当前用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		// 获取当前访问的URL，并去掉当前应用程序的前缀（也就是 namespaceName + actionName ）
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privilegeUrl = null;
		if (namespace.endsWith("/")) {
			privilegeUrl = namespace + actionName;
		} else {
			privilegeUrl = namespace + "/" + actionName;
		}

		// 要去掉开头的'/'
		if (privilegeUrl.startsWith("/")) {
			privilegeUrl = privilegeUrl.substring(1);
		}

		// 如果未登录用户
		if (user == null) {
			System.out.println("如果未登录用户");
			if (privilegeUrl.startsWith("homeAction_login")) { 
				// 如果是正在使用登录功能，就放行
				return invocation.invoke();
			} else {
				// 如果不是去登录，就转到登录页面
				//return "loginUI";
				return invocation.invoke();//放行
			}
		}
		// 如果已登录用户（就判断权限）
		else {
			System.out.println("如果已登录用户");
			return invocation.invoke();//放行
		}
		
	}
	
			 
}
