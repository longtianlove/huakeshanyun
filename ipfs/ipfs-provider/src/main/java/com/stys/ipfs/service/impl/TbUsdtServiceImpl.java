package com.stys.ipfs.service.impl;

import org.springframework.beans.BeanUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.dto.TbUsdtVO;
import com.stys.ipfs.entity.TbUsdt;
import com.stys.ipfs.mapper.TbUsdtMapper;
import com.stys.ipfs.service.ITbUsdtService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbUsdtServiceImpl extends ServiceImpl<TbUsdtMapper, TbUsdt> implements ITbUsdtService {

	@Override
	public TbUsdt getUnUseOne() {
	  
		TbUsdtVO  tbUsdtVO= this.baseMapper.selectUnUseOne();
		if(null!=tbUsdtVO) {
			TbUsdt  tbUsdt=new TbUsdt();
			BeanUtils.copyProperties(tbUsdtVO, tbUsdt);
			return tbUsdt;
		}
		return null; 
	}

}
