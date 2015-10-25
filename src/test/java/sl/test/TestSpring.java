package sl.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sl.service.UserServiceI;

public class TestSpring {
	
	@Test
	public void test() throws Exception {
		//ApplicationContext ac = new ClassPathXmlApplicationContext(new String[] { "classpath:spring.xml" });
		//UserServiceI userService = (UserServiceI) ac.getBean("userService");
		//userService.test();
		 // diagrams实际路径  
	  String realPath ="D:\\workspace\\activiti-demo\\src\\main\\resources\\diagrams\\";  
		   // 创建 Activiti流程引擎  
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("spring-hibernate.xml").buildProcessEngine(); 
        System.out.println("创建 Activiti流程引擎ok......");
        
        // 取得 Activiti 服务  
        RepositoryService repositoryService = processEngine.getRepositoryService();  
        RuntimeService runtimeService = processEngine.getRuntimeService();  
        repositoryService.createDeployment().addInputStream("MyProcess.bpmn",new FileInputStream(realPath + "\\MyProcess.bpmn"))
        //.addInputStream("DemoProcess.png", new FileInputStream(realPath + "<a href=\"file://\\MyProcess.png\">\\DemoProcess.png</a>"))  
        .deploy(); 
        System.out.println("取得 Activiti 服务ok......");
        
        // 启动流程实例  
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employeeName", "testUser");
        variables.put("numberOfDays", new Integer(4));
        variables.put("vacationMotivation", "I'm really tired!");
        
        
        ProcessInstance instance = processEngine.getRuntimeService().startProcessInstanceByKey("myProcess", variables);  
        String procId = instance.getId();  
        System.out.println("procId:"+ procId);  
		System.out.println("启动流程实例ok......");
		
		   // 获得第一个任务  
        TaskService taskService = processEngine.getTaskService();  
        List<Task> tasks = taskService.createTaskQuery().taskDefinitionKey("scripttask1").list();  
        for (Task task : tasks) {  
            System.out.println("Following task is: taskID -" +task.getId()+" taskName -"+ task.getName());  
            // 认领任务  
            taskService.claim(task.getId(), "testUser");  
        }
        
        System.out.println("获得第一个任务ok......"+tasks.size());
		
        // 查看testUser 现在是否能够获取到该任务  
        tasks = taskService.createTaskQuery().taskAssignee("testUser").list();  
        for (Task task : tasks) {  
            System.out.println("Task for testUser: " + task.getName());  
            // 完成任务  
            taskService.complete(task.getId());  
        }  
        System.out.println("Number of tasks for testUser: " + taskService.createTaskQuery().taskAssignee("testUser").count());  
        
        
        
        // 获取并认领第二个任务  
        tasks = taskService.createTaskQuery().taskDefinitionKey("secondTask").list();  
        for (Task task : tasks) {  
            System.out.println("Following task is : taskID -" +task.getId()+" taskName -"+ task.getName());  
            taskService.claim(task.getId(), "testUser");  
        }  
          
        //完成第二个任务结束结束流程  
        for (Task task : tasks) {  
            taskService.complete(task.getId());  
        }  
          
        // 核实流程是否结束  
        HistoryService historyService = processEngine.getHistoryService();  
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();  
        System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());  
	}
	

}
