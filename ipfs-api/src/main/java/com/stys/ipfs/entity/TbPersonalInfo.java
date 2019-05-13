package com.stys.ipfs.entity;

import com.baomidou.mybatisplus.enums.IdType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

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
@TableName("tb_personal_info")
public class TbPersonalInfo extends Model<TbPersonalInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户外键
     */
    @TableField("user_id")
	private Integer userId;
    /**
     * 银行卡号
     */
    @TableField("bank_card")
	private String bankCard;
    /**
     * 银行名称
     */
    @TableField("bank_name")
    private String bankName;
    /**
     * 身份证号
     */
	private String idcard;
    /**
     * 手机号
     */
	private String phone;
    /**
     * 用户真实姓名
     */
    @TableField("real_name")
	private String realName;
    /**
     * 认证状态
     */
    @TableField("personal_status")
	private Integer personalStatus;
    /**
     * 身份证正面
     */
    @TableField("identity_img_front")
	private String identityImgFront;
    /**
     * 身份证反面
     */
    @TableField("identity_img_reverse")
	private String identityImgReverse;
    
    /**
     * 	认证信息
     */
    @TableField(exist=false)
    private String remark;
    
    @TableField(exist=false)
  	private String nickname; //用户昵称
    
    @TableField(exist=false)
  	private String accont; //用户账户

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbPersonalInfo{" +
			", id=" + id +
			", userId=" + userId +
			", bankCard=" + bankCard +
			", idcard=" + idcard +
			", phone=" + phone +
			", realName=" + realName +
			", personalStatus=" + personalStatus +
			"}";
	}
}
