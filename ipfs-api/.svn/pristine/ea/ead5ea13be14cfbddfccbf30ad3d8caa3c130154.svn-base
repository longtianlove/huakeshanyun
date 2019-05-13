package com.stys.ipfs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @since 2019-04-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_usdt_withdraw")
public class TbUsdtWithdraw extends Model<TbUsdtWithdraw> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 用户编号
	 */
	@TableField("user_id")
	private Integer userId;
	/**
	 * usdt数量
	 */
	@TableField("usdt_number")
	private BigDecimal usdtNumber;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 交易编号
	 */
	private String txid;
	/**
	 * 交易时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/**
	 * 转出地址
	 */
	@TableField("out_address")
	private String outAddress;
	/**
	 * 到账金额
	 */
	@TableField("arrival_amount")
	private BigDecimal arrivalAmount;

	@TableField("main_out_address")
	private String mainOutAddress;

	@TableField("usdt_fee")
	private BigDecimal usdtFee;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
