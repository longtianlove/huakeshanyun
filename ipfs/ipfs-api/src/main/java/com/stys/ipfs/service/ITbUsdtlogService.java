package com.stys.ipfs.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.entity.TbUsdtlog;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
public interface ITbUsdtlogService extends IService<TbUsdtlog> {

	List<TbUsdtlog> selectUsdtlog(Integer userId);

}
