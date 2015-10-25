package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.BulletinDaoI;
import sl.model.SxnBulletin;
@Repository("bulletinDao")
public class BulletinDaoImpl extends BaseDaoImpl<SxnBulletin> implements BulletinDaoI {

}
