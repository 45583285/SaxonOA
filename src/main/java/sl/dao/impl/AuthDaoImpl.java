package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.AuthDaoI;
import sl.model.SysAuth;
@Repository("authDao")
public class AuthDaoImpl extends BaseDaoImpl<SysAuth> implements AuthDaoI {

}
