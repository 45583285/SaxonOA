package sl.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Test;

public class SuBaoTest {
	
	
	
	
	
	/*
	 * 订单插入
	 */
	@Test
	
	public void dingdan(){
		Native2AsciiUtils  s = new Native2AsciiUtils();
		
		try {
			String urls="https://thirdif.ditiego.net/ISCM/Order/OrderDistribution?OrderCode=359599755826&Status=400&Procedure={\"track\":{\"billcode\":\"359599755826\",\"detail\":[{\"time\":\"2015-09-1416:45:54\",\"scantype\":\"收件\",\"memo\":\"北仑(北仑中通)已收件进入公司分捡\"},{\"time\":\"2015-09-1416:47:59\",\"scantype\":\"签收\",\"memo\":\"北仑大矸六部派件已签签收人是拍照签收\"}]}}&ExpressName=中通快递";
			//urls = s.native2Ascii(urls);
			//	URL url = new URL(URLEncoder.encode(URLEncoder.encode(urls,"gbk"),"gbk"));
			URL url = new URL(urls);
			URLConnection con = url.openConnection();
			
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			String type = con.guessContentTypeFromStream(urlStream);
			String charSet = null;
			if (type == null)
				type = con.getContentType();
/*			if (type == null || type.trim().length() == 0
					|| type.trim().indexOf("text/html") < 0)
				return;*/
/*			if (type.indexOf("charset=") > 0)
				charSet = type.substring(type.indexOf("charset=") + 8);
			
			*/
			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			
			String content = new String(b, 0, numRead);

			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					// String newContent = new String(b, 0, numRead);
					String newContent = new String(b, 0, numRead, charSet);
					content += newContent;
				}
			}
			System.out.println("content1:" + content);
			urlStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getEncoding(String str) {      
	       String encode = "GB2312";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s = encode;      
	              return s;      
	           }      
	       } catch (Exception exception) {      
	       }      
	       encode = "ISO-8859-1";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s1 = encode;      
	              return s1;      
	           }      
	       } catch (Exception exception1) {      
	       }      
	       encode = "UTF-8";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s2 = encode;      
	              return s2;      
	           }      
	       } catch (Exception exception2) {      
	       }      
	       encode = "GBK";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s3 = encode;      
	              return s3;      
	           }      
	       } catch (Exception exception3) {      
	       }      
	      return "";      
	   }  
	/*@Test
	public void kuaidi100() {
		try {
			URL url = new URL(
					"http://www.kuaidiapi.cn/rest/?uid=42300&key=2a66c6c2a02b4d49aa5b6d3642af94e8&order=220407580416&id=shentong");
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			String type = con.guessContentTypeFromStream(urlStream);
			String charSet = null;
			if (type == null)
				type = con.getContentType();

			if (type == null || type.trim().length() == 0
					|| type.trim().indexOf("text/html") < 0)
				return;

			if (type.indexOf("charset=") > 0)
				charSet = type.substring(type.indexOf("charset=") + 8);

			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			String content = new String(b, 0, numRead);
			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					// String newContent = new String(b, 0, numRead);
					String newContent = new String(b, 0, numRead, charSet);
					content += newContent;
				}
			}
			System.out.println("content:" + content);
			urlStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}*/
	
	
	
	
	
	
}
