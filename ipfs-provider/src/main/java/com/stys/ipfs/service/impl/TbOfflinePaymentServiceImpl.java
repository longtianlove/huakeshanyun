package com.stys.ipfs.service.impl;

import com.stys.ipfs.entity.TbOfflinePayment;
import com.stys.ipfs.mapper.TbOfflinePaymentMapper;
import com.stys.ipfs.service.ITbOfflinePaymentService;
import com.stys.ipfs.util.StringUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-03-21
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbOfflinePaymentServiceImpl extends ServiceImpl<TbOfflinePaymentMapper, TbOfflinePayment> implements ITbOfflinePaymentService {

	@Override
	public Page<TbOfflinePayment> getPageTbOfflinePayment(Page<TbOfflinePayment> page, String phone, String realName,Integer status) {
		if(!StringUtils.isEmpty(realName)) {
			realName="%"+realName+"%";
		}
		page.setRecords(this.baseMapper.selectPageTbOfflinePayment(page, realName, phone,status));
		return page;
	}

}
