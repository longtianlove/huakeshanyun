package com.stys.ipfs.service;

import com.stys.ipfs.dto.AppResultInfo;
import com.stys.ipfs.entity.TbProduct;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2019-03-09
 */
public interface ITbProductService extends IService<TbProduct> {
	
	/**
	 * 	购买
	 * @param userId 用户Id
	 * @param productId 产品id
	 * @return
	 */
	AppResultInfo<?> buyProduct(Integer userId, Integer productId);
}
