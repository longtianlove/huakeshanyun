package com.stys.ipfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stys.ipfs.dto.TbInviteVO;
import com.stys.ipfs.entity.TbInvite;

/**
 * <p>
 * 邀请表 Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2018-10-29
 */
@Mapper
public interface TbInviteMapper extends BaseMapper<TbInvite> {
	/**
	 * 获取邀请人父节点
	 */
	@Select("SELECT a.id,a.tb_iviter,a.tb_ivitee, b.nickname AS iviterNickname ,c.nickname  AS iviteeNickname ,parent_id " + 
			",e.phone ,m.phone as phone2, f.name,h.name as name2 " + 
			"		FROM tb_invite  a " + 
			"		JOIN  sys_app_userinfo b ON a.tb_iviter=b.user_id  " + 
			"		JOIN  sys_app_userinfo c ON a.tb_ivitee = c.user_id  " + 
			"		JOIN  sys_app_user e on e.id=c.user_id " + 
			"		JOIN  sys_app_user m on m.id=b.user_id " + 
			"		join  sys_dic f  on f.id=e.dic_id " + 
			"		join  sys_dic h  on h.id=m.dic_id " +  
			"ORDER BY parent_id")
	List<TbInviteVO> selectListTbInviteVO();
	
	/**
	 *	 我邀请的人
	 */
	@Select("SELECT a.id,a.tb_iviter,a.tb_ivitee, b.nickname AS iviterNickname ,c.nickname "
			+ "AS iviteeNickname ,parent_id FROM tb_invite  a JOIN "
			+ "sys_userinfo b ON a.tb_iviter=b.user_id  JOIN sys_userinfo c "
			+ "ON  a.tb_ivitee=c.user_id WHERE a.tb_iviter=#{userId}")
	List<TbInviteVO> selectMyTbInviteVO(Integer userId);
}
