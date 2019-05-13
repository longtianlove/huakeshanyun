package com.stys.ipfs.mapper;

import com.stys.ipfs.entity.TbVipminer;

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
 * @since 2019-03-17
 */
public interface TbVipminerMapper extends BaseMapper<TbVipminer> {

	@Select("<script>  SELECT a.*,b.nickname,c.real_name FROM tb_vipminer a JOIN sys_app_userinfo b ON a.user_id=b.user_id "
			+ "LEFT JOIN tb_personal_info c ON a.user_id=c.user_id where 1=1 "
			+ "<if test='nickname != null and nickname != \"\" ' > and  b.nickname like #{nickname} </if>"
			+ "</script>")
	List<TbVipminer> selectPageListTbVipminer(Page<TbVipminer> page,@Param("nickname")String nickname);

}
