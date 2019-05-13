package com.stys.ipfs.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

public class ConverterConfig {
	
	
	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;
	
	@PostConstruct
    public void initEditableAvlidation() {
		
		 ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer)handlerAdapter.getWebBindingInitializer();
		 if(initializer.getConversionService()!=null) {
			 GenericConversionService genericConversionService = (GenericConversionService)initializer.getConversionService();
			 genericConversionService.addConverter(new DateConverter());
		 }
		
		
	}

}
