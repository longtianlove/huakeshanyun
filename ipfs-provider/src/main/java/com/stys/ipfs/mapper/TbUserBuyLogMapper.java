package com.stys.ipfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.entity.TbUserBuyLog;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2019-01-14
 */
public interface TbUserBuyLogMapper extends BaseMapper<TbUserBuyLog> {

	@Select("<script> SELECT  a.* ,b.nickname,c.phone,e.real_name  FROM tb_user_buy_log a JOIN sys_app_userinfo b ON a.user_id =b.user_id "
			+ "join sys_app_user c on c.id=a.user_id LEFT JOIN tb_personal_info e  ON a.user_id=e.user_id  where 1=1  "
			+ "<if test='nickname != null and nickname != \"\" ' > and  b.nickname like #{nickname} </if>"
			+ "<if test='phone != null and phone != \"\" ' > and  c.phone = #{phone} </if>"
			+ " ORDER BY a.create_time DESC </script>")
	List<TbUserBuyLog> selectPageTbUserBuyLog(Page<TbUserBuyLog> page,@Param("nickname") String nickname ,@Param("phone") String phone);

	@Select("select sum(t.product_price) as total_profit from tb_user_buy_log t where t.create_time like #{currentDateTime} ")
	Float selectTodayBuyProfit(@Param("currentDateTime")String currentDateTime);  

}
