package com.stys.ipfs.service.impl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stys.ipfs.dto.TbInviteVO;
import com.stys.ipfs.entity.TbInvite;
import com.stys.ipfs.mapper.TbInviteMapper;
import com.stys.ipfs.service.ITbInviteService;

/**
 * <p>
 * 邀请表 服务实现类
 * </p>
 *
 * @author dp
 * @since 2018-10-29
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbInviteServiceImpl extends ServiceImpl<TbInviteMapper, TbInvite> implements ITbInviteService {
	

	
	@Override
	public List<TbInviteVO> getTreeNode() { 
		return this.baseMapper.selectListTbInviteVO();
	}

	public List<TbInviteVO> getParentNode() {
		return this.baseMapper.selectListTbInviteVO();
	}

	@Override
	public List<TbInviteVO> getMyInvite(Integer userId) {
		return this.baseMapper.selectMyTbInviteVO(userId);
	}


	@Override
	public TbInvite getTbInviteByTb_iviter(Integer tb_iviter) {
		TbInvite  tbInvite=new TbInvite();
		tbInvite.setTbIviter(tb_iviter);
		return  this.baseMapper.selectOne(tbInvite); 
	}

}
