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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /** 
     * 角色ID
     */
    @TableField("role_id")
	private Integer roleId;
    /**
     * 名称
     */
	private String name;
    /**
     * 用户名
     */
    @TableField("user_name")
	private String userName;
    /**
     * 密码
     */
    @TableField("pass_word")
    @JsonIgnore
	private String passWord;
    /**
     * 盐值
     */
	@TableField("salt")
	@JsonIgnore
	private String salt;
	/**
	 * 手机
	 */
	@TableField("phone")
	private String phone;
    /**
     * 状态(0：禁用，1：启用，2：锁定)
     */
	@TableField("state")
	private Integer state;
    /**
     * 修改时间
     */
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;
    /**
     * 创建时间
     */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;

	 
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
			", id=" + id +
			", roleId=" + roleId +
			", name=" + name +
			", userName=" + userName +
			", passWord=" + passWord +
			", salt=" + salt +
			", state=" + state +
			", updateTime=" + updateTime +
			", createTime=" + createTime +
			", phone=" + phone +
			"}";
	}
}
