package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.RoleDaoI;
import sl.model.SysRole;
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<SysRole> implements RoleDaoI {

}
