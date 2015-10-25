package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.LoginInfoDaoI;
import sl.model.SysLoginInfo;
@Repository("loginInfoDao")
public class LoginInfoDaoImpl extends BaseDaoImpl<SysLoginInfo> implements LoginInfoDaoI {

}
