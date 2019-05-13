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
@TableName("tb_feima_withdrawad_log")
public class TbFeimaWithdrawadLog extends Model<TbFeimaWithdrawadLog> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 提现金额
	 */
	@TableField("cash_number")
	private BigDecimal cashNumber;
	/**
	 * 用户外键
	 */
	@TableField("user_id")
	private Integer userId;
	/**
	 * 审核状态（1、通过 2、不通过）
	 */
	@TableField("cash_status")
	private Integer cashStatus;
	/**
	 * 实际扣除
	 */
	@TableField("actual_amount")
	private BigDecimal actualAmount;
	/**
	 * 提现发起时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/**
	 * 用户姓名
	 */
	@TableField("user_name")
	private String userName;
	/**
	 * 手续费
	 */
	private BigDecimal handfree;
	/**
	 * 审核信息
	 */
	@TableField("verify_info")
	private String verifyInfo;
	/**
	 * 提现前余额
	 */
	@TableField("before_amount")
	private BigDecimal beforeAmount;

	@TableField("after_amount")
	private BigDecimal afterAmount;

	@TableField(exist = false)
	private String phone; // 用户账户

	@TableField(exist = false)
	private String realName; // 用户真实姓名

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
