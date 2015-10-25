package sl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sl.dao.FileinDaoI;
import sl.model.SxnFilein;
import sl.model.SysUser;
import sl.pageModel.DataGrid;
import sl.pageModel.Dbsy;
import sl.pageModel.Filein;
import sl.service.FileinServiceI;
import sl.util.DateUtils;

@Service("fileinService")
public class FileinServiceImpl extends BaseServiceImpl implements
		FileinServiceI {
	/**
	 * Logger for this class
	 */
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

	private static final Logger logger = Logger
			.getLogger(FileinServiceImpl.class);
	private FileinDaoI fileinDao;

	public FileinDaoI getFileinDao() {
		return fileinDao;
	}

	@Autowired
	public void setFileinDao(FileinDaoI fileinDao) {
		this.fileinDao = fileinDao;
	}

	DateUtils du = new DateUtils();

	@Override
	public void save(Filein filein) {
		SxnFilein u = new SxnFilein();
		BeanUtils.copyProperties(filein, u);
		if (filein.getUuid() == null) {
			u.setUuid(UUID.randomUUID().toString());
			u.setCreateTime(du.getCurrentTime());
			u.setCreateUser(getSession().getLoginNames());
		} else {
			u.setUpdateTime(du.getCurrentTime());
			u.setUpdateUser(getSession().getLoginNames());
		}
		/* 新方法启动流程 start------------------------------------------------------ */
		// 1：获取请假单ID，使用请假单ID，查询请假单的对象LeaveBill
		String id = u.getUuid();
		// 3：使用当前对象获取到流程定义的key（对象的名称就是流程定义的key）
		String key = "LeaveBill";

		/**
		 * 4：从Session中获取当前任务的办理人，使用流程变量设置下一个任务的办理人 inputUser是流程变量的名称，
		 * 获取的办理人是流程变量的值
		 */
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("inputUser", getSession().getLoginName());// 表示惟一用户
		/**
		 * 5： (1)使用流程变量设置字符串（格式：LeaveBill.id的形式），通过设置，让启动的流程（流程实例）关联业务
		 * (2)使用正在执行对象表中的一个字段BUSINESS_KEY（Activiti提供的一个字段），让启动的流程（流程实例）关联业务
		 */
		String objId = key + "." + id;
		variables.put("objId", objId);
		// 6：使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
		runtimeService.startProcessInstanceByKey(key, objId, variables);
		/* 新方法启动流程 end-------------------------------------------------------- */
		// fileinDao.save(u);
		fileinDao.saveOrUpdate(u);

	}

	@Override
	public void submit(Filein filein) {
		SxnFilein u = new SxnFilein();
		BeanUtils.copyProperties(filein, u);
		if (filein.getUuid() == null) {
			u.setUuid(UUID.randomUUID().toString());
			u.setCreateTime(du.getCurrentTime());
			u.setCreateUser(getSession().getLoginNames());
			/*
			 * 新方法启动流程
			 * start------------------------------------------------------
			 */
			// 1：获取请假单ID，使用请假单ID，查询请假单的对象LeaveBill
			String id = u.getUuid();
			// 3：使用当前对象获取到流程定义的key（对象的名称就是流程定义的key）
			String key = "LeaveBill";

			/**
			 * 4：从Session中获取当前任务的办理人，使用流程变量设置下一个任务的办理人 inputUser是流程变量的名称，
			 * 获取的办理人是流程变量的值
			 */
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("inputUser", getSession().getLoginName());// 表示惟一用户
			/**
			 * 5： (1)使用流程变量设置字符串（格式：LeaveBill.id的形式），通过设置，让启动的流程（流程实例）关联业务
			 * (2)使用正在执行对象表中的一个字段BUSINESS_KEY（Activiti提供的一个字段），让启动的流程（流程实例）关联业务
			 */
			String objId = key + "." + id;
			variables.put("objId", objId);
			// 6：使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
			runtimeService.startProcessInstanceByKey(key, objId, variables);
			/*
			 * 新方法启动流程
			 * end--------------------------------------------------------
			 */
			// fileinDao.save(u);
			fileinDao.saveOrUpdate(u);
		} else {
			u.setUpdateTime(du.getCurrentTime());
			u.setUpdateUser(getSession().getLoginNames());

			/**
			 * 1：在完成之前，添加一个批注信息，向act_hi_comment表中添加数据，用于记录对当前申请人的一些审核信息
			 */
			// 使用任务ID，查询任务对象，获取流程流程实例ID
			String taskId = filein.getProcessid();
			System.out.println(filein.getProcessid());
			Task task = taskService.createTaskQuery()//
					.taskId(taskId)// 使用任务ID查询
					.singleResult();
			// 获取流程实例ID
			String processInstanceId = task.getProcessInstanceId();
			/**
			 * 注意：添加批注的时候，由于Activiti底层代码是使用： String userId =
			 * Authentication.getAuthenticatedUserId(); CommentEntity comment =
			 * new CommentEntity(); comment.setUserId(userId);
			 * 所有需要从Session中获取当前登录人
			 * ，作为该任务的办理人（审核人），对应act_hi_comment表中的User_ID的字段，不过不添加审核人，该字段为null
			 * 所以要求，添加配置执行使用Authentication.setAuthenticatedUserId();添加当前任务的审核人
			 * */
			Authentication.setAuthenticatedUserId(getSession().getLoginNames());
			taskService.addComment(taskId, processInstanceId, "dddddd");
			Map<String, Object> variables = new HashMap<String, Object>();

			variables.put("outcome", "123");
			// 3：使用任务ID，完成当前人的个人任务，同时流程变量
			taskService.complete(taskId, variables);
			// 4：当任务完成之后，需要指定下一个任务的办理人（使用类）-----已经开发完成

			/**
			 * 5：在完成任务之后，判断流程是否结束 如果流程结束了，更新请假单表的状态从1变成2（审核中-->审核完成）
			 */
			ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
					.processInstanceId(processInstanceId)// 使用流程实例ID查询
					.singleResult();
			// 流程结束了
			if (pi == null) {
				// 更新请假单表的状态从1变成2（审核中-->审核完成）
				// LeaveBill bill = leaveBillDao.findLeaveBillById(id);
				// bill.setState(2);
				System.out.println("流程结束了");
			}

		}

	}

	@Override
	public DataGrid datagrid(Filein filein) {
		DataGrid dg = new DataGrid();
		String hql = "from SxnFilein t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(filein, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(filein, hql);
		List<SxnFilein> l = fileinDao.find(hql, params, filein.getPage(),
				filein.getRows());
		List<Filein> nl = new ArrayList<Filein>();
		changeModel(l, nl);
		dg.setTotal(fileinDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<SxnFilein> l, List<Filein> nl) {
		if (l != null && l.size() > 0) {
			for (SxnFilein t : l) {
				Filein u = new Filein();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	private String addOrder(Filein filein, String hql) {
		if (filein.getSort() != null) {
			hql += " order by " + filein.getSort() + "+0 " + filein.getOrder();
		}
		return hql;
	}

	private String addWhere(Filein filein, String hql,
			Map<String, Object> params) {
		if (filein.getUuid() != null && !filein.getUuid().trim().equals("")) {
			// hql += " where t.name like :name";
			// params.put("name", "%%" + process.getName().trim() + "%%");
		}
		return hql;
	}

	@Override
	public Filein getById(String uuid) {
		Filein filein = new Filein();
		SxnFilein m = fileinDao.get("from SxnFilein t where t.uuid='" + uuid
				+ "'");
		BeanUtils.copyProperties(m, filein);
		return filein;
	}

	@Override
	public List<Filein> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String ids) {
		// TODO Auto-generated method stub
		String[] nids = ids.split(",");
		String hql = "delete SxnFilein t where t.uuid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		fileinDao.executeHql(hql);

	}

	@Override
	public List<Filein> treeGrid(Filein o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Filein o) {
		// TODO Auto-generated method stub
	}

	@Override
	public DataGrid queryHistoricActivityInstance(String uuid) {
		Task task = taskService.createTaskQuery()//
				.taskId(uuid)//使用任务ID查询
				.singleResult();
		//2：使用任务对象Task获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		DataGrid dg = new DataGrid();
		List<HistoricActivityInstance> hais = historyService
				.createHistoricActivityInstanceQuery()
				// 过滤条件
				.processInstanceId(processInstanceId)
				// 分页条件
				// .listPage(firstResult, maxResults)
				// 排序条件
				.orderByHistoricActivityInstanceStartTime().asc()
				// 执行查询
				.list();
		for (HistoricActivityInstance hai : hais) {
			System.out.print("activitiId:" + hai.getActivityId() + "，");
			System.out.print("name:" + hai.getActivityName() + "，");
			System.out.print("type:" + hai.getActivityType() + "，");
			System.out.print("pid:" + hai.getProcessInstanceId() + "，");
			System.out.print("assignee:" + hai.getAssignee() + "，");
			System.out.print("startTime:" + hai.getStartTime() + "，");
			System.out.print("endTime:" + hai.getEndTime() + "，");
			System.out.println("duration:" + hai.getDurationInMillis());
		}

		dg.setTotal((long) hais.size());
		dg.setRows(hais);
		return dg;

	}
	
	@Override
	public Filein getByTaskId(String uuid) {
		//1：使用任务ID，查询任务对象Task
		Task task = taskService.createTaskQuery()//
				.taskId(uuid)//使用任务ID查询
				.singleResult();
		//2：使用任务对象Task获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		//3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
						.processInstanceId(processInstanceId)//使用流程实例ID查询
						.singleResult();
		//4：使用流程实例对象获取BUSINESS_KEY
		String buniness_key = pi.getBusinessKey();
		//5：获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
		String id = "";
		if(StringUtils.isNotBlank(buniness_key)){
			//截取字符串，取buniness_key小数点的第2个值
			id = buniness_key.split("\\.")[1];
		}
		
		
		Filein filein = new Filein();
		SxnFilein m = fileinDao.get("from SxnFilein t where t.uuid='" + id
				+ "'");
		BeanUtils.copyProperties(m, filein);
		filein.setProcessid(uuid);
		return filein;
	}


}
