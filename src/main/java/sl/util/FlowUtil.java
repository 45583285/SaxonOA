package sl.util;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipInputStream;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;

import sl.dao.FileinDaoI;
import sl.model.SxnFilein;
import sl.service.impl.BaseServiceImpl;
public class FlowUtil extends BaseServiceImpl {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FlowUtil.class);
	DateUtils du = new DateUtils();
	public void submit(SxnFilein u, FileinDaoI fileinDao,
			ProcessEngine processEngine, TaskService taskService, String user,
			HistoryService historyService, RuntimeService runtimeService,
			RepositoryService repositoryService, String oldProcId) throws Exception {
		FlowUtil f = new FlowUtil();
		if(u.getProcessid().equals(null) || u.getProcessid().equals("")){
			//启动流程
			String procId = f.startProcess(processEngine, "myProcess");
			
			if(u.getUuid()==null){
				u.setUuid(UUID.randomUUID().toString());
				u.setCreateTime(du.getCurrentTime());
				u.setCreateUser(getSession().getLoginNames());
			}else{
				u.setUpdateTime(du.getCurrentTime());
				u.setUpdateUser(getSession().getLoginNames());
			}
			
			
			u.setProcessid(procId);
			String nextTask = nextTaskDefinition(procId,historyService,runtimeService,repositoryService);
			Map<String, String> nodeMap = new HashMap<String, String>();
			nodeMap = f.findNextTask(historyService, repositoryService, runtimeService, procId);
			Set keys = nodeMap.keySet();
			Iterator it = keys.iterator();
			while(it.hasNext()){
				Object key = it.next();
				Object value = nodeMap.get(key);
				System.out.println(key);
				System.out.println(value);
			}
			
			
			
			 List<Task> tasks = taskService.createTaskQuery().taskDefinitionKey(nextTask).list();  
		        for (Task task : tasks) {  
		        	 if(u.getProcessid().equals(task.getProcessInstanceId())){
		        		 taskService.claim(task.getId(), user);  
		        	 }
		            	
		        }
		        
		        
		        
		        
		      tasks = taskService.createTaskQuery().taskAssignee(user).list();  
		        for (Task task : tasks) {  
		        	if(u.getProcessid().equals(task.getProcessInstanceId())){
		        		taskService.complete(task.getId());  
		        		
		        	}
			            
		        } 
		         
		        
		        nextTask = nextTaskDefinition(procId,historyService,runtimeService,repositoryService);
		        tasks = taskService.createTaskQuery().taskDefinitionKey(nextTask).list();  
		        System.out.println(tasks.size());
		        for (Task task : tasks) {  
		        	if(u.getProcessid().equals(task.getProcessInstanceId())){
		        		 taskService.claim(task.getId(), user);  
		        	}
			           
		        }
		        
		        fileinDao.saveOrUpdate(u);
		        
		}else{
			
			
			 String nextTask = "";
			 List<Task> tasks;
			 System.out.println("000000000000000000000000000000000");
			 if(!f.endTask(historyService, repositoryService, runtimeService, oldProcId)){
				 System.out.println("1111111111111111111111111111111");
				nextTask = nextTaskDefinition(oldProcId,historyService,runtimeService,repositoryService);
				tasks = taskService.createTaskQuery().taskAssignee(user)
						.taskDefinitionKey(nextTask).list();
				for (Task task : tasks) {
					if(u.getProcessid().equals(task.getProcessInstanceId())){
						// 完成任务
						taskService.complete(task.getId());
					}
				}
				nextTask = nextTaskDefinition(oldProcId,historyService,runtimeService,repositoryService);
				tasks = taskService.createTaskQuery()
						.taskDefinitionKey(nextTask).list();
				for (Task task : tasks) {
					// 认领任务
					if(u.getProcessid().equals(task.getProcessInstanceId())){
						taskService.claim(task.getId(), user);
					}

				}
			 }else{
				 
				 
/*	判断流程是否已经结束			 HistoryService historyService = processEngine.getHistoryService();
				 HistoricProcessInstance historicProcessInstance =
				 historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();
				 System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());*/
				 
				 
				 
				 System.out.println("22222222222222222222222222222");
				 nextTask = nextTaskDefinition(oldProcId,historyService,runtimeService,repositoryService);
				 tasks = taskService.createTaskQuery().taskAssignee(user)
							.taskDefinitionKey(nextTask).list();
					for (Task task : tasks) {
						if (u.getProcessid().equals(task.getExecutionId())) {
							// 完成任务
							taskService.complete(task.getId());
						}
					}
					logger.info("流程结束！");
			 }
		        
		        fileinDao.saveOrUpdate(u);
		}
		
		
	}
	
	/**
	 * 根据实例编号查找当前任务节点
	 * @param String procInstId ：实例编号
	 * @return
	 */
	public String nextTaskDefinition(String procInstId,HistoryService historyService,RuntimeService runtimeService,RepositoryService repositoryService){
		//流程标示
		String processDefinitionId = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInstId).singleResult().getProcessDefinitionId();
		
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinitionId);
		//执行实例
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
		//当前实例的执行到哪个节点
		String activitiId = execution.getActivityId();
		//获得当前任务的所有节点
		List<ActivityImpl> activitiList = def.getActivities();
		String id = null;
		for(ActivityImpl activityImpl:activitiList){  
			id = activityImpl.getId(); 
			if(activitiId.equals(id)){

				return (String) activityImpl.getId(); //当前taskID
			}
		}
		return null;
	}
	
	/** 
	 * 流程操作核心类<br> 
	 * 此核心类主要处理：流程部署、启动、获得当前环节、获得下一个环节、判断是否为最后一个环节、查看流程、收回、退回、结束流程等核心操作<br> 
	 *  
	 * @author Saxon 
	 *  
	 */  
	
    /** 
     * 流程部署 
     *  
     * @param path 
     *            上传文件路径 
     * @param flowName 
     *            部署现实别名 
     * @throws Exception 
     */  
		public void deployZIP(ProcessEngine processEngine,RepositoryService repositoryService,String path,String flowName) throws Exception {
		  // 获取仓库服务
		  repositoryService = processEngine.getRepositoryService();
		  // 创建发布配置对象
		  DeploymentBuilder builder = repositoryService.createDeployment();
		  // 获得上传文件的输入流程
		  InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
		  ZipInputStream zipInputStream = new ZipInputStream(in);
		  // 设置发布信息
		  builder
		  .name(flowName)// 添加部署规则的显示别名
		  .addZipInputStream(zipInputStream);
		  // 完成发布
		  builder.deploy();
		 }
	    /** 
	     * 流程启动 
	     *  
	     * @param processName 
	     *            流程别名  
	     * @throws Exception 
	     * 
	     * return 流程ID
	     */  
			public String startProcess(ProcessEngine processEngine,String processName) throws Exception {
				ProcessInstance instance = processEngine.getRuntimeService().startProcessInstanceByKey(processName);  
		        String procId = instance.getId();  
		        return procId;
			 }	
			/**
			 * 根据实例编号查找当前任务节点
			 * @param String procId ：实例编号
			 * @return 
			 */
			public String findNowTask(HistoryService historyService,RepositoryService repositoryService,RuntimeService runtimeService,String procId){
				//流程标示
				String processDefinitionId = historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult().getProcessDefinitionId();
				
				ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinitionId);
				//执行实例
				ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery().processInstanceId(procId).singleResult();
				//当前实例的执行到哪个节点
				String activitiId = execution.getActivityId();
				//获得当前任务的所有节点
				List<ActivityImpl> activitiList = def.getActivities();
				String id = null;
				for(ActivityImpl activityImpl:activitiList){  
					id = activityImpl.getId(); 
					if(activitiId.equals(id)){
						//logger.info("当前任务："+activityImpl.getProperty("name"));
						return (String) activityImpl.getId(); //当前taskID
					}
				}
				return null;
			}
			/**
			* 查询流程当前节点的下一步节点。
			* @param taskId
			* @return
			* @throws Exception
			*/
	public Map<String, String> findNextTask(HistoryService historyService,
			RepositoryService repositoryService, RuntimeService runtimeService,
			String procId) throws Exception {
		Map<String, String> nodeMap = new HashMap<String, String>();

		// 流程标示
		String processDefinitionId = historyService
				.createHistoricProcessInstanceQuery().processInstanceId(procId)
				.singleResult().getProcessDefinitionId();

		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		// 执行实例
		ExecutionEntity execution = (ExecutionEntity) runtimeService
				.createProcessInstanceQuery().processInstanceId(procId)
				.singleResult();
		// 当前实例的执行到哪个节点
		String activitiId = execution.getActivityId();
		// 获得当前任务的所有节点
		List<ActivityImpl> activitiList = def.getActivities();
		String id = null;
		for (ActivityImpl activityImpl : activitiList) {
			id = activityImpl.getId();
			if (activitiId.equals(id)) {
				List<PvmTransition> outTransitions = activityImpl
						.getOutgoingTransitions();// 获取从某个节点出来的所有线路
				for (PvmTransition tr : outTransitions) {
					PvmActivity ac = tr.getDestination(); // 获取线路的终点节点
					String nodeID = (String) ac.getId();
					String nodeName = (String) ac.getProperty("name");
				//	System.out.println("下一步任务任务：" + nodeID + ":" + nodeName);
					nodeMap.put(nodeID,nodeName);
				}
			}
		}

		return nodeMap;
	}
	
	/**
	* 判断是否为最后一个环节。
	* @param taskId
	* @return
	* @throws Exception
	*/
	public boolean endTask(HistoryService historyService,
			RepositoryService repositoryService, RuntimeService runtimeService,
			String procId) throws Exception{
		FlowUtil f = new FlowUtil();
	//	String node = f.findNextTask(historyService, repositoryService, runtimeService, procId, taskId).get(0);
	//	if(node.equals("end")){
	//		return true;
	//	}
		System.out.println("111111111");
		Map<String ,String> nodeMap = f.findNextTask(historyService, repositoryService, runtimeService, procId);
		System.out.println("22222222222");
		Set keys = nodeMap.keySet();
		Iterator it = keys.iterator();
		System.out.println("ddddddddddddddd");
		while(it.hasNext()){
			Object key = it.next();
			Object value = nodeMap.get(key);
		//	System.out.println(key);
		//	System.out.println(value);
			System.out.println("value:"+value);
/*			if(value.equals("End")){
				return true;
			}*/
			if(value.equals("end2")){
				return true;
			}
		}

		return false;
	}
	/**
	* 查看流程。
	* @param taskId
	* @return
	* @throws Exception
	*/
	
	/**
	* 收回。
	* @param taskId
	* @return
	* @throws Exception
	*/
	
	/**
	* 退回。
	* @param taskId
	* @return
	* @throws Exception
	*/
    public void backProcess(String taskId) throws Exception {  
        //ActivityImpl endActivity = findActivitiImpl(taskId, "end");  
       // commitProcess(taskId, null, endActivity.getId());  
    } 

	
	/**
	* 结束流程。
	* @param taskId
	* @return
	* @throws Exception
	*/
    public void endProcess(String taskId) throws Exception {  
        //ActivityImpl endActivity = findActivitiImpl(taskId, "end");  
       // commitProcess(taskId, null, endActivity.getId());  
    } 

    
	/**
	* 待办事宜。
	* @param taskId
	 * @return 
	* @return
	* @throws Exception
	*/
    public List<Task> queryTask(TaskService taskService, String user) throws Exception {  

    	return taskService.createTaskQuery().taskAssignee(user).list();
    	
    	
    } 
    
}
