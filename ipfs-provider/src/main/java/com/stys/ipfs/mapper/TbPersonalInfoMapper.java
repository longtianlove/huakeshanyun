package com.stys.ipfs.mapper;

import com.stys.ipfs.entity.TbPersonalInfo;

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
 * @since 2019-03-12
 */
public interface TbPersonalInfoMapper extends BaseMapper<TbPersonalInfo> {

	
	@Select("<script>SELECT a.*,b.phone AS accont ,c.nickname FROM  tb_personal_info a JOIN 	sys_app_user b ON a.user_id=b.id JOIN sys_app_userinfo c ON a.user_id=c.user_id where 1=1 "
			+ "<if test='accont != null and accont != \"\" ' > and  b.phone =#{accont} </if>"
			+ "<if test='nickname != null and nickname != \"\" ' > and  c.nickname like #{nickname} </if>"
			+ "<if test='realName != null and realName != \"\" ' > and  a.real_name like #{realName} </if>"
			+ "</script>")
	List<TbPersonalInfo> selectPageListTbPersonalInfo(Page<TbPersonalInfo> page,@Param("accont")String accont ,@Param("nickname")String nickname,@Param("realName")String realName);
}
