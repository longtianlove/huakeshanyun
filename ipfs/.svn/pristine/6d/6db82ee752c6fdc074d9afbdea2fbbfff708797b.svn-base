package com.stys.ipfs.tools.generator;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;

/**
 * 这个文件不需要提交
 * 使用时，只需要defaultGenerator.setIncludeTables(TABLE_PREFIX, "tb_castle");修改替换tb_castle 这个表
 * 代码生成OUTPUT_...等目录,放入目录就可以
 * 代码生成器
 */
public class TyqxCmsGenerator {

	// 根据命名规范，只修改此常量值即可
	private static String MODULE = "ipfs";
	private static String TABLE_PREFIX = "sys_";
	private static String PARENT_PACKAGE_NAME = "com.stys";

	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/hksy_db?useUnicode=true&amp;characterEncoding=UTF-8&amp;tinyInt1isBit=false";
	private static String JDBC_USERNAME = "root";
	private static String JDBC_PASSWORD = "root";

	private static String OUTPUT_API_DIR = "D:\\workspace2\\ipfs\\ipfs-api\\src\\main\\java";
	private static String OUTPUT_SERVICE_DIR = "D:\\workspace2\\ipfs\\ipfs-provider\\src\\main\\java";
	private static String OUTPUT_CONTROLLER_DIR = "D:\\workspace2\\ipfs\\ipfs-consumer\\src\\main\\java";
	private static String OUTPUT_MAPPER_DIR="D:\\workspace2\\ipfs\\ipfs-provider\\src\\main\\resources\\mapper";

	
	/**
	 *          自动代码生成
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName(JDBC_DRIVER);
		dsc.setUsername(JDBC_USERNAME);
		dsc.setPassword(JDBC_PASSWORD);
		dsc.setUrl(JDBC_URL);

		PackageConfig pc = new PackageConfig();
		pc.setParent(PARENT_PACKAGE_NAME);
		pc.setModuleName(MODULE);

		DefaultGenerator defaultGenerator = new DefaultGenerator();
		defaultGenerator.setDataSource(dsc);
		defaultGenerator.setPackageInfo(pc);
		//defaultGenerator.setIncludeTables(TABLE_PREFIX, "sys_code", "sys_login_log");
		defaultGenerator.setIncludeTables(TABLE_PREFIX, "tb_vipminer","tb_vipminer_earnings","tb_system_profit");
	 
		 
		// 生成API
		defaultGenerator.setOutputDir(OUTPUT_API_DIR);
		defaultGenerator.createClass("entity", "service");
		
		// 生成Mapper
		 defaultGenerator.setOutputDir(OUTPUT_MAPPER_DIR);
		 defaultGenerator.createClass("Mapper");
		 
		 
		// 生成服务
		defaultGenerator.setOutputDir(OUTPUT_SERVICE_DIR);
		defaultGenerator.createClass("Mapper", "mapper", "service.impl");
	
		// 生成Controller
		defaultGenerator.setOutputDir(OUTPUT_CONTROLLER_DIR);
		defaultGenerator.createClass("controller");
		
	}

}
