package com.stys.ipfs.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
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
 * @since 2019-03-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_withdrawad_log")
public class TbWithdrawadLog extends Model<TbWithdrawadLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 提现金额
     */
    @TableField("cash_number")
	private Float cashNumber;
    /**
     * 用户外键
     */
    @TableField("user_id")
	private Integer userId;
    /**
     * 用户银行账户
     */
    @TableField("user_account")
	private String userAccount;
    /**
     * 审核状态（0、待审核 1、通过 2、不通过）
     */
    @TableField("cash_status")
	private Integer cashStatus;
    /**
     * 实际扣除
     */
    @TableField("actual_amount")
	private Float actualAmount;
    /**
     * 提现发起时间
     */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
    /**
     * 用户姓名
     */
    @TableField("user_name")
	private String userName;
    /**
     * 审核信息
     */
    @TableField("verify_info")
    private String verifyInfo;
    /**
     * 手续费
     */
	private Float handfree;

	@TableField(exist=false)
	private String phone; //账号
	
	@TableField(exist=false)
	private String nickname; //昵称
	
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbWithdrawadLog{" +
			", id=" + id +
			", cashNumber=" + cashNumber +
			", userId=" + userId +
			", userAccount=" + userAccount +
			", cashStatus=" + cashStatus +
			", actualAmount=" + actualAmount +
			", createTime=" + createTime +
			", userName=" + userName +
			", handfree=" + handfree +
			"}";
	}
}
