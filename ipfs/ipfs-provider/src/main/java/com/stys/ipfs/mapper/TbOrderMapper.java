package com.stys.ipfs.mapper;

import com.stys.ipfs.entity.TbOrder;

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
 * @since 2019-01-21
 */
public interface TbOrderMapper extends BaseMapper<TbOrder> {

	
	@Select("<script> SELECT  a.* ,b.nickname,c.merchandise_name  FROM tb_order a JOIN sys_userinfo b ON a.user_id =b.user_id JOIN tb_merchandise c ON a.product_id=c.id "
			+ "where 1=1 <if test='orderNo != null and orderNo != \"\" ' > and  a.order_no =#{orderNo} </if>"
			+ "</script>")
	List<TbOrder> selectPageTbOrder(Page<TbOrder> page,@Param("orderNo") String orderNo);

}
