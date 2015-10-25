package sl.dao.impl;

import org.springframework.stereotype.Repository;
import sl.dao.MenuDaoI;
import sl.model.SysMenu;
@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<SysMenu> implements MenuDaoI {

}
