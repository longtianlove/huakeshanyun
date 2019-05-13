package com.stys.ipfs.service.impl;

import com.stys.ipfs.entity.TbUsdtWithdraw;
import com.stys.ipfs.mapper.TbUsdtWithdrawMapper;
import com.stys.ipfs.service.ITbUsdtWithdrawService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-04-26
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbUsdtWithdrawServiceImpl extends ServiceImpl<TbUsdtWithdrawMapper, TbUsdtWithdraw> implements ITbUsdtWithdrawService {

}
