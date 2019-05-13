package com.stys.ipfs.mapper;

import com.stys.ipfs.entity.TbOfflinePayment;

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
 * @since 2019-03-21
 */
public interface TbOfflinePaymentMapper extends BaseMapper<TbOfflinePayment> {
	
	
	@Select("<script> SELECT a.*,b.nickname,c.phone,e.real_name FROM tb_offline_payment a JOIN sys_app_userinfo b ON a.user_id = b.user_id JOIN sys_app_user c "
			+ "ON c.id = a.user_id LEFT JOIN tb_personal_info e ON a.user_id=e.user_id WHERE 1 = 1  "
			+ "<if test='realName != null and realName != \"\" ' > and  e.real_name like #{realName} </if>"
			+ "<if test='phone != null and phone != \"\" ' > and  c.phone = #{phone} </if>"
			+ "<if test='status != null ' > and  a.status = #{status} </if>"
			+ "ORDER BY a.create_time DESC </script>")
	List<TbOfflinePayment> selectPageTbOfflinePayment(Page<TbOfflinePayment> page,@Param("realName") String realName ,@Param("phone") String phone,@Param("status") Integer status);
}