package com.stys.ipfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stys.ipfs.dto.TbUsdtVO;
import com.stys.ipfs.entity.AppUser;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2019-03-11
 */
public interface AppUserMapper extends BaseMapper<AppUser> {

	@Select("SELECT " + 
			"	c.id, " + 
			"	c.usdt_balance, " + 
			"	c.usdt_addr, " + 
			"	c.status, " + 
			"	c.create_time, " + 
			"	c.address_imgpath, " + 
			"   d.user_id ,a.phone,d.nickname " + 
			"FROM" + 
			"	sys_app_user a," + 
			"	tb_usdt_user b," + 
			"	sys_app_userinfo d," + 
			"	tb_usdt c " + 
			"WHERE" + 
			"	a.id = b.user_id " + 
			"	AND b.usdt_id = c.id " + 
			"	AND a.state = '0'" + 
			"	and d.user_id=a.id" + 
			" ")
	List<TbUsdtVO> selectUSDTs();

	@Select("SELECT" + 
			"   c.id, " + 
			"	c.usdt_balance, " + 
			"	c.usdt_addr, " + 
			"	c.status, " + 
			"	c.create_time, " + 
			"	c.address_imgpath, " + 
			"   d.user_id as user_id ,a.phone,d.nickname " + 
			" FROM " + 
			"	sys_app_user a," + 
			"	tb_usdt_user b, " + 
			"	sys_app_userinfo d," + 
			"	tb_usdt c " + 
			" WHERE " + 
			"	 b.user_id=a.id" + 
			"	AND c.id=b.usdt_id" + 
			"	and d.user_id=a.id" + 
			"	AND c.usdt_addr=#{usdtAddress} " +  
			" ") 
	TbUsdtVO selectUserIdByUSDTAddress(@Param(value = "usdtAddress")String usdtAddress);
	
	@Select("update    sys_app_user   set state=#{status}  where id=#{user_id}")
	boolean updateStatus(int user_id, int status); 

}
