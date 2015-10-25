package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.ContentsDaoI;
import sl.model.SxnContents;
@Repository("contentsDao")
public class ContentsDaoImpl extends BaseDaoImpl<SxnContents> implements ContentsDaoI {

}
