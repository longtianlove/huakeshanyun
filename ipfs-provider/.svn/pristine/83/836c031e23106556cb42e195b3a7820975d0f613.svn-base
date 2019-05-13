package com.stys.ipfs.config;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

/**
 * 公共字段自动填充
 */
public class AutoMetaObjectHandler extends MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		setFieldValByName("updateTime", new Date(), metaObject);
		setFieldValByName("createTime", new Date(), metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		setFieldValByName("updateTime", new Date(), metaObject);
	}
}