package com.stys.ipfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.AppUserInfoVo;
import com.stys.ipfs.entity.AppUserinfo;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2019-03-11
 */
public interface AppUserinfoMapper extends BaseMapper<AppUserinfo> {

	@Select("<script> SELECT a.*,b.*,c.*,   IFNULL(d.personal_status, 0) AS personal_status, f.address_imgpath,g.name AS lv "
			+ ",IF(IFNULL(h.user_id,0), 1,0) AS vipminer_status FROM sys_app_user a "
			+ " JOIN sys_app_userinfo b ON a.id=b.user_id " + "JOIN tb_user_assets c "
			+ " ON a.id=c.user_id LEFT JOIN tb_personal_info d ON a.id=d.user_id 	JOIN tb_usdt_user e on e.user_id=a.id"
			+ "	JOIN tb_usdt f on f.id=e.usdt_id  	 " + "  left join  tb_vipminer  h on h.user_id=a.id  "
			+ " JOIN sys_dic g ON a.dic_id=g.id "

			+ "where 1=1 <if test='userId != null and userId != \"\" ' > and  a.id=#{userId} </if>" + "</script>")
	public AppUserInfoVo UserInfo(@Param("userId") Integer userId);

	@Select("<script> SELECT a.*,b.*,c.*,d.personal_status ,IF(IFNULL(h.user_id,0), 1,0) AS vipminer_status ,g.name AS lv FROM sys_app_user a JOIN sys_app_userinfo b ON a.id=b.user_id JOIN tb_user_assets c "
			+ " ON a.id=c.user_id LEFT JOIN tb_personal_info d ON a.id=d.user_id  left join  tb_vipminer  h on h.user_id=a.id  JOIN sys_dic g ON a.dic_id=g.id  where 1=1 "
			+ "<if test='phone != null and phone != \"\" ' > and  a.phone=#{phone} </if>"
			+ "<if test='nickname != null and nickname != \"\" ' > and  b.nickname like #{nickname}</if>" + "</script>")
	List<AppUserInfoVo> selectPageListAppUserInfoVo(Page<AppUserInfoVo> page, @Param("phone") String phone,
			@Param("nickname") String nickname);
}
