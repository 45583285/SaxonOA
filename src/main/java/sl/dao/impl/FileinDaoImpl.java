package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.FileinDaoI;
import sl.model.SxnFilein;
@Repository("fileinDao")
public class FileinDaoImpl extends BaseDaoImpl<SxnFilein> implements FileinDaoI {

}
