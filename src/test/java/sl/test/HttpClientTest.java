package sl.test;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;


import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.ConnectionPoolTimeoutException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientTest {

	public String requestSystem(String loginUrl,String username,String password,String jsondata,String url,String encode) {
		Logger logger = Logger.getLogger(this.getClass());
	    CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = null;
	    String returnMsg = "";
	    JSONObject jsonObject = new JSONObject();
	    try {
	      String set_cookie="";
	      HttpResponse rese = null;
	      if(loginUrl!=null && loginUrl.trim().length()>0){
	      client = new DefaultHttpClient();
	      //登录-----------------------------------------------------------------------------------
	      httpPost = new HttpPost(loginUrl);
	      List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
	      nvps.add(new BasicNameValuePair(username.split(",")[0], username.split(",")[1]));
	      nvps.add(new BasicNameValuePair(password.split(",")[0], password.split(",")[1]));
	      httpPost.setEntity(new UrlEncodedFormEntity(nvps, encode));
	      rese = client.execute(httpPost);
	     
	      //打印Cookie信息
	      List<org.apache.http.cookie.Cookie> list =  ((AbstractHttpClient) client).getCookieStore().getCookies();
	      for(int i=0;i<list.size();i++) {
	    	  org.apache.http.impl.cookie.BasicClientCookie c = (BasicClientCookie) list.get(i);
	          System.out.println("Cookie:" + c.getName()+","+c.getValue());
	      }
	      
	      set_cookie = rese.getFirstHeader("Set-Cookie").getValue();  
	      System.out.println("--------------------------------------------");
	      System.out.println(set_cookie.substring(0,set_cookie.indexOf(";")));
	      }
	      //人员信息提交---------------------------------------------------------------------------
	      client = new DefaultHttpClient();
	      httpPost = new HttpPost(url);
	      List<BasicNameValuePair> nvpsData = new ArrayList<BasicNameValuePair>();
	      nvpsData.add(new BasicNameValuePair("FIELD_JSON", "["+jsondata+"]"));
	      httpPost.setEntity(new UrlEncodedFormEntity(nvpsData, encode));
	      String mycookie = "";
	      if(set_cookie.trim().length()>0 && set_cookie.indexOf(";")>-1){
	    	  mycookie = set_cookie.substring(0,set_cookie.indexOf(";"));
	      }
	      httpPost.setHeader("Cookie", mycookie);
	      rese = client.execute(httpPost);
	      
	      //打印返回信息
	      HttpEntity entity = rese.getEntity();
	      returnMsg = EntityUtils.toString(entity);
	      System.out.println("returnMsg:" + returnMsg);
	      
	       
//	      if(rese.getStatusLine().getStatusCode() == 302 || rese.getStatusLine().getStatusCode() == 200){
//	        //打印Header信息
//	        Header[] heads = rese.getAllHeaders();
//	        for(Header head :heads) {
//	          System.out.println("heads=" + head.getName()+":" +head.getValue());
//	        }
//	        String locationUrl="";
//	        if(rese.getLastHeader("Location")!=null)locationUrl = rese.getLastHeader("Location").getValue();
//	        System.out.println("locationUrl:" +locationUrl);
//	      }
	    } catch (ConnectException e) {
	      jsonObject.putAll(Status.getStatus(Status.INSERT_ERR));
	      returnMsg = jsonObject.toString();
		  logger.error("连接被拒绝！", e);
	    } catch (ConnectionPoolTimeoutException e) {
	      jsonObject.putAll(Status.getStatus(Status.INSERT_ERR));
		  returnMsg = jsonObject.toString();
	      logger.error("获取连接超时！", e);
	    } catch (ConnectTimeoutException e) {
	      jsonObject.putAll(Status.getStatus(Status.INSERT_ERR));
		  returnMsg = jsonObject.toString();
	      logger.error("与目标服务连接超时！", e);
	    } catch (SocketTimeoutException e) {
	      jsonObject.putAll(Status.getStatus(Status.INSERT_ERR));
		  returnMsg = jsonObject.toString();
	      logger.error("等待目标服务返回信息超时！", e);
	    } catch (Exception e) {
	      jsonObject.putAll(Status.getStatus(Status.INSERT_ERR));
		  returnMsg = jsonObject.toString();
		  logger.error("操作异常！", e);
	    } finally {
	      // 关闭连接， 释放资源
	      if (httpPost != null)
	        httpPost.abort();
	      // 关闭连接管理器
	      if (client != null)
	        client.getConnectionManager().shutdown();
	    }
	    return returnMsg;

	  }

	/**
	 * @param args
	 */
	public static void main(String[] args){
		HttpClientTest demo1 = new HttpClientTest();
		//post登录
		String loginurl = "";
		String username="";
		String userpass="";
		String encode = "gbk";        
		//调用http接口
		//String url = "http://121.40.250.221:8080/ybpt/web/ybmobile/MinShengMobile_addBank.action";
		String url = "http://192.168.0.222:8080/ybptwx/web/ybmobile/MinShengMobile_addBank.action";
		String jsondata = "{\"unitid\":\"F1BCCE0AD206883E5F22649445754C36\",\"ordernumber\":\"51155\",\"name\":\"徐222峰\",\"phonenumber\":\"987676555\",\"address\":\"浙江省宁波市宁海县上东国际7号楼1305室\",\"quantity\":\"3\",\"goods\":\"宁波地铁10月计次月票\",\"express\":\"SF\",\"oddnumbers\":\"\",\"type\":\"1\"}";
		//预定
     //   url = "http://121.40.250.221:8080/ybpt/web/ybmobile/WebEquipInfo_reWebEquipInfos.action";
     //   jsondata = "{\"unitid\":\"F1BCCE0AD206883E5F22649445754C36\"}";
        //取消预定
     //   url = "http://121.40.250.221:8080/ybpt/web/ybmobile/WebEquipInfo_orderorcancelbox.action";
     //   jsondata = "{\"unitid\":\"F1BCCE0AD206883E5F22649445754C36\",\"eid\":\"0E309981-C684-A6D2-68E2-7EE490DDD9A5\",\"boxcondition\":\"2\",\"type\":\"1\",\"ordernum\":\"12345667711117\"}";
	//微信发送
	//	url = "http://192.168.0.222:8080/ybpt/web/ybmobile/HjWx_sendHjWx.action";
	//	jsondata = "{\"unitid\":\"BE6486265FB56B5ABB3CD48B29B5D7B2\",\"mobilePhone\":\"18758347377\",\"takePosition\":\"宁职院服务点\",\"inTime\":\"2015年10月25日\",\"expressCompany\":\"圆通快递\",\"expressNo\":\"36542155148571642152145\",\"takeNumber\":\"764365^\"}";
	//subao设备接口
		url = "http://sbaotest.tunnel.mobi/ybpt/web/ybmobile/WebEquipInfo_reWebEquipInfos.action";
		jsondata = "{\"unitid\":\"F1BCCE0AD206883E5F22649445754C36\"}";

		//for(int i=1;i<100;i++){
		//	jsondata = "{\"unitid\":\"F1BCCE0AD206883E5F22649445754C36\",\"ordernumber\":\"2016092110400220331000"+(i)+"\",\"name\":\"许齐松"+i+"\",\"phonenumber\":\"15827756099\",\"address\":\"浙江省宁波市海曙区科创大厦118号世纪广场 AB座12室\",\"quantity\":\""+i+"\",\"goods\":\"10月月票\",\"express\":\"\",\"oddnumbers\":\"\",\"type\":\"0\"}";
            demo1.requestSystem(loginurl,username,userpass,jsondata,url,encode);
		//}


		
		
/*		HttpClientTest demo1 = new HttpClientTest();
		//post登录
		String loginurl = "";
		String username="";
		String userpass="";
		//调用http接口
		String url = "http://testpartner.zto.cn/client/interface.php";
		String jsondata = "";
		String encode = "gbk";                                          
		demo1.requestSystem(loginurl,username,userpass,jsondata,url,encode);*/
		
    } 

}
