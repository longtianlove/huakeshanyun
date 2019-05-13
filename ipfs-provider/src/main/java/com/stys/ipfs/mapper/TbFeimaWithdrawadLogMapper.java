package com.stys.ipfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.entity.TbFeimaWithdrawadLog;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2019-05-05
 */
public interface TbFeimaWithdrawadLogMapper extends BaseMapper<TbFeimaWithdrawadLog> {

	@Select("<script>SELECT" + "	a.*," + "	b.nickname," + "	c.phone," + "	e.real_name " + "FROM"
			+ "	tb_feima_withdrawad_log a" + "	JOIN sys_app_userinfo b ON a.user_id = b.user_id"
			+ "	JOIN sys_app_user c ON c.id = a.user_id" + "	LEFT JOIN tb_personal_info e ON a.user_id = e.user_id "
			+ "WHERE" + " 	1 = 1  "
			+ "<if test='realName != null and realName != \"\" ' > and  e.real_name like #{realName} </if>"
			+ "<if test='phone != null and phone != \"\" ' > and  c.phone = #{phone} </if>"
			+ "<if test='startTime != null and startTime != \"\" ' > and  a.create_time>#{startTime} </if>"
			+ "<if test='endTime != null and endTime != \"\" ' > and  a.endTime>#{endTime} </if>"
			+ " ORDER BY a.create_time DESC  </script>")
	List<TbFeimaWithdrawadLog> selectTbFeimaWithdrawadLog(Page<TbFeimaWithdrawadLog> page,@Param("realName") String realName,
			@Param("phone")	String phone,@Param("startTime") String startTime,@Param("endTime") String endTime);

}
