package sl.test;

import java.util.HashMap;
import java.util.Map;

public class Status {

	public Status() {
		
	}
	
	public static final int INSERT_OK = 1;
	public static final int UPDATE_OK = 2;
	public static final int INSERT_ERR = 3;
	public static final int UPDATE_ERR = 4;
	public static final int DELETE_OK = 5;
	public static final int DELETE_ERR = 6;
	public static final int MOVE_OK = 7;
	public static final int MOVE_ERR = 8;
	public static final int PASS_OK = 9;
	public static final int PASS_ERR = 10;
	public static final int PASS_OLD_ERR = 12;
	public static final int PASS_NEW_ERR = 13;
	public static final int LOG_OK = 14;
	public static final int LOG_ERR = 15;
	public static final int INSERT_ERR_UNITID = 16;
	public static final int INSERT_ERR_USERID = 17;
	public static final int INSERT_ERR_DEPID = 18;
	public static final int INSERT_ERR_DELETE= 19;
	public static final int INSERT_OK_RETURNID = 11;
	
	
	public static Map<String,Object> getStatus(int status){
		Map<String,Object> m = new HashMap<String,Object>();
		switch(status){
		  case 1:{
			  m.put("NAME", "1");
			  m.put("VALUE", "保存成功!");
			  break;
		  }
		  case 2:{
			  m.put("NAME", "2");
			  m.put("VALUE", "更新成功!");
			  break;
		  }
		  case 3:{
			  m.put("NAME", "-1");
			  m.put("VALUE", "保存失败!");
			  break;
		  }
		  case 4:{
			  m.put("NAME", "-2");
			  m.put("VALUE", "更新失败!");
			  break;
		  }
		  case 5:{
			  m.put("NAME", "3");
			  m.put("VALUE", "删除成功!");
			  break;
		  }
		  case 6:{
			  m.put("NAME", "-3");
			  m.put("VALUE", "删除失败!");
			  break;
		  }
		  case 7:{
			  m.put("NAME", "4");
			  m.put("VALUE", "调动成功!");
			  break;
		  }
		  case 8:{
			  m.put("NAME", "-4");
			  m.put("VALUE", "调动失败!");
			  break;
		  }
		  case 9:{
			  m.put("NAME", "5");
			  m.put("VALUE", "密码修改成功!");
			  break;
		  }
		  case 10:{
			  m.put("NAME", "-5");
			  m.put("VALUE", "密码修改失败!");
			  break;
		  }
		  case 12:{
			  m.put("NAME", "-51");
			  m.put("VALUE", "原密码错误!");
			  break;
		  }
		  case 13:{
			  m.put("NAME", "-52");
			  m.put("VALUE", "两次输入密码不一致!");
			  break;
		  }
		  case 14:{
			  m.put("NAME", "6");
			  m.put("VALUE", "保存日志成功!");
			  break;
		  }
		  case 15:{
			  m.put("NAME", "-6");
			  m.put("VALUE", "保存日志失败!");
			  break;
		  }
		  case 16:{
			  m.put("NAME", "-7");
			  m.put("VALUE", "单位编号重复!");
			  break;
		  }
		  case 17:{
			  m.put("NAME", "-8");
			  m.put("VALUE", "用户账号重复!");
			  break;
		  }
		  case 18:{
			  m.put("NAME", "-9");
			  m.put("VALUE", "角色编码重复!");
			  break;
		  }
		  case 19:{
			  m.put("NAME", "-10");
			  m.put("VALUE", "这条记录已被删除，是否恢复记录!");
			  break;
		  }
		  default:{
			  m.put("NAME", "0");
			  m.put("VALUE", "返回有误!");
			  break;
		  }
		}
		return m;
	}
	
	public static Map<String,Object> getStatusErrorMessage(String message){
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("NAME", "-999");
		m.put("VALUE", "失败,("+message+")!");
		return m;
	}
	
	public static Map<String,Object> getStatusSuccessMessage(String message){
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("NAME", "999");
		m.put("VALUE", "成功,("+message+")!");
		return m;
	}
	
	public static Map<String,Object> getStatus(int status,String uuid){
		Map<String,Object> m = new HashMap<String,Object>();
		switch(status){
		  case 11:{
			  m.put("NAME", "11");
			  m.put("VALUE", "保存成功!");
			  m.put("RETURNUUID", uuid);
			  break;
		  }
		  default:{
			  m.put("NAME", "0");
			  m.put("VALUE", "返回有误!");
			  break;
		  }
		}
		return m;
	}

}
