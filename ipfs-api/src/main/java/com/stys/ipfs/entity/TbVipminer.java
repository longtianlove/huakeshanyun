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
 * 
 * </p>
 *
 * @author dp
 * @since 2019-03-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_vipminer")
public class TbVipminer extends Model<TbVipminer> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 限额
	 */
	@TableField("limit_coin")
	private Double limitCoin;
	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/**
	 * 结束时间
	 */
	@TableField("end_time")
	private Date endTime;
	/**
	 * 排序编号
	 */
	@TableField("vip_sort")
	private Integer vipSort;
	/**
	 * 平台分红收益
	 */
	@TableField("gift_coin")
	private Double giftCoin;

	@TableField("status")
	private Integer status;
	
	@TableField(exist=false)
	private String nickname; //用户昵称
	
	@TableField(exist=false)
	private String realName; //用户姓名

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	/**
	 * 用户id
	 */
	@TableField("user_id")
	private Integer userId;

	@Override
	public String toString() {
		return "TbVipminer{" + ", id=" + id + ", limitCoin=" + limitCoin + ", createTime=" + createTime + ", endTime="
				+ endTime + ", vipSort=" + vipSort + ", giftCoin=" + giftCoin + "}";
	}
}
