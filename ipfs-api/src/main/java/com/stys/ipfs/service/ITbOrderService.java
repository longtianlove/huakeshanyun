package com.stys.ipfs.service;

import com.stys.ipfs.entity.TbOrder;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2019-01-21
 */
public interface ITbOrderService extends IService<TbOrder> {

	Page<TbOrder> selectPageTbOrder(Page<TbOrder> page, String orderNo);

}
