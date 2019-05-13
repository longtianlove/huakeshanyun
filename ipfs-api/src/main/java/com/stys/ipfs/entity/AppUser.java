
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
 * @since 2019-03-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_app_user")
public class AppUser extends Model<AppUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 状态（0,冻结、1,启用）
     */
	private Integer state;
    /**
     * 创建时间
     */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
    /**
     * 电子邮件
     */
	private String email;
	/**
	 * 手机号码
	 */
	private String phone;
    /**
     * 等级
     */
    @TableField("dic_id")
	private Integer dicId;
    /**
     * 密码
     */
	private String password;
	/**
	 * 支付密码
	 */
	 @TableField("payment_code")
	private String paymentCode;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AppUser{" +
			", id=" + id +
			", state=" + state +
			", createTime=" + createTime +
			", email=" + email +
			", dicId=" + dicId +
			", password=" + password +
			", paymentCode=" + paymentCode +
			", phone=" + phone +
			"}";
	}
}