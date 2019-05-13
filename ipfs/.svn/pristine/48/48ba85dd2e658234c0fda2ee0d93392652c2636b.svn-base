package com.stys.ipfs.tools.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.toolkit.StringUtils;

/**
 * 生成网页和其他的file修改这个文件
 * 
 * @author dp
 * 
 *
 */
public class WebGenerator {

	private static final Logger logger = LoggerFactory.getLogger(WebGenerator.class);

	private static String templatePath="list.html.vm"; 
	private static String templatePath2="list.js.vm"; 
	private static String templatePath3="info.html.vm"; 
	private static String templatePath4="info.js.vm"; 
	
	private static String OUTPUT_WEB_JS_DIR = "D:\\workspace2\\ipfs\\ipfs-consumer\\src\\main\\resources\\static\\js";
	private static String OUTPUT_WEB_HTML_DIR = "D:\\workspace2\\ipfs\\ipfs-consumer\\src\\main\\resources\\templates\\";

	/**
	 * velocity引擎
	 */
	private static VelocityEngine engine;

	public static void makeHtml(String entityName,VelocityContext ctx) {

		 
		String newEntityName=entityName.substring(0, 1).toLowerCase()+entityName.substring(1);
		newEntityName=newEntityName.replaceAll("_", "");
		try {
			String new_out_path=OUTPUT_WEB_HTML_DIR+File.separator +newEntityName+File.separator+templatePath.substring(0,templatePath.length()-3);  
			String new_out_path2=OUTPUT_WEB_JS_DIR+File.separator +newEntityName+File.separator+templatePath2.substring(0,templatePath2.length()-3);  
			String new_out_path3=OUTPUT_WEB_HTML_DIR+File.separator +newEntityName+File.separator+templatePath3.substring(0,templatePath3.length()-3);  
			String new_out_path4=OUTPUT_WEB_JS_DIR+File.separator +newEntityName+File.separator+templatePath4.substring(0,templatePath4.length()-3);  
			vmToFile(ctx, templatePath, new_out_path);
			vmToFile(ctx, templatePath2, new_out_path2); 
			vmToFile(ctx, templatePath3, new_out_path3);
			vmToFile(ctx, templatePath4, new_out_path4); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static boolean containsUpperCase(String word) {
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (Character.isUpperCase(c)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	/**
	 * <p>
	 * 将模板转化成为文件
	 * </p>
	 *
	 * @param context      内容对象
	 * @param templatePath 模板文件
	 * @param outputFile   文件生成的目录
	 */
	private static void vmToFile(VelocityContext context, String templatePath, String outputFile) throws IOException {
		if (StringUtils.isEmpty(templatePath)) {
			return;
		}
		VelocityEngine velocity = getVelocityEngine();
		Template template = velocity.getTemplate(templatePath, ConstVal.UTF8);
		File file = new File(outputFile);
		if (!file.getParentFile().exists()) {
			// 如果文件所在的目录不存在，则创建目录
			if (!file.getParentFile().mkdirs()) {
				logger.debug("创建文件所在的目录失败!"); 
				return;
			}
		}
		FileOutputStream fos = new FileOutputStream(outputFile);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, ConstVal.UTF8));
		template.merge(context, writer);
		writer.close();
		logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
	}

	/**
	 * 设置模版引擎，主要指向获取模版路径
	 */
	private static VelocityEngine getVelocityEngine() {
		if (engine == null) {
			Properties p = new Properties();
			engine = new VelocityEngine(p);
			engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	 
		}
		return engine;
	} 
	
	//测试
	public static void main(String[] args) {

	 //	makeHtml("task_schedule");
	}
}
