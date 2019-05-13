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
 * 系统登录日志表
 * </p>
 *
 * @author Auto Generator
 * @since 2018-10-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_login_log")
public class LoginLog extends Model<LoginLog> {

    private static final long serialVersionUID = 1L;

    /** 
     * 主键ID 
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户ID
     */
    @TableField("user_id")
	private Integer userId;
    /**
     * 用户名
     */
    @TableField("user_name")
	private String userName;
    /**
     * IP地址
     */
    @TableField("ip_address")
	private String ipAddress;
    /**
     * 地理位置
     */
    @TableField("geography_location")
	private String geographyLocation;
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
		return "LoginLog{" +
			", id=" + id +
			", userId=" + userId +
			", userName=" + userName +
			", ipAddress=" + ipAddress +
			", geographyLocation=" + geographyLocation +
			", updateTime=" + updateTime +
			", createTime=" + createTime +
			"}";
	}
}
