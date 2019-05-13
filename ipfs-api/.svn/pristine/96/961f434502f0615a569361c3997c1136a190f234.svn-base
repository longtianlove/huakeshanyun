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
 * @since 2019-05-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_feima_withdraw")
public class TbFeimaWithdraw extends Model<TbFeimaWithdraw> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 用户编号
	 */
	@TableField("user_id")
	private Integer userId;
	/**
	 * 数量
	 */
	private BigDecimal amount;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 交易时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/**
	 * 到账金额
	 */
	@TableField("arrival_amount")
	private BigDecimal arrivalAmount;
	/**
	 * 转出手续费
	 */
	@TableField("handler_fee")
	private BigDecimal handlerFee;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbFeimaWithdraw{" + ", id=" + id + ", userId=" + userId + ", amount=" + amount + ", status=" + status
				+ ", createTime=" + createTime + ", arrivalAmount=" + arrivalAmount + ", handlerFee=" + handlerFee
				+ "}";
	}
}
