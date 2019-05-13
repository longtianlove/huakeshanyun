package com.stys.ipfs.service;

import com.baomidou.mybatisplus.service.IService;
import com.stys.ipfs.entity.TbBackfill;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dp
 * @since 2019-03-15
 */
public interface ITbBackfillService extends IService<TbBackfill> {

	Integer insertNewData(TbBackfill tbBackfill);

}
