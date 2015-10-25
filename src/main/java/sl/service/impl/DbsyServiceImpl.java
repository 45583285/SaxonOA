package sl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sl.dao.FileinDaoI;
import sl.dao.UserDaoI;
import sl.model.SxnFilein;
import sl.model.SysUser;
import sl.pageModel.DataGrid;
import sl.pageModel.Dbsy;
import sl.pageModel.Filein;
import sl.service.DbsyServiceI;
import sl.util.DateUtils;
@Service("dbsyService")
public class DbsyServiceImpl extends BaseServiceImpl implements DbsyServiceI {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(DbsyServiceImpl.class);
	private FileinDaoI fileinDao;

	public FileinDaoI getFileinDao() {
		return fileinDao;
	}

	@Autowired
	public void setFileinDao(FileinDaoI fileinDao) {
		this.fileinDao = fileinDao;
	}
	private UserDaoI userDao;
	public UserDaoI getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}
	@Resource
	private ProcessEngine processEngine;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private FormService formService;
	DateUtils du = new DateUtils();

	@Override
	public DataGrid datagrid() {
		DataGrid dg = new DataGrid();
		// 创建任务查询对象
		TaskQuery taskQuery = taskService.createTaskQuery();
		String assignee = getSession().getLoginName();
		taskQuery.taskAssignee(assignee).orderByTaskCreateTime().desc();
		// 执行查询
		List<Task> tasks = taskQuery.list();
		System.out.println("======================【" + assignee
				+ "】的代办任务列表=================");
		List<Dbsy> nl = new ArrayList<Dbsy>();
		for (Task task : tasks) {
			TaskFormData formData = formService.getTaskFormData(task.getId());
			//获取Form key的值
			String url = formData.getFormKey();
			//1：使用任务ID，查询任务对象Task

			//2：使用任务对象Task获取流程实例ID
			String processInstanceId = task.getProcessInstanceId();
			//3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
			ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
							.processInstanceId(processInstanceId)//使用流程实例ID查询
							.singleResult();
			//4：使用流程实例对象获取BUSINESS_KEY
			String buniness_key = pi.getBusinessKey();
			System.out.println(buniness_key+"eeeeeeeeee123");
			//5：获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
			String id = "";
			Dbsy u = new Dbsy();
			if(StringUtils.isNotBlank(buniness_key)){
				//截取字符串，取buniness_key小数点的第2个值
				id = buniness_key.split("\\.")[1];
				try{
					SxnFilein m = fileinDao.get("from SxnFilein t where t.uuid='" + id+ "'");
					SysUser p = userDao.get("from SysUser t where t.sysName='"+task.getAssignee().toString()+"'");
					System.out.println(id+task.getAssignee().toString()+":::::::"+m.getTitle().toString()+p.getName().toString());
					u.setDealingPeople(p.getName().toString());
					u.setModuleName(m.getTitle().toString());
					u.setTitle(m.getTitle().toString());
				}catch(Exception e){
					
				}
				
			}
			u.setId(task.getId().toString());
			u.setAssignee(task.getAssignee().toString());
			u.setCreateTime(du.getyyyy_MM_dd(task.getCreateTime()));
			u.setName(task.getName());
			
			nl.add(u);
		}
		dg.setRows(nl);

		return dg;
	}



}
