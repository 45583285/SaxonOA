package sl.service.impl;
import javax.annotation.Resource;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.stereotype.Service;
import sl.service.WorkflowServiceI;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowServiceI {
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
	
	/**部署流程定义*/
	@Override
	public void saveNewDeploye(File file, String filename) {
		try {
			//2：将File类型的文件转化成ZipInputStream流
			System.out.println("333333333333");
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
			repositoryService.createDeployment()//创建部署对象
							.name(filename)//添加部署名称
							.addZipInputStream(zipInputStream)//
							.deploy();//完成部署
			List<Deployment> list = findDeploymentList();
			
			for(Deployment i : list){
				System.out.println(i.getId()+i.getName()+i.getTenantId()+i.getDeploymentTime());
			}
			
			
			List<ProcessDefinition> list1 = findProcessDefinitionList();
			
			for(ProcessDefinition j : list1){
				System.out.println(j.getId()+j.getKey()+j.getName());
			}
			
			System.out.println("333333333333");
			
		} catch (Exception e) {
			System.out.println("44444444444");
			e.printStackTrace();
		}
	}
	
	
	/**查询部署对象信息，对应表（act_re_deployment）*/
	@Override
	public List<Deployment> findDeploymentList() {
		List<Deployment> list = repositoryService.createDeploymentQuery()//创建部署对象查询
							.orderByDeploymenTime().asc()//
							.list();
		return list;
	}
	
	
	/**查询流程定义的信息，对应表（act_re_procdef）*/
	@Override
	public List<ProcessDefinition> findProcessDefinitionList() {
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()//创建流程定义查询
							.orderByProcessDefinitionVersion().asc()//
							.list();
		return list;
	}
	
	
	/**使用部署对象ID和资源图片名称，获取图片的输入流*/
	@Override
	public InputStream findImageInputStream(String deploymentId,
			String imageName) {
		return repositoryService.getResourceAsStream(deploymentId, imageName);
	}
	
	/**使用部署对象ID，删除流程定义*/
	@Override
	public void deleteProcessDefinitionByDeploymentId(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}
	
}
