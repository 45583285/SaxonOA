package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.MenusDaoI;
import sl.model.SxnMenu;
@Repository("menusDao")
public class MenusDaoImpl extends BaseDaoImpl<SxnMenu> implements MenusDaoI {

}
