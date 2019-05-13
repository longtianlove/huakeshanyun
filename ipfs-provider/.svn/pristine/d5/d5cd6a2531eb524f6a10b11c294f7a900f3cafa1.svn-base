package com.stys.ipfs.service.impl;

import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.entity.Dic;
import com.stys.ipfs.mapper.DicMapper;
import com.stys.ipfs.service.IDicService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dp
 * @since 2018-10-24
 */
@Service(version = "1.0.0", timeout = 60000)
public class DicServiceImpl extends ServiceImpl<DicMapper, Dic> implements IDicService {

	@Override
	public Integer getDicNum(Dic entity) {
		EntityWrapper<Dic> wrapper = new EntityWrapper<>();
		Dic dic = entity;
		wrapper.eq("group_name", dic.getGroupName());
		wrapper.eq("name", dic.getName());
		Map<String, Object> numDicMap = this.selectMap(wrapper);
		int num = 6;
		if (null != numDicMap.get("value1")) {
			String numStr = (String) numDicMap.get("value1");
			num = Integer.valueOf(numStr);
		}
		return num;
	}

	@Override
	public String getDicValue1(String group_name, String name) {
		EntityWrapper<Dic> wrapper = new EntityWrapper<>();
		wrapper.eq("group_name", group_name);
		wrapper.eq("name", name);
		Map<String, Object> numDicMap = this.selectMap(wrapper);

		if (null == numDicMap.get("value1")) {
			return "";
		}
		return (String) numDicMap.get("value1");
	}

	@Override
	public  Dic getDicData(String group_name, String name) {
	 
		Dic dic=this.baseMapper.getDicData(group_name,name);
		return dic;
	}

	@Override
	public List<Dic> getDicDataByGroupName(String group_name) {
		EntityWrapper<Dic> dicwrapper = new EntityWrapper<>();
		dicwrapper.eq("group_name", group_name);
		return this.selectList(dicwrapper);
	}

}
