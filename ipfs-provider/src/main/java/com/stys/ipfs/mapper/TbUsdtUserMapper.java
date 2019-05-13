package com.stys.ipfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.TbUsdtVO;
import com.stys.ipfs.entity.TbUsdtUser;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
public interface TbUsdtUserMapper extends BaseMapper<TbUsdtUser> {

	@Select("<script> SELECT " + "	d.nickname,\r" + "	a.user_id, " + "	c.phone, "
			+ "	b.usdt_privatekey, " + "	b.address_imgpath, " + "	b.usdt_addr ,  a.usdt_id  " + "FROM "
			+ "	tb_usdt_user a " + "	LEFT JOIN tb_usdt b ON a.usdt_id = b.id "
			+ "	LEFT JOIN sys_app_user c ON c.id = a.user_id "
			+ "	LEFT JOIN sys_app_userinfo d ON d.user_id = a.user_id  " + "WHERE " + "	1 = 1 \r"
			+ "<if test='phone != null and phone != \"\" ' > and  c.phone like #{phone} </if>" + "</script>")
	List<TbUsdtVO> selectPageTbUsdtVO(Page<TbUsdtVO> page, EntityWrapper<TbUsdtVO> wrapper,@Param("phone") String phone);

}
