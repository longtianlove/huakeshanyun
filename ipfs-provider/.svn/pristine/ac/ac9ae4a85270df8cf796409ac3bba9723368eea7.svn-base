package com.stys.ipfs.mapper;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stys.ipfs.dto.TbUsdtVO;
import com.stys.ipfs.entity.TbUsdt;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
public interface TbUsdtMapper extends BaseMapper<TbUsdt> {

	@Select("select  * from tb_usdt  t where t.status=0  ORDER BY  id asc limit 1")
	TbUsdtVO selectUnUseOne();

}
