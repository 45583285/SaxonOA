package sl.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;


/*import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONObject;*/

public class JsonToXML {  
  
 private static StringBuilder result = new StringBuilder(  
   "<?xml version=\"1.0\" encoding=\"utf-8\"?>");  
 private static String[] spacialChars = { "&", "<", ">", "\"", "'" };  
 private static String[] validChars = { "&", "<", ">", "\"","'" };  
  
   
 /** 
  * For specail char there are bugs using this way to format xml string 
  * Don't suggest to use this method,just for debugging 
  * @param input 
  * @param indent 
  * @return 
  */  
 public static String prettyFormat(String input, int indent) {  
  try {  
   Source xmlInput = new StreamSource(new StringReader(input));  
   StringWriter stringWriter = new StringWriter();  
   StreamResult xmlOutput = new StreamResult(stringWriter);  
   TransformerFactory transformerFactory = TransformerFactory  
     .newInstance();  
   transformerFactory.setAttribute("indent-number", indent);  
   Transformer transformer = transformerFactory.newTransformer();  
   transformer.setOutputProperty(OutputKeys.INDENT, "yes");  
   transformer.transform(xmlInput, xmlOutput);  
   return xmlOutput.getWriter().toString();  
  } catch (Exception e) {  
   throw new RuntimeException(e);   
  }  
 }  
  
 public static String toXml(String jsonString){  
  try {  
   JSONObject jsonObject = new JSONObject(jsonString);  
   toXml(jsonObject);  
  } catch (Exception e) {  
   throw new RuntimeException(e);   
  }  
    
  return result.toString();  
 }  
   
 private static void toXml(JSONObject json) throws Exception {  
  
  Iterator<?> keyIter = json.keys();  
  while (keyIter.hasNext()) {  
   String key = (String) keyIter.next();  
   Object jsonValue = json.get(key);  
   if (jsonValue instanceof JSONArray) {  
    JSONArray arrayValue = (JSONArray) jsonValue;  
    for (int i = 0; i < arrayValue.length(); i++) {  
     appendFlagBegin(key);  
     Object arrItem = arrayValue.get(i);  
     if (arrItem instanceof JSONObject) {  
      toXml((JSONObject) arrItem);  
     } else if (arrItem instanceof JSONArray) {  
      String arrItemStr = "{" + key + ":"  
        + ((JSONArray) arrItem).toString() + "}";  
      toXml(new JSONObject(arrItemStr));  
     } else {  
      appendText(arrItem.toString());  
     }  
     appendFlagEnd(key);  
    }  
   } else {  
    appendFlagBegin(key);  
    if (jsonValue instanceof JSONObject) {  
     toXml((JSONObject) jsonValue);  
    } else {  
     appendText(jsonValue.toString());  
    }  
    appendFlagEnd(key);  
   }  
  }  
 }  
  
 private static String replaceSpecialChar(String s) {  
  for (int i = 0; i < spacialChars.length; i++) {  
   s = s.replaceAll(spacialChars[i], validChars[i]);  
  }  
  return s;  
 }  
  
 private static void appendText(String s) {  
  result.append(replaceSpecialChar(s));  
 }  
  
 private static void appendFlagBegin(String str) {  
  result.append("<" + str + ">");  
 }  
  
 private static void appendFlagEnd(String str) {  
  result.append("</" + str + ">");  
 }  
  
}  