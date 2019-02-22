package com.forenms.sdk.util;

import  java.io.BufferedReader;  
import  java.io.InputStreamReader;  
import  java.io.OutputStream;  
import  java.net.HttpURLConnection;  
import  java.net.URL;  
import  java.util.Map;

/**
 * 
* @Title:UrlConnectionUtils
* @Description: 请求工具类封装
* @author chenshangming
* @date 2016年12月9日上午10:41:43
 */
public   class  UrlConnectionUtils {  
    //POST  
    private   static   final  String SERVLET_POST =  "POST"  ;  
    //GET
    private   static   final  String SERVLET_GET =  "GET"  ;  
    /**
     *   
    * @Title: prepareParam    
    * @Description:入参封装
    * @param paramMap
    * @return 
    * @return String
     */
    private   static  String prepareParam(Map<String,Object> paramMap){  
        StringBuffer sb = new  StringBuffer();  
        if (paramMap==null||paramMap.isEmpty()){  
            return   ""  ;  
        }else {  
            for (String key: paramMap.keySet()){  
                String value = (String)paramMap.get(key);  
                if (sb.length()< 1 ){  
                    sb.append(key).append("=" ).append(value);  
                }else {  
                    sb.append("&" ).append(key).append( "=" ).append(value);  
                }  
            }  
            return  sb.toString();  
        }  
    }  
    /**
     *   
    * @Title: doPost    
    * @Description:POST请求
    * @param urlStr
    * @param paramMap
    * @return
    * @throws Exception 
    * @return String
     */
    public  static  String  doPost(String urlStr,Map<String,Object> paramMap ){  
    	try {
    		String paramStr = prepareParam(paramMap);
    		URL url = new  URL(urlStr); 
    		if("https".equalsIgnoreCase(url.getProtocol())){
    			   SslUtils.ignoreSsl();
    		}
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            conn.setRequestMethod(SERVLET_POST);    
            conn.setDoInput(true );  
            conn.setDoOutput(true );  
            OutputStream os = conn.getOutputStream();       
            os.write(paramStr.toString().getBytes("utf-8" ));       
            os.close();           
              
            BufferedReader br = new  BufferedReader( new InputStreamReader(conn.getInputStream()));  
            String line ;  
            String result ="" ;  
            while ( (line =br.readLine()) !=  null  ){  
                result+=line;  
            }  
            if(br!=null){
            	br.close();  
            }
            return new String(result.getBytes(), "utf-8");
		} catch (Exception e) {
			return e.getMessage();
		}
        
          
    }  
    /**
     *   
    * @Title: doGet    
    * @Description:GET请求
    * @param urlStr
    * @param paramMap
    * @return
    * @throws Exception 
    * @return String
     */
    public  static   String   doGet(String urlStr,Map<String,Object> paramMap ){  
    	try {
    		 String paramStr = prepareParam(paramMap);  
    	        if (paramStr ==  null  || paramStr.trim().length()< 1 ){  
    	              
    	        }else {  
    	            urlStr +="?" +paramStr;  
    	        }  
    	        URL url = new  URL(urlStr);  
    	        if("https".equalsIgnoreCase(url.getProtocol())){
     			   SslUtils.ignoreSsl();
    	        }
    	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
    	        conn.setRequestMethod(SERVLET_GET);  
    	        conn.setRequestProperty("Content-Type" , "text/html; charset=UTF-8" );  
    	        conn.connect();  
    	        BufferedReader br = new  BufferedReader( new InputStreamReader(conn.getInputStream()));  
    	        String line ;  
    	        String result ="" ;  
    	        while ( (line =br.readLine()) !=  null  ){  
    	            result += line;  
    	        }  
    	        if(br!=null){
    	        	br.close();  
    	        }
    	        return new String(result.getBytes(), "utf-8");
		} catch (Exception e) {
			  return e.getMessage();
		}
       
    }  
}  