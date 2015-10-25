package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.ProcessDaoI;
import sl.model.SysProcess;
@Repository("processDao")
public class ProcessDaoImpl extends BaseDaoImpl<SysProcess> implements ProcessDaoI {

}
