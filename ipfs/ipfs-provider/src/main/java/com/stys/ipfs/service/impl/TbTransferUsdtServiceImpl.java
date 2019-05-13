package com.stys.ipfs.service.impl;

import com.stys.ipfs.entity.TbTransferUsdt;
import com.stys.ipfs.mapper.TbTransferUsdtMapper;
import com.stys.ipfs.service.ITbTransferUsdtService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-03-15
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbTransferUsdtServiceImpl extends ServiceImpl<TbTransferUsdtMapper, TbTransferUsdt> implements ITbTransferUsdtService {

}
