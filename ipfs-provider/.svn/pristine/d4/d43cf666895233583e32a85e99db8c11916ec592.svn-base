package com.stys.ipfs.mapper;

import com.stys.ipfs.entity.TbWithdrawadLog;

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
 * @since 2019-03-13
 */
public interface TbWithdrawadLogMapper extends BaseMapper<TbWithdrawadLog> {
	
	@Select("<script> SELECT a.*,b.nickname,c.phone FROM tb_withdrawad_log a  JOIN sys_app_userinfo b ON a.user_id=b.user_id JOIN sys_app_user c ON a.user_id= c.id where 1=1 "
			+ "<if test='cashStatus != null and cashStatus != \"\" ' > and  a.cash_status =#{cashStatus} </if>"
			+ "<if test='phone != null and phone != \"\" ' > and  c.phone =#{phone} </if>"
			+ "<if test='userName != null and userName != \"\" ' > and  a.user_name like #{userName} </if>"
			+ "ORDER BY a.create_time DESC </script>")
	List<TbWithdrawadLog> selectPageListTbWithdrawadLog(Page<TbWithdrawadLog> page,@Param("phone")String phone ,@Param("cashStatus")String cashStatus,@Param("userName")String userName);
}
