package com.stys.ipfs.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 文章信息管理
 * </p>
 *
 * @author dp
 * @since 2018-11-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_article")
public class Article extends Model<Article> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 图片地址
	 */
	@TableField("img_path")
	private String imgPath;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 类型
	 */
	@TableField("dic_id")
	private Integer dicId;
	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/**
	 * 展示时间
	 */
	@TableField("start_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startTime;
	/**
	 * 结束时间
	 */
	@TableField("end_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime;
	/**
	 * 点击量
	 */
	@TableField("click_num")
	private Integer clickNum;
	/**
	 * 备注
	 */
	@TableField(el = "remark, jdbcType=CLOB")
	private String remark;
	
	
	/**
	 * 超链接
	 */
	private String link;
	/**
	 * 当前状态
	 */
	private Integer state;

	@Override
	protected Serializable pkVal() { 
		return this.id;
	}
 
}
