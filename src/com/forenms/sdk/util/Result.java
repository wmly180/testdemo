package com.forenms.sdk.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @Title:RetrunResult
* @Description: 手机app接口中心
* @date 2016年10月15日下午4:40:43
 */
public class Result {
    
	//单例模式
	private static  Result result=null;
	//返回Map
	Map<String,Object> reMap;
	
	//成功
	public static final int success=200;
	//失败
	public static final int error=500;
	
	public String defaultMsg="error:未知错误";
	
	private Result(){
		
	}
	
	public static Result getRetrunResult(){
		  if(result==null){
			  result=new Result();
		  }
		  return result;
	}
	
	/**
	 * 
	* @Title: Objectflush    
	* @Description:返回结果
	* @param code
	* @param msg
	* @param data
	* @return 
	* @return Map<String,Object>
	 */
	public Map<String, Object>  ObjectflushData(int code,String msg,Object data){
		reMap = new HashMap<String,Object>();
		reMap.put("code",code);
		reMap.put("msg", msg==null?defaultMsg:msg);
		reMap.put("data", data);
		return reMap;
	}
	
	
	/**
	 * 
	* @Title: Objectflush    
	* @Description:返回结果
	* @param code
	* @param msg
	* @param data
	* @return 
	* @return Map<String,Object>
	 */
	public Map<String, Object>  Objectflush(int code,String msg){
		reMap = new HashMap<String,Object>();
		reMap.put("code",code);
		reMap.put("msg", msg==null?defaultMsg:msg);
		return reMap;
	}
	
	/**
	 * 
	* @Title: Success    
	* @Description:执行成功
	* @return 
	* @return Map<String,Object>
	 */
	public Map<String, Object>  Success(String msg){
		reMap = new HashMap<String,Object>();
		reMap.put("code",success);
		reMap.put("msg",msg==null?defaultMsg:msg);
		return reMap;
	}
	
	/**
	 * 
	* @Title: Error    
	* @Description:执行失败
	* @return 
	* @return Map<String,Object>
	 */
	public Map<String, Object>  Error(String msg){
		reMap = new HashMap<String,Object>();
		reMap.put("code",error);
		reMap.put("msg",msg==null?defaultMsg:msg);
		return reMap;
	}
}
