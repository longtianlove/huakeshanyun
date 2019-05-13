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
 * @since 2019-04-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_feima_order")
public class TbFeimaOrder extends Model<TbFeimaOrder> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	@TableField("mchOrderNo")
	private String mchOrderNo;

	@TableField("user_id")
	private Integer userId;

	@TableField("productName")
	private String productName;

	@TableField("productDesc")
	private String productDesc;

	private Integer amount;

	private String attach;

	/**
	 * 0 待支付
	 */
	private Integer status;

	@TableField("orderToken")
	private String orderToken;

	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbFeimaOrder{" + ", id=" + id + ", mchOrderNo=" + mchOrderNo + ", userId=" + userId + ", productName="
				+ productName + ", productDesc=" + productDesc + ", amount=" + amount + ", attach=" + attach
				+ ", status=" + status + ", createTime=" + createTime + "}";
	}
}
