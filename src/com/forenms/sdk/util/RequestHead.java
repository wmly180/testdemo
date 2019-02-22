package com.forenms.sdk.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RequestHead {

private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String pKey;
	
	public RequestHead(String pKey) {
		this.pKey = pKey;
	}

	public String getpKey() {
		return pKey;
	}

	public void setpKey(String pKey) {
		this.pKey = pKey;
	}

	public  Map<String, String> getHead(){
		String timeZone = dateFormat.format(new Date());
		Map<String, String> maps = new HashMap<>();
		maps.put("pKey", pKey);
		maps.put("timeZone",timeZone);
		maps.put("sign", Md5Utils.MD5Encode(pKey+"\\\\"+timeZone,"utf-8",true));
		return maps;
	}
	
	
}
