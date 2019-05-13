package com.stys.ipfs.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

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
@TableName("sys_app_userinfo")
public class AppUserinfo extends Model<AppUserinfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 昵称
     */
	private String nickname;
    /**
     * 头像路径
     */
	private String avater;
    /**
     * 邀请人邀请码
     */
    @TableField("invitation_code")
	private String invitationCode;
    /**
     * 外键
     */
    @TableField("user_id")
	private Integer userId;
    /**
     * 邀请码
     */
	private String code;
    /**
     * 邀请码图像路径
     */
    @TableField("code_path")
	private String codePath;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AppUserinfo{" +
			", id=" + id +
			", nickname=" + nickname +
			", avater=" + avater +
			", invitationCode=" + invitationCode +
			", userId=" + userId +
			", code=" + code +
			", codePath=" + codePath +
			"}";
	}
}
