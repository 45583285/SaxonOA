package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.UserRoleDaoI;
import sl.model.SysUserRole;
@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<SysUserRole> implements UserRoleDaoI {

}
