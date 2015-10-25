package sl.service;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;


public interface WorkflowServiceI {

	void saveNewDeploye(File file, String filename);

	List<Deployment> findDeploymentList();

	List<ProcessDefinition> findProcessDefinitionList();

	InputStream findImageInputStream(String deploymentId, String imageName);

	void deleteProcessDefinitionByDeploymentId(String deploymentId);



}
