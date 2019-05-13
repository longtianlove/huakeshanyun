package com.stys.ipfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stys.ipfs.entity.TbUsdtlog;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
public interface TbUsdtlogMapper extends BaseMapper<TbUsdtlog> {

	@Select("select * from tb_usdtlog where user_id=#{userId} order by usdtlog_time desc  ")
	List<TbUsdtlog> selectUsdtlog(Integer userId);

}
