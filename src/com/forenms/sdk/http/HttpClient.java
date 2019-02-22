package com.forenms.sdk.http;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import com.forenms.sdk.util.Result;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.request.RequestCall;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * 
* @Title:HttpClient
* @Description: Http工具类
* @date 2017年1月25日下午4:45:11
 */
public class HttpClient {

	private static  HttpClient httpClient  = null;
	private static  OkHttpUtils httpUtils = null;
	//返回值
	Result result = Result.getRetrunResult();
	
	private HttpClient(){
		if(httpUtils==null){
			httpUtils = OkHttpUtils.initClient(initHttpsConfig());
	    }
	}
	/**
	 * 
	* @Title: getInstance    
	* @Description:实例化http客户端
	* @param httpMethod
	* @return 
	* @return HttpClient
	 */
	public static HttpClient getInstance(){
		    if(httpClient==null){
		    	httpClient = new HttpClient();
		    }
		    return httpClient;
	}
	
	/**
	 * 
	* @Title: initHttpsConfig    
	* @Description:https配置
	* @return 
	* @return OkHttpClient
	 */
	public static OkHttpClient initHttpsConfig(){
		 HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
		 OkHttpClient okHttpClient = new OkHttpClient.Builder()
	                .connectTimeout(1500L, TimeUnit.MILLISECONDS)
	                .readTimeout(1500L, TimeUnit.MILLISECONDS)
	                .hostnameVerifier(new HostnameVerifier()
	                {
	                    @Override
	                    public boolean verify(String hostname, SSLSession session)
	                    {
	                        return true;
	                    }
	                })
	                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
	                .build();
		 return okHttpClient;
	}
	/**
	 * 
	* @Title: reqGet    
	* @Description:Get请求
	* @param url
	* @param paramMap
	* @return 
	* @return String
	 */
	@SuppressWarnings("static-access")
	public Map<String, Object> reqGet(String url,Map<String, String> paramMap){
		 //默认返回参数
		 String res="sdk请求会员中心数据失败: ";
		 GetBuilder builder=httpUtils.get();
		 //入参
		 if(paramMap!=null&&!paramMap.isEmpty()){
		     for (Entry<String, String> entry : paramMap.entrySet()) {
		    	 builder.addParams(entry.getKey(), entry.getValue());
		     }
		 }
		 //绑定请求路径
		 builder.url(url);
		 //绑定请求
		 RequestCall call = builder.build();
		 Response response;
		 try {
			//执行请求 
			response = call.execute();
			if(response.isSuccessful()){
				 //请求成功信息
				 res =response.body().string();
				 return result.ObjectflushData(Result.success, "与会员中心信息对接成功", res);
			 }else{
				 //请求错误信息
				 res+=response.code()+"-"+response.message();
				 return result.Error(res);
			 }
		 } catch (IOException e) {
		   //异常信息	 
		   res+=e.toString();
		   return result.Error(res);
		 }
	}
	/**
	 * 
	* @Title: reqPostParamWithFile    
	* @Description:参数和文件一起上传
	* @param url
	* @param paramMap
	* @param UploadFiles
	* @return 
	* @return String
	 */
	@SuppressWarnings("static-access")
	public Map<String, Object> reqPostParamWithFile(String url,Map<String, String> paramMap,Map<String, File> UploadFilesMap){
		 //默认返回参数
		 String res="sdk请求会员中心数据失败: ";
		 PostFormBuilder builder = httpUtils.post();
		 //添加参数
		 if(paramMap!=null&&!paramMap.isEmpty()){
			 builder.params(paramMap);
		 }  
		 //添加文件
		 if(UploadFilesMap!=null&&!UploadFilesMap.isEmpty()){
			  for (Entry<String, File> entry : UploadFilesMap.entrySet()) {
				  builder.addFile(entry.getKey(), entry.getValue().getName(), entry.getValue());	
			}
		 }
		 //绑定请求路径
		 builder.url(url);
		 //绑定请求
		 RequestCall call = builder.build();
		 Response response;
		 try {
				//执行请求 
				response = call.execute();
				if(response.isSuccessful()){
					 //请求成功信息
					 res =response.body().string();
					 return result.ObjectflushData(Result.success, "与会员中心信息对接成功", res);
				 }else{
					 //请求错误信息
					 res+=response.code()+"-"+response.message();
					 return result.Error(res);
				 }
			 } catch (IOException e) {
			   //异常信息	 
			   res+=e.toString();
			   return result.Error(res);
		 }
	}
}
