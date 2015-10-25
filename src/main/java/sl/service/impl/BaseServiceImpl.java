package sl.service.impl;


import org.apache.struts2.ServletActionContext;

import sl.pageModel.SessionInfo;
import sl.util.ResourceUtil;
public class BaseServiceImpl {

	
	public SessionInfo getSession(){
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		return sessionInfo;
	}
}
