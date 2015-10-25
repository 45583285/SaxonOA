package sl.test;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ActivitiController {
	private static GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(); 
	@BeforeClass   
	public static void Init(){       
		ctx.load("classpath:spring-hibernate.xml");   
		ctx.refresh();  
	}
	
	@Test
	public void testActivities(){   
		System.out.println("2222");
		RuntimeService runtimeService = (RuntimeService) this.ctx.getBean("runtimeService");   
		runtimeService.getClass();   
		RepositoryService repositoryService=(RepositoryService) 
				this.ctx.getBean("repositoryService");  
		
		System.out.println("33333");
		}
	
	
	
}