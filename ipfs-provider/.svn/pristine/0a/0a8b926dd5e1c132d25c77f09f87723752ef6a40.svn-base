package com.stys.ipfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.TbInviterExpericence;
import com.stys.ipfs.dto.UserExperienceVO;
import com.stys.ipfs.entity.TbUserExperience;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2019-01-13
 */
public interface TbUserExperienceMapper extends BaseMapper<TbUserExperience> {
	@Select("<script> SELECT  b.nickname,a.user_id ,a.experience ,a.create_time FROM tb_user_experience a JOIN  sys_app_userinfo b ON a.user_id=b.user_id "
			+ "<if test='nickname != null and nickname != \"\" ' > and  b.nickname like #{nickname} </if>"
			+ "</script>")
	List<UserExperienceVO> selectPageListVO(Page<UserExperienceVO> page, EntityWrapper<UserExperienceVO> wrapper,
			@Param("nickname") String nickname);

	@Select("SELECT" + "	b.nickname," + "	a.user_id," + "	a.experience," + "	a.create_time," + "	c.phone"
			+ "	" + "FROM" + "	tb_user_experience a" + "	JOIN sys_app_userinfo b ON a.user_id = b.user_id"
			+ "	JOIN sys_app_user c ON c.id = b.user_id  where c.id=#{initer_user_id}")
	TbInviterExpericence selectAllExperienceDatas(@Param("initer_user_id") Integer initer_user_id);
}
