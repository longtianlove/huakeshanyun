package com.stys.ipfs.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stys.ipfs.entity.Dic;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2018-10-24
 */
public interface DicMapper extends BaseMapper<Dic> { 

	@Select("select * from sys_dic where group_name=#{group_name} and name=#{name}")
	Dic getDicData(@Param(value="group_name") String group_name,@Param(value="name") String name); 

}
