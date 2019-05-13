package com.stys.ipfs.mapper;

import com.stys.ipfs.entity.Article;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 文章信息管理 Mapper 接口
 * </p>
 *
 * @author dp
 * @since 2018-11-14
 */
public interface ArticleMapper extends BaseMapper<Article> {
	
	/**
	 * 	获取当前时间段的推送
	 * @param date
	 * @return
	 */
	@Select("SELECT * FROM  sys_article  WHERE state= 1 and dic_id=#{id} order by create_time LIMIT #{value}")
	List<Article> getArticleList(@Param(value = "id")int id,@Param(value = "value") Integer value);
	
}
