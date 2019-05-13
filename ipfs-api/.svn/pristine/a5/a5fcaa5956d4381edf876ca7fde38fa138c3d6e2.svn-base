package com.stys.ipfs.util.usdt;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CoinProxyFactory implements MethodInterceptor {
   
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
	private Object target;

	public CoinProxyFactory(Object target) {
        this.target = target;
    }

	public Object getProxyInstance() {
		Enhancer en = new Enhancer();
		en.setSuperclass(target.getClass());
		en.setCallback(this);
		return en.create();

	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		
		logger.info("before this  is  coin  transform"); 
		
		Object returnValue = method.invoke(target, args); 

		logger.info("");
		return returnValue; 
	}

}
