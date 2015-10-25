package sl.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sl.dao.ProcessDaoI;
import sl.model.SysProcess;
import sl.pageModel.DataGrid;
import sl.pageModel.Process;
import sl.service.ProcessServiceI;
import sl.util.FileUtil;
import sl.util.JsonToXML;
import sl.util.ProjectConstants;
import sl.util.WorkflowPath;
import sl.util.WorkflowProcess;
import sl.util.ZipTools;
@Service("processService")
public class ProcessServiceImpl implements ProcessServiceI {
	/**
	 * Logger for this class
	 */
	@Resource
	private ProcessEngine processEngine;
	private List<WorkflowProcess> flowList = new ArrayList<WorkflowProcess>();
	private static final Logger logger = Logger
			.getLogger(ProcessServiceImpl.class);
	private ProcessDaoI processDao;
	public ProcessDaoI getProcessDao() {
		return processDao;
	}
	@Autowired
	public void setProcessDao(ProcessDaoI processDao) {
		this.processDao = processDao;
	}
	@Override
	public void save(Process process) {
		SysProcess u = new SysProcess();
		BeanUtils.copyProperties(process, u);
		System.out.println(":::::"+process.getFile());
		u.setUuid(UUID.randomUUID().toString());
		u.setCreateTime(new Date().toString());
		RepositoryService repositoryService = processEngine.getRepositoryService();  
		Deployment builder =null;
		try {
			
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(process.getFile()));
			builder = repositoryService.createDeployment()//创建部署对象
			.name(u.getUuid())//添加部署名称
			.addZipInputStream(zipInputStream)//
			.deploy();//完成部署
			 System.out.println("取得 Activiti 服务ok......");		 
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		u.setProcessId(builder.getId());
		processDao.save(u);
		
		
		
		
		
/*		
		 
	     try {
			repositoryService.createDeployment()
			 .addInputStream("MyProcess.bpmn",new FileInputStream("F:\\tomcat6\\webapps\\SaxonOA\\leave.bpmn")).deploy();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
	       

	}
	@Override
	public DataGrid datagrid(Process process) {
		DataGrid dg = new DataGrid();
		String hql = "from SysProcess t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql = addWhere(process, hql, params);
		String totalHql = "select count(*) " + hql;
		hql = addOrder(process, hql);
		List<SysProcess> l = processDao.find(hql, params, process.getPage(), process.getRows());
		List<Process> nl = new ArrayList<Process>();
		changeModel(l, nl);
		dg.setTotal(processDao.count(totalHql, params));
		dg.setRows(nl);
		return dg;
	}

	private void changeModel(List<SysProcess> l, List<Process> nl) {
		if (l != null && l.size() > 0) {
			for (SysProcess t : l) {
				Process u = new Process();
				BeanUtils.copyProperties(t, u);
				nl.add(u);
			}
		}
	}

	private String addOrder(Process process, String hql) {
		if (process.getSort() != null) {
			hql += " order by " + process.getSort() + "+0 " + process.getOrder();
		}
		return hql;
	}

	private String addWhere(Process process, String hql, Map<String, Object> params) {
		if (process.getUuid() != null && !process.getUuid().trim().equals("")) {
		//	hql += " where t.name like :name";
		//	params.put("name", "%%" + process.getName().trim() + "%%");
		}
		return hql;
	}
	@Override
	public List<Process> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void remove(String ids) {
		// TODO Auto-generated method stub
		String[] nids = ids.split(",");
		String hql = "delete SysProcess t where t.uuid in (";
		for (int i = 0; i < nids.length; i++) {
			if (i > 0) {
				hql += ",";
			}
			hql += "'" + nids[i] + "'";
		}
		hql += ")";
		
		try{
			SysProcess m = processDao.get("from SysProcess t where t.uuid='"+ids+"'");
			RepositoryService repositoryService = processEngine.getRepositoryService();  
			repositoryService.deleteDeployment(m.getProcessId(), true);
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		processDao.executeHql(hql);
		
		
	}
	@Override
	public List<Process> treeGrid(Process o) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Process getById(String uuid) {
		Process process = new Process();
		SysProcess m = processDao.get("from SysProcess t where t.uuid='"+uuid+"'");
		BeanUtils.copyProperties(m, process);
		
		
		
		RepositoryService repositoryService = processEngine.getRepositoryService();  
		  // 从仓库中找需要展示的文件
		  String deploymentId = m.getProcessId();
		  System.out.println(deploymentId);
		  List<String> names = repositoryService.getDeploymentResourceNames(deploymentId);
		  String imageName = null;
		  for (String name : names) {
		   if(name.indexOf(".png")>=0){
		    imageName = name;
		   }
		  }
		  if(imageName!=null){
		     System.out.println(imageName);
		   File f = new File("F:/tomcat6/webapps/SaxonOA/admin/process/"+ deploymentId+".png");
		   // 通过部署ID和文件名称得到文件的输入流
		   InputStream in =  repositoryService.getResourceAsStream(deploymentId, imageName);
		  try {
			FileUtils.copyInputStreamToFile(in, f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  }
			return process;
	}
	@Override
	public void update(Process o) {
		// TODO Auto-generated method stub
		
	//	JsonToXML jx = new JsonToXML();
	//	logger.info(jx.toXml(o.getProcessInfo()));
		
		
		Map map = new HashMap();

		JSONObject json = JSONObject.fromObject(o.getProcessInfo());
		XMLSerializer serializer = new XMLSerializer();
		serializer.setTypeHintsEnabled(false);
		serializer.setRootName("root");
		String xml = JsonToXML.prettyFormat(serializer.write(json), 4);
		System.out.println(xml);
		Document document;
		try {
			document = DocumentHelper.parseText(xml);
			Element roots = document.getRootElement();
			List<WorkflowProcess> states = new ArrayList<WorkflowProcess>();

			HashMap<String, WorkflowProcess> mapState = new HashMap<String, WorkflowProcess>();
			WorkflowProcess workflow = new WorkflowProcess();
			HashMap<String, List<WorkflowPath>> mapPaths = new HashMap<String, List<WorkflowPath>>();
			for (Iterator i = roots.elementIterator(); i.hasNext();) {
				Element node = (Element) i.next();
				if (node.getName().equals("paths")) {
					// 步骤
					for (Iterator j = node.elementIterator(); j.hasNext();) {
						Element rect = (Element) j.next();
						WorkflowPath path = new WorkflowPath();
						path.setName(rect.getText());
						String location_x = "", location_y = "", loaction_dot_x = "", location_dot_y = "";
						for (Iterator x = rect.elementIterator(); x.hasNext();) {
							Element subnode = (Element) x.next();
							if (subnode.getName().equals("from")) {
								path.setFrom(subnode.getText());
							} else if (subnode.getName().equals("to")) {
								path.setTo(subnode.getText());
							} else if (subnode.getName().equals("dots")) {
								for (Iterator u = subnode.elementIterator(); u.hasNext();) {
									Element subsubNode = (Element) u.next();
									if (subsubNode.getName().equals("e")) {
										for (Iterator c = subsubNode.elementIterator(); c.hasNext();) {
											Element lastNode = (Element) c.next();
											if (lastNode.getName().equals("x")) {
												loaction_dot_x = lastNode.getText();
											} else if (lastNode.getName().equals("y")) {
												location_dot_y = lastNode.getText();
											}
										}
										break;
									}
								}
							} else if (subnode.getName().equals("textPos")) {

								for (Iterator u = subnode.elementIterator(); u.hasNext();) {
									Element subsubNode = (Element) u.next();
									if (subsubNode.getName().equals("x")) {
										location_x = subsubNode.getText();
									} else if (subsubNode.getName().equals("y")) {
										location_y = subsubNode.getText();
									}
								}
								String g = location_x + "," + location_y;
								if (!location_dot_y.equals("")) {
									g = loaction_dot_x + "," + location_dot_y + ":" + location_x + "," + location_y;
								}
								path.setG(g);
							} else if (subnode.getName().equals("text")) {
								for (Iterator u = subnode.elementIterator(); u.hasNext();) {
									Element subsubNode = (Element) u.next();
									if (subsubNode.getName().equals("text")) {
										path.setName(subsubNode.getText());
										break;
									}
								}
							}
						}
						
						if (mapPaths.containsKey(path.getFrom())) {
							mapPaths.get(path.getFrom()).add(path);
						} else {
							List<WorkflowPath> pathList = new ArrayList<WorkflowPath>();
							pathList.add(path);
							mapPaths.put(path.getFrom(), pathList);
						}
					}
				} else if (node.getName().equals("states")) {
					// 节点
					for (Iterator j = node.elementIterator(); j.hasNext();) {
						Element rect = (Element) j.next();
						WorkflowProcess state = new WorkflowProcess();
						state.setProcessName(rect.getName());
						for (Iterator x = rect.elementIterator(); x.hasNext();) {
							Element subnode = (Element) x.next();
							if (subnode.getName().equals("type")) {
								state.setType(subnode.getText());
							} else if (subnode.getName().equals("text")) {
								for (Iterator u = subnode.elementIterator(); u.hasNext();) {
									Element subsubNode = (Element) u.next();
									if (subsubNode.getName().equals("text")) {
										state.setName(subsubNode.getText());
										break;
									}
								}
							} else if (subnode.getName().equals("attr")) {
								String location_x = "", location_y = "", width = "", height = "", g = "";
								for (Iterator u = subnode.elementIterator(); u.hasNext();) {
									Element subsubNode = (Element) u.next();
									if (subsubNode.getName().equals("x")) {
										location_x = subsubNode.getText();
									} else if (subsubNode.getName().equals("y")) {
										location_y = subsubNode.getText();
									} else if (subsubNode.getName().equals("width")) {
										width = subsubNode.getText();
									} else if (subsubNode.getName().equals("height")) {
										height = subsubNode.getText();
									}
								}
								if (!width.equals("")) {
									g = location_x + "," + location_y + "," + width + "," + height;
								} else {
									g = location_x + "," + location_y;
								}
								state.setG(g);
							}else if (subnode.getName().equals("props")) {
								String ids = "";
								for (Iterator u = subnode.elementIterator(); u.hasNext();) {
									Element subsubNode = (Element) u.next();
									if (subsubNode.getName().equals("ids")) {
										
										for (Iterator uu = subsubNode.elementIterator(); uu.hasNext();) {
											Element subsubsubNode = (Element) uu.next();
											if (subsubsubNode.getName().equals("value")) {
												ids = subsubsubNode.getText();
											}
										}
									}
								}
								state.setIds(ids);
							}
						} 
						mapState.put(state.getProcessName(), state);
						states.add(state);
					} 
				} else if (node.getName().equals("props")) {
					// 流程信息
					for (Iterator j = node.elementIterator(); j.hasNext();) {
						Element rect = (Element) j.next();
						for (Iterator x = rect.elementIterator(); x.hasNext();) {
							Element subnode = (Element) x.next();
							if (subnode.getName().equals("name")) {
								for (Iterator u = subnode.elementIterator(); u.hasNext();) {
									Element subsubNode = (Element) u.next();
									if (subsubNode.getName().equals("value")) {
										workflow.setName(subsubNode.getText());
										break;
									}
									break;
								}
							}
						}
					}
				}
			}

			for (WorkflowProcess process : states) {
				Iterator iterPath = mapPaths.entrySet().iterator();
				while (iterPath.hasNext()) {
					Entry entryPath = (Entry) iterPath.next();
					String Pathkey = (String) entryPath.getKey();
					List<WorkflowPath> paths = (List<WorkflowPath>) entryPath.getValue();
					if (process.getProcessName().equals(Pathkey)) {
						process.setPaths(paths);
						break;
					}
				}
			}
			map.put("stateList", states);
			map.put("workflow", workflow);
			HashMap<String, String> idNameMap = new HashMap<String, String>();
			for (WorkflowProcess state : states) {
				logger.info("WorkflowProcess:"+state.getProcessName()+":"+state.getIds());
				//idNameMap.put(state.getProcessName(), state.getName());
				idNameMap.put(state.getProcessName(), state.getIds());
				for (WorkflowProcess flow : flowList) {
					if (state.getName().equals(flow.getName())) {
						if (state.getType().equals("decision")) {
							state.setExpr(flow.getExpr());
						} else {
							state.setAssignType(flow.getAssignType());
							state.setAssignee(flow.getAssignee());
							state.setAssignValue(flow.getAssignValue());
							state.setForm(flow.getForm());
						}
					}
				}
			}
			//String zipPath = addWorkflow(states, idNameMap, workflow);
			String xmlPath = addWorkflow(states, idNameMap, workflow);
			if (!xmlPath.equals("")) {
				// 5.发布
				RepositoryService repositoryService = processEngine.getRepositoryService();
			//	File flowFile = new File(zipPath);
			//	InputStream is = new FileInputStream(flowFile);
			//	ZipInputStream zis = new ZipInputStream(is);
				//logger.info(zipPath+":"+flowFile+":"+is+":"+o.getProcessName());
				// 发起流程，仅仅就是预定义任务，即在系统中创建一个流程，这是全局的，与具体的登陆 用户无关。然后，在启动流程时，才与登陆用户关联起来
				//repositoryService.createDeployment().name("test110").addZipInputStream(zis).deploy();
				
			//	logger.info(o.getProcessName()+":"+xmlPath);
/*				repositoryService.createDeployment()
				 .addInputStream(o.getProcessName()+".bpmn",new FileInputStream("D:\\workflow\\f11.jpdl.xml")).deploy();
				*/
				repositoryService.createDeployment()
				 .addInputStream(o.getProcessName()+".bpmn",new FileInputStream("D:\\workflow\\"+o.getProcessName()+".jpdl.xml")).deploy();
			
				
				//zis.close();
			//	is.close();
				//System.out.println("did: " + did);
				System.out.println("发布流程成功<br />");
/*				BizWorkflow flow = new BizWorkflow();
				flow.setWorkflowName(workflow.getName());
				flow.setDeploymentId(Integer.valueOf(did));
				flow.setWorkflowDescribe(workflowData);
				workflowBusinessService.saveWork(flow);*/
			}
			//map.put("f", "s");
			
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		SysProcess d = new SysProcess();
		BeanUtils.copyProperties(o, d);
		processDao.update(d);
		
	}

	
	
	public String JsonToXml(List<WorkflowProcess> states, HashMap<String, String> idNameMap, WorkflowProcess workflow) {
		StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		sb.append("<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">");
		sb.append("<process id=\"").append(workflow.getName()).append("\" name=\"").append(workflow.getName()).append("\" isExecutable=\"true\">");
		for (WorkflowProcess process : states) {
			if (process.getType().equals("start")) {
				sb.append("<startEvent");
			} else if (process.getType().equals("end")){
				sb.append("<endEvent");
			} else if (process.getType().equals("task")){
				sb.append("<userTask");
			}
			
			if (process.getType().equals("start")) {
				sb.append(" id=").append("\"").append(process.getIds()).append("\"");
				sb.append(" name=").append("\"").append(process.getName()).append("\"></startEvent>");
			} else if (process.getType().equals("end")){
				sb.append(" id=").append("\"").append(process.getIds()).append("\"");
				sb.append(" name=").append("\"").append(process.getName()).append("\"></endEvent>");
			} else if (process.getType().equals("task")){
				sb.append(" id=").append("\"").append(process.getIds()).append("\"");
				sb.append(" name=").append("\"").append(process.getName()).append("\"></userTask>");
			}
			if (process.getPaths() != null && process.getPaths().size() > 0) {
				for (WorkflowPath path : process.getPaths()) {
					sb.append("<sequenceFlow");
					sb.append(" id=\"").append(path.getName()+"\"");
					sb.append(" sourceRef=\"").append(idNameMap.get(path.getFrom())+"\"");
					sb.append(" targetRef=\"").append(idNameMap.get(path.getTo())+"\">");
					sb.append("</sequenceFlow>");
					
					logger.info("path.getFrom():"+path.getFrom()+":path.getTo():"+path.getTo());
				}
			}
		}
		
		
		
		
		
		
		
		/*sb.append("<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">");
		
		sb.append("<process name=\"").append(workflow.getName()).append("\" xmlns=\"http://jbpm.org/4.4/jpdl\">");
		for (WorkflowProcess process : states) {
			if (process.getType().equals("start") || process.getType().equals("end")) {
				sb.append("<").append(process.getType());
			} else if (process.getType().equals("decision")) {
				sb.append("<").append(process.getType());
				sb.append(" expr=").append("\"#{").append(process.getExpr()).append("}\"");
				sb.append(" g=").append("\"").append(process.getG()).append("\"");
				sb.append(" name=").append("\"").append(process.getName()).append("\">");
			} else if (process.getType().equals("task")){
				process.setAssignType("0");
				//sb.append("<").append(process.getType()).append(" name=\"").append(process.getName());
				if (process.getAssignType().equals("0")) {
					//角色
					sb.append("<").append(process.getType()).append(" name=\"").append(process.getName());
					sb.append("\" form=").append("\"").append(process.getForm()).append("\"");
					sb.append(" g=\"").append(process.getG()).append("\" >");
					sb.append("<assignment-handler ").append("class=\"").append(ProjectConstants.ASSIGNMENT_HANDLER);
					sb.append("\" >");
					sb.append("<field name=\"").append(ProjectConstants.ASSIGNMENT_HANDLER_FIELD_ROLDIDS).append("\" >");
					sb.append("<string value=\"").append(process.getAssignValue()).append("\"/>");
					sb.append("</field>");
					sb.append("</assignment-handler>");
				} else if (process.getAssignType().equals("1")){
					//指定审批人
					sb.append("<").append(process.getType());
					sb.append(" assignee=").append("\"").append(process.getAssignee()).append("\"");
					sb.append(" form=").append("\"").append(process.getForm()).append("\"");
					sb.append(" g=").append("\"").append(process.getG()).append("\"");
					sb.append(" name=").append("\"").append(process.getName()).append("\">");
				}else if (process.getAssignType().equals("2")){
					//发起人
					sb.append("<").append(process.getType());
					sb.append(" assignee=").append("\"#{").append(ProjectConstants.ASSIGNMENT_PROMOTER).append("}\"");
					sb.append(" form=").append("\"").append(process.getForm()).append("\"");
					sb.append(" g=").append("\"").append(process.getG()).append("\"");
					sb.append(" name=").append("\"").append(process.getName()).append("\">");
				}
			}			
			if (process.getType().equals("end")) {
				sb.append(" g=").append("\"").append(process.getG()).append("\"");
				sb.append(" name=").append("\"").append(process.getName()).append("\"/>");
			} else if (process.getType().equals("start")){
				sb.append(" g=").append("\"").append(process.getG()).append("\"");
				sb.append(" name=").append("\"").append(process.getName()).append("\">");
			}
			if (process.getPaths() != null && process.getPaths().size() > 0) {
				for (WorkflowPath path : process.getPaths()) {
					sb.append("<transition");
					sb.append(" g=\"").append(path.getG()).append("\"");
					if(process.getPaths().size()>1){
						sb.append(" name=").append("\"").append(path.getName()).append("\"");
					}
					sb.append(" to=\"").append(idNameMap.get(path.getTo()));
					sb.append("\"/>");
				}
			}
			if (!process.getType().equals("end")) {
				sb.append("</").append(process.getType()).append(">");
			}
			
			
		}*/
		sb.append("</process>");
		sb.append("</definitions>");
		return sb.toString();
	}
	
	
	public String addWorkflow(List<WorkflowProcess> states, HashMap<String, String> idNameMap, WorkflowProcess workflow)
		    throws Exception {
			// 1.根据对象，拼凑xml文件内容
			String xml = JsonToXml(states, idNameMap, workflow);
			
			// 2.保存xml文件
			String xmlPath = ProjectConstants.workflowFilePath + workflow.getName() + ".jpdl.xml";
			//String imagePath = ProjectConstants.workflowFilePath + workflow.getName() + ".png";
			String zipPath = ProjectConstants.workflowFilePath + workflow.getName() + ".zip";
			if (FileUtil.fileWrite(xml, xmlPath)) {
				// 3.生成png图片
				try {
					File file = new File(xmlPath);
					InputStream is = new FileInputStream(file);
					//JpdlModel jpdlModel = new JpdlModel(is);
					//ImageIO.write(new JpdlModelDrawer().draw(jpdlModel), "png", new File(imagePath));
				} catch (FileNotFoundException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 4.生成zip包
				List<String> fileNameList = new ArrayList<String>();
				fileNameList.add(xmlPath);
			//	fileNameList.add(imagePath);
				try {
					ZipTools.zipFile(ProjectConstants.workflowFilePath, fileNameList, zipPath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//return zipPath;//返回zip路径
				return xmlPath;
			}
			return "";
		}
	
	
}
