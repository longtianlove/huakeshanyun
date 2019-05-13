package com.stys.ipfs.service.impl;

import java.io.IOException;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.zxing.WriterException;
import com.stys.ipfs.dto.TbUsdtVO;
import com.stys.ipfs.entity.TbUsdt;
import com.stys.ipfs.entity.TbUsdtUser;
import com.stys.ipfs.mapper.TbUsdtUserMapper;
import com.stys.ipfs.service.ITbUsdtService;
import com.stys.ipfs.service.ITbUsdtUserService;
import com.stys.ipfs.util.StringUtils;
import com.stys.ipfs.util.TwoDimensionCode;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
@Service(version = "1.0.0", timeout = 60000)
public class TbUsdtUserServiceImpl extends ServiceImpl<TbUsdtUserMapper, TbUsdtUser> implements ITbUsdtUserService {

	@Reference(version = "1.0.0", check = false)
	private ITbUsdtService itbUsdtService;

	@Override
	public boolean insertUsdtData(int userId) {

		try {
			TbUsdt tbUsdt = itbUsdtService.getUnUseOne();
			if (null == tbUsdt) {
				return false;
			}
			TbUsdtUser tbUsdtUser = new TbUsdtUser();
			tbUsdtUser.setUsdtId(tbUsdt.getId());
			tbUsdtUser.setUserId(userId);
			if (this.baseMapper.insert(tbUsdtUser) > 0) {
				// usdt 已经使用，并更新状态
				tbUsdt.setStatus(1);
				String addressImgpath = TwoDimensionCode.makeUSDTCodeImg(tbUsdt.getUsdtAddr());
				tbUsdt.setAddressImgpath(addressImgpath);
				if (itbUsdtService.updateById(tbUsdt)) {
					return true;
				}
			}
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Page<TbUsdtVO> selectPageTbUsdtVO(Page<TbUsdtVO> page, EntityWrapper<TbUsdtVO> wrapper, TbUsdtVO tbUsdtVO) {

		SqlHelper.fillWrapper(page, wrapper);
		String phone = "";
		if (!StringUtils.isEmpty(tbUsdtVO.getPhone())) {
			phone = "%" + tbUsdtVO.getPhone() + "%";
		}
		page.setRecords(this.baseMapper.selectPageTbUsdtVO(page, wrapper, phone));
		return page;
	}

}
