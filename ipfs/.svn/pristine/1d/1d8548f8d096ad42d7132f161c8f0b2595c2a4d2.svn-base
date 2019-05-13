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
 * 系统权限表
 * </p>
 *
 * @author Auto Generator
 * @since 2018-07-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor  
@TableName("sys_permission")
public class Permission extends Model<Permission> { 

    private static final long serialVersionUID = 1L;    

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 是否可用
     */
	private Integer available;
    /**
     * 权限名称
     */
    @TableField("permission_name")
	private String permissionName;
    /**
     * 父权限ID
     */
    @TableField("parent_id")
	private Integer parentId;
    /**
     * 父权限ID列表
     */
    @TableField("parent_ids")
	private String parentIds;
    /**
     * 权限编码
     */
    @TableField("permission_code")
	private String permissionCode;
    /**
     * 资源类型(menu/button)
     */
    @TableField("resource_type")
	private String resourceType;
    /**
     * 资源路径
     */
	private String url;
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
		return "Permission{" +
			", id=" + id +
			", available=" + available +
			", permissionName=" + permissionName +
			", parentId=" + parentId +
			", parentIds=" + parentIds +
			", permissionCode=" + permissionCode +
			", resourceType=" + resourceType +
			", url=" + url +
			", updateTime=" + updateTime +
			", createTime=" + createTime +
			"}";
	}
}
