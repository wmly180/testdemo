package com.forenms.sdk.api;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.forenms.sdk.http.HttpClient;

/**
 * 
* @Title:Api
* @Description: 接口类
* @date 2016年12月9日上午11:06:48
 */
public class Api {
	
	public static final   String errorMsg="请求失败";
    
	//路径
	private String ServerUrl;
	//初始化构造器
    public Api(String ServerUrl) {
    	  this.ServerUrl  = ServerUrl;
	}

    //养老保险查询接口
	private  static final String getQueryEndowmentInsurance = "/api/getInsurance";
	
	private  static final String getElecTronicSealPath = "/api/access/quote/electronicSeal";
	
	/**
	 * 入参转换
	* @Title: inJsonParam    
	* @Description:
	* @return 
	* @return Map<String,Object>
	 */
	public static Map<String, String> inJsonParam(Map<String, String> paramMap){
		Map<String, String> map = new HashMap<>();
		map.put("inJson",JSON.toJSONString(paramMap));
		return map;
	}
	/**
	 * 
	* @Title: getQueryEndowmentInsurance    
	* @param paramMap
	* @return 
	* @return String
	 */
	public String getQueryEndowmentInsurance(Map<String, String> paramMap){
		Map<String, Object> result = HttpClient.getInstance().reqPostParamWithFile(ServerUrl+getQueryEndowmentInsurance, inJsonParam(paramMap),null);
		return  JSON.toJSONString(result);
	}
	
	public String getElecTronicSeal(Map<String, String> paramMap){
		Map<String, Object> result = HttpClient.getInstance().reqPostParamWithFile(ServerUrl+getElecTronicSealPath, inJsonParam(paramMap),null);
		return  JSON.toJSONString(result);
	}
}
