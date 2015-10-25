package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.UserDaoI;
import sl.model.SysUser;
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<SysUser> implements UserDaoI {

}
