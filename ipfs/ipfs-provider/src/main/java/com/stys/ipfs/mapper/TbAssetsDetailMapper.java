package com.stys.ipfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stys.ipfs.dto.UserAssetDetailVO;
import com.stys.ipfs.entity.TbAssetsDetail;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
public interface TbAssetsDetailMapper extends BaseMapper<TbAssetsDetail> {

	@Select("<script>SELECT  * FROM ( SELECT a.create_time ,a.in_or_out,a.amount,b.phone AS myaccont,c.phone AS otheraccont,a.type FROM "
			+ " tb_assets_detail a JOIN sys_app_user b ON a.user_id=b.id LEFT JOIN sys_app_user c ON a.sun_user_id=c.id WHERE a.type IN(38,43,47,33,24) "
			+ "<if test='userId != null and userId != \"\" ' > and  a.user_id = #{userId} </if>"
			+ ") s ORDER BY s.create_time desc </script>")
	public List<UserAssetDetailVO> getUserAessetDetail(Page<UserAssetDetailVO> page,@Param("userId") Integer userId);

	@Select("<script>SELECT a.*,b.nickname AS nickname ,d.phone,c.nickname AS sunname FROM tb_assets_detail a JOIN sys_app_userinfo b ON a.user_id=b.user_id"
			+ " LEFT JOIN sys_app_userinfo c ON a.sun_user_id=c.user_id JOIN sys_app_user d ON d.id=a.user_id"
			+ "<if test='startTime != null and startTime != \"\" and endTime != null and endTime != \"\"' >"  
			+ "and  a.create_time BETWEEN #{startTime} and #{endTime} </if>"
			+ "<if test='nickname != null and nickname != \"\" ' > and  b.nickname like #{nickname} </if>"
			+ "<if test='type != null and type != \"\" ' > and  a.type = #{type} </if>"
			+ "<if test='phone != null and phone != \"\" ' > and  d.phone = #{phone} </if>"
			+ "ORDER BY a.create_time desc </script>")
	List<TbAssetsDetail> selectPageListTbAssetsDetail(Page<TbAssetsDetail> page, @Param("phone") String phone,
			@Param("nickname") String nickname, @Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("type") Integer type); 

	/**
	 * 导出excel
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@Select("<script>SELECT a.*,b.nickname AS nickname ,d.phone,c.nickname AS sunname,e.name FROM tb_assets_detail a JOIN sys_app_userinfo b ON a.user_id=b.user_id"
			+ " LEFT JOIN sys_app_userinfo c ON a.sun_user_id=c.user_id JOIN sys_app_user d ON d.id=a.user_id   LEFT JOIN sys_dic e ON e.id=a.type  "
			+ "<if test='startTime != null and startTime != \"\" and endTime != null and endTime != \"\"' > "
			+ "and  a.create_time BETWEEN #{startTime} and #{endTime} </if>" + "ORDER BY a.create_time desc </script>")
	List<TbAssetsDetail> selectListTbAssetsDetail(@Param("startTime") String startTime,
			@Param("endTime") String endTime);
}