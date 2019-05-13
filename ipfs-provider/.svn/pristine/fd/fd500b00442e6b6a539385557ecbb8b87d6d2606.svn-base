package com.stys.ipfs.mapper;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stys.ipfs.entity.TbUserAssets;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
public interface TbUserAssetsMapper extends BaseMapper<TbUserAssets> {

	@Select("update ")
	boolean updateByForUpdate(TbUserAssets tbUserAssets);

}
