package com.stys.ipfs.service.impl;

import java.util.Date;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.entity.TbBackfill;
import com.stys.ipfs.mapper.TbBackfillMapper;
import com.stys.ipfs.service.ITbBackfillService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-03-15
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbBackfillServiceImpl extends ServiceImpl<TbBackfillMapper, TbBackfill> implements ITbBackfillService {

	@Override
	public Integer insertNewData(TbBackfill tbBackfill) {
		tbBackfill.setCreateTime(new Date());
		//增加30天 
		tbBackfill.setBackfillTime(com.stys.ipfs.util.DateUtil.addDay2(30));
		Integer flag=this.baseMapper.insert(tbBackfill);
		return flag;
		
		
	}

}
