package sl.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import junit.framework.TestCase;


import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import sl.util.JsonToXML;


public class test extends TestCase {


	public static void main(String[] args) throws Exception { // TODO
		// Auto-generated
		// method stub

		String s = "{status : 'success'}";
		System.out.println("2222222222");
		String rowidString = "[{\"kl_id\":\"1\",\"kl_title\":\"Test date\",\"kl_content\":\"Test date\",\"kl_type\":\"1\",\"id\":\"1\"},{\"kl_id\":\"2\",\"kl_title\":\"Test\",\"kl_content\":\"Test\",\"kl_type\":\"1\",\"id\":\"2\"}]";
		rowidString = "{root:";
		rowidString = rowidString
		    + "{states:{rect1:{type:'start',text:{text:'开始'}, attr:{ x:451, y:37, width:100, height:50}, props:{text:{value:'开始'},temp1:{value:''},temp2:{value:''}}},rect2:{type:'state',text:{text:'state1'}, attr:{ x:466, y:138, width:100, height:50}, props:{text:{value:'state1'},temp1:{value:''},temp2:{value:''}}},rect4:{type:'fork',text:{text:'分支'}, attr:{ x:480, y:257, width:100, height:50}, props:{text:{value:'分支'},temp1:{value:''},temp2:{value:''}}},rect5:{type:'task',text:{text:'任务1'}, attr:{ x:387, y:361, width:100, height:50}, props:{text:{value:'任务1'},temp1:{value:''},temp2:{value:''}}},rect7:{type:'task',text:{text:'任务2'}, attr:{ x:580, y:375, width:100, height:50}, props:{text:{value:'任务2'},temp1:{value:''},temp2:{value:''}}},rect8:{type:'join',text:{text:'合并'}, attr:{ x:523, y:513, width:100, height:50}, props:{text:{value:'合并'},temp1:{value:''},temp2:{value:''}}},rect9:{type:'end',text:{text:'结束'}, attr:{ x:512, y:710, width:100, height:50}, props:{text:{value:'结束'},temp1:{value:''},temp2:{value:''}}}},paths:{path10:{from:'rect1',to:'rect2', dots:[],text:{text:'TO state1'},textPos:{x:0,y:-10}, props:{text:{value:''}}},path11:{from:'rect2',to:'rect4', dots:[],text:{text:'TO 分支'},textPos:{x:0,y:-10}, props:{text:{value:''}}},path12:{from:'rect4',to:'rect5', dots:[],text:{text:'TO 任务1'},textPos:{x:0,y:-10}, props:{text:{value:''}}},path13:{from:'rect4',to:'rect7', dots:[],text:{text:'TO 任务2'},textPos:{x:0,y:-10}, props:{text:{value:''}}},path14:{from:'rect5',to:'rect8', dots:[],text:{text:'TO 合并11'},textPos:{x:0,y:-10}, props:{text:{value:'TO 合并11'}}},path15:{from:'rect7',to:'rect8', dots:[],text:{text:'TO 合并'},textPos:{x:0,y:-10}, props:{text:{value:''}}},path16:{from:'rect8',to:'rect9', dots:[],text:{text:'TO 结束22'},textPos:{x:0,y:-10}, props:{text:{value:'TO 结束22'}}}},props:{props:{name:{value:'新建流程'},key:{value:''},desc:{value:''}}}}";
		
		rowidString = rowidString + "}";
		JSONObject json = JSONObject.fromObject(rowidString);
		System.out.println(rowidString);
		XMLSerializer serializer=new XMLSerializer();
		System.out.println(rowidString);
		// String xml = JsonToXML.prettyFormat(JsonToXML.toXml(json.toString()), 4);
		 serializer.setTypeHintsEnabled( false );      
		 String xml =JsonToXML.prettyFormat(serializer.write( json ),4); 
	     xml=xml.replace("<o>", "");
	     xml=xml.replace("</o>", "");
		 System.out.println(xml);

		Document document = DocumentHelper.parseText(xml);
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
					for (Iterator x = rect.elementIterator(); x.hasNext();) {
						Element subnode = (Element) x.next();
						if (subnode.getName().equals("from")) {
							path.setFrom(subnode.getText());
						} else if (subnode.getName().equals("to")) {
							path.setTo(subnode.getText());
						} else if (subnode.getName().equals("textPos")) {
							String location_x = "", location_y = "";
							for (Iterator u = subnode.elementIterator(); u.hasNext();) {
								Element subsubNode = (Element) u.next();
								if (subsubNode.getName().equals("x")) {
									location_x = subsubNode.getText();
								} else if (subsubNode.getName().equals("y")) {
									location_y = subsubNode.getText();
								}
							}
							String g = location_x + "," + location_y;
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

		Iterator iter = mapState.entrySet().iterator();
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			String key = (String) entry.getKey();
			WorkflowProcess val = (WorkflowProcess) entry.getValue();
			Iterator iterPath = mapPaths.entrySet().iterator();
			while (iterPath.hasNext()) {
				Entry entryPath = (Entry) iterPath.next();
				String Pathkey = (String) entryPath.getKey();
				List<WorkflowPath> paths = (List<WorkflowPath>) entryPath.getValue();
				if (key.equals(Pathkey)) {
					val.setPaths(paths);
					break;
				}
			}
		}
		System.out.println(mapState.size());
		System.out.println(mapPaths.size());
		System.out.println(workflow.getName());
		System.out.println(mapState.get("value"));
		// try {
		// File file = new File("D:/test.jpdl.xml");
		// InputStream is = new FileInputStream(file);
		// JpdlModel jpdlModel = new JpdlModel(is);
		// ImageIO.write(new JpdlModelDrawer().draw(jpdlModel), "png", new
		// File("D:/test.png"));
		// } catch (FileNotFoundException e) { // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) { // TODO Auto-generated catch block
		// e.printStackTrace();
		//
		// } catch (Exception e) { // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		
		
		StringBuffer sb=new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<process name=\"leave\" xmlns=\"http://jbpm.org/4.4/jpdl\">");
		Iterator list = mapState.entrySet().iterator();
		while (list.hasNext()) {
			Entry entry = (Entry) list.next();
			String key = (String) entry.getKey();
			WorkflowProcess val = (WorkflowProcess) entry.getValue();
			if(val.getType().equals("start")||val.getType().equals("end")){
				sb.append("<").append(val.getType()).append(" ");
			}else{
				sb.append("<").append(val.getType()).append(" ");
				sb.append("assignee=").append("\"").append(val.getAssignee()).append("\"");
				sb.append("form=").append("\"").append("").append("\"");
			}
			sb.append("g=").append("\"").append(val.getG()).append("\"");
			if(val.getType().equals("end")){
				sb.append("name=").append("\"").append(val.getName()).append("\" />");
			}else{
			    sb.append("name=").append("\"").append(val.getName()).append("\" >");
			}
			Iterator iterPath = mapPaths.entrySet().iterator();
			while (iterPath.hasNext()) {
				Entry entryPath = (Entry) iterPath.next();
				String Pathkey = (String) entryPath.getKey();
				List<WorkflowPath> paths = (List<WorkflowPath>) entryPath.getValue();
				if (key.equals(Pathkey)) {
					for(int j=0;j<paths.size();j++){
						if(val.getType().equals("start")||val.getType().equals("task")){
							sb.append("<transition  ").append("to=\"").append(paths.get(j).getTo());
							sb.append("\"  />");
						  }else if(val.getType().equals("decision")){
							  sb.append("<transition  expr=\"").append("").append("\"");
							  sb.append("g=\"").append(paths.get(j).getG()).append("\"");
							  sb.append("name=").append("\"").append(paths.get(j).getName()).append("\"");
							  sb.append("to=\"").append(paths.get(j).getTo());
							  sb.append("\"  />");
						  }else{
							  sb.append("<transition  ");
							  sb.append("g=\"").append(paths.get(j).getG()).append("\"");
							  sb.append("name=").append("\"").append(paths.get(j).getName()).append("\"");
							  sb.append("to=\"").append(paths.get(j).getTo());
							  sb.append("\"  />");
						  }
					}
					sb.append("</").append(val.getType()).append(">");
					break;
				}
				
				
			}
		}
		sb.append("</process>");
		System.out.print(sb.toString());
		
	}
}
