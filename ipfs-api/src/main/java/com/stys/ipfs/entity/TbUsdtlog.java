package com.stys.ipfs.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_usdtlog")
public class TbUsdtlog extends Model<TbUsdtlog> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 时间
	 */
	@TableField("usdtlog_time")
	private Date usdtlogTime;
	/**
	 * 余额
	 */
	@TableField("usdtlog_balance")
	private Float usdtlogBalance;

	/**
	 * usdt主键
	 */
	@TableField("usdt_id")
	private Integer usdtId;
	/**
	 * 用户主键
	 */
	@TableField("user_id")
	private Integer userId;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbUsdtlog{" + ", id=" + id + ", usdtlogTime=" + usdtlogTime + ", usdtlogBalance=" + usdtlogBalance
				+ "}";
	}
}
