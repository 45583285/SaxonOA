package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.InfoDaoI;
import sl.model.SysInfo;
@Repository("infoDao")
public class InfoDaoImpl extends BaseDaoImpl<SysInfo> implements InfoDaoI {

}
