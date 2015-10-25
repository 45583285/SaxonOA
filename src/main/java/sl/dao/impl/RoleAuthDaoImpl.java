package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.RoleAuthDaoI;
import sl.model.SysRoleAuth;
@Repository("roleAuthDao")
public class RoleAuthDaoImpl extends BaseDaoImpl<SysRoleAuth> implements RoleAuthDaoI {

}
