package com.stys.ipfs.mapper;

import com.stys.ipfs.entity.TbCoCreator;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2019-04-26
 */
public interface TbCoCreatorMapper extends BaseMapper<TbCoCreator> {
	
	@Select("<script>  SELECT a.*,b.phone,c.real_name FROM  tb_co_creator  a  JOIN sys_app_user  "
			+ "b ON a.user_id=b.id LEFT JOIN tb_personal_info c ON a.user_id=c.user_id where 1=1 "
			+ "<if test='realName != null and realName != \"\" ' > and  c.real_name like #{realName} </if>"
			+ "<if test='phone != null and phone != \"\" ' > and  b.phone =#{phone} </if>"
			+ "</script>")
	List<TbCoCreator> selectPageTbCoCreator(Page<TbCoCreator> page,@Param("realName")String realName,@Param("phone")String phone);
}
