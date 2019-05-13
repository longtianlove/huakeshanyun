package com.stys.ipfs.service.impl;

import com.stys.ipfs.entity.Article;
import com.stys.ipfs.mapper.ArticleMapper;
import com.stys.ipfs.service.IArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 文章信息管理 服务实现类
 * </p>
 *
 * @author dp
 * @since 2018-11-14
 */
@Service(version = "1.0.0", timeout = 60000)
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {


	@Override
	public List<Article> getArticleList(int id, int value) {
		return this.baseMapper.getArticleList(id, value);
	}
}
