package com.stys.ipfs.service;

import com.stys.ipfs.entity.Article;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 文章信息管理 服务类
 * </p>
 *
 * @author dp
 * @since 2018-11-14
 */
public interface IArticleService extends IService<Article> {
	
	List<Article> getArticleList(int id, int value);

}
