package sl.dao.impl;

import org.springframework.stereotype.Repository;

import sl.dao.DeptDaoI;
import sl.model.SysDept;
@Repository("deptDao")
public class DeptDaoImpl extends BaseDaoImpl<SysDept> implements DeptDaoI {

}
