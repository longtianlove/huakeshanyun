package com.stys.ipfs.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.entity.Dic;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2018-10-24
 */
public interface IDicService extends IService<Dic> {

	
	 Integer getDicNum(Dic entity); 
	
	String getDicValue1(String group_name, String name);
	
	 Dic getDicData(String group_name,String name);
	
	/**
	 * 	根据分组名称获取数据字典
	 * @param group_name
	 * @return
	 */
	List<Dic> getDicDataByGroupName(String group_name);
}
