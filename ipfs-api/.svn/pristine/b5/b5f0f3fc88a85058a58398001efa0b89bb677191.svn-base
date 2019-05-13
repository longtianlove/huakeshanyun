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
 * @since 2019-03-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_transfer_usdt")
public class TbTransferUsdt extends Model<TbTransferUsdt> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 交易编号
     */
	@TableField("txid")
	private String txid;
    /**
     * 发送usdt编号
     */
	private String fromaddress;
    /**
     * 主账号
     */
	private String toaddress;
    /**
     * 创建时间
     */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
    /**
     * 交易状态
     */
	private Integer status;
    /**
     * 余额
     */
	private Float balance;
    /**
     * 确认的节点数
     */
	private Integer confirmations;

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
		return "TbTransferUsdt{" +
			", id=" + id +
			", txid=" + txid +
			", fromAddress=" + fromaddress +
			", toAddress=" + toaddress +
			", createTime=" + createTime +
			", status=" + status +
			", balance=" + balance +
			", confirmations=" + confirmations +
			"}";
	}
}
