package com.stys.ipfs.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 资产明细表
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_assets_detail")
public class TbAssetsDetail extends Model<TbAssetsDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键UUID
     */
	private String id;
    /**
     * 用户外键
     */
    @TableField("user_id")
	private Integer userId;
    /**
     * 账变前金额
     */
    @TableField("before_amount")
	private Double beforeAmount;
    /**
     * 账变金额
     */
	private Double amount;
    /**
     * 账变后金额
     */
    @TableField("after_amount")
	private Double afterAmount;
    /**
     * 账变类型
     */
	private Integer type;
    /**
     * 创建时间
     */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
    /**
     * 收支类型(0收入,1支出)
     */
    @TableField("in_or_out")
	private Integer inOrOut;
    /**
     * 回馈返利
     */
    @TableField("sun_user_id")
	private Integer sunUserId;
    /**
     * 账务类型（1、金币2、推广礼包3、超级矿工）
     */
    @TableField("account_type")
    private Integer accountType;
    
    @TableField(exist=false)
  	private String nickname; //用户昵称
    
    @TableField(exist=false)
  	private String sunname; //返利用户昵称
    
    @TableField(exist=false)
    private String phone; //用户账户
    
    @TableField(exist=false)
    private String name; //账变类型名称
    
    @TableField(exist=false)
    private String realName; //用户真实姓名

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbAssetsDetail{" +
			", id=" + id +
			", userId=" + userId +
			", beforeAmount=" + beforeAmount +
			", amount=" + amount +
			", afterAmount=" + afterAmount +
			", type=" + type +
			", createTime=" + createTime +
			", inOrOut=" + inOrOut +
			", sunUserId=" + sunUserId +
			", accountType=" + accountType +
			"}";
	}
}
