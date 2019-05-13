package com.stys.ipfs.util.usdt;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class Ustd {
    //private Logger log = Logger.getLogger("");
    /**
     * USDT查询余额
     * @return
     * */
     public String getBalance(String address){
         JSONObject json = doRequest(Account.METHOD_GET_BALANCE,address,Account.propertyid);
         if(isError(json)){
             System.out.println(json.get("error"));
            //log.error("获取USDT余额:{}",json.get("error"));
            return null;
         }
         return json.getString(Account.RESULT);
     }
    public String newAddress(){
        JSONObject json = doRequest(Account.METHOD_NEW_ADDRESS);
        if(isError(json)){
            System.out.println(json.get("error"));
            //log.error("获取USDT地址失败:{}",json.get("error"));
            return "";
        }
        return json.getString(Account.RESULT);

    }
    public String getAddresses(){
        JSONObject json = doRequest("getaddressesbyaccount","");
        if(isError(json)){
            System.out.println(json.get("error"));
            //log.error("获取USDT地址失败:{}",json.get("error"));
            return "";
        }
        return json.getString(Account.RESULT);
    }
    /**
     *
     * @param json
     * @return
     */
    private boolean isError(JSONObject json){
        if( json == null || (StringUtils.isNotEmpty(json.getString("error")) && json.get("error") != "null")){
            return true;
        }
        return false;
    }

    private JSONObject doRequest(String method,Object... params){
        JSONObject param = new JSONObject();
        param.put("id",System.currentTimeMillis()+"");
        param.put("jsonrpc","2.0");
        param.put("method",method);
        if(params != null){
            param.put("params",params);
        }
        String creb = Base64.encodeBase64String((Account.username+":"+Account.password).getBytes());
        Map<String,String> headers = new HashMap<>(2);
        headers.put("Authorization","Basic "+creb);
        String resp = "";
        if (Account.METHOD_GET_TRANSACTION.equals(method)){
            try{
                resp = USDTHttpUtil.jsonPost(Account.url,headers,param.toJSONString());
            }catch (Exception e){
                if (e instanceof IOException){
                    resp = "{}";
                }
            }
        }else{
            resp = USDTHttpUtil.jsonPost(Account.url,headers,param.toJSONString());
        }
        return JSON.parseObject(resp);
    }

}
