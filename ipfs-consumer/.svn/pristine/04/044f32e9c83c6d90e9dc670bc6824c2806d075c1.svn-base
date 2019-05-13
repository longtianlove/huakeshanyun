package com.stys.ipfs.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class CoinUtil {

	   public static String readData() throws Exception {
	        //创建StringBuffer类型的变量json，用于存放拼装好的json数据
	        //StringBuffer json = new StringBuffer("");
	        String json = "";
	        //url中不可以出现空格，空格全部用%20替换
	        String url = "http://api.coindog.com/api/v1/currency/ranks"; 
	        URL urls = new URL(url);  
	        java.net.HttpURLConnection conn = (java.net.HttpURLConnection)urls.openConnection();  
	        //因为服务器的安全设置不接受Java程序作为客户端访问，解决方案是设置客户端的User Agent
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        //只可以设置为GET方式，不可以使用POST方式
	        //conn.setRequestMethod("POST");
	        conn.setRequestMethod("GET");
	        //设置编码格式为UTF-8
	        conn.setRequestProperty("contentType", "UTF-8");
	//得到输入流
	        InputStream inputStream = conn.getInputStream(); 
	        //从输入流中获取数据（一定要设置编码格式，不然在服务器端接收到的数据可能乱码）
	        BufferedReader bf=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
	        String line=null;
	        while((line=bf.readLine())!=null){//一行一行的读
	        	
	            json = json + line;
	            
	        }
	        if(inputStream!=null){  
	            inputStream.close();  
	        }
	        String[] strs = json.split("\\\\");
	        String str = "";
	        StringBuffer jsons = new StringBuffer("");
	        for (int i = 0; i < strs.length; i++) {
	            str = strs[i];
	            jsons = jsons.append(str);
	        }
	        return json;
	    }
	    
}
