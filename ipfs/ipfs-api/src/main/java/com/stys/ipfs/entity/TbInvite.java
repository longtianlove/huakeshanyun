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
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 邀请表
 * </p>
 *
 * @author dp
 * @since 2018-10-29
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@TableName("tb_invite")
public class TbInvite extends Model<TbInvite> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 邀请人
	 */
	@TableField("tb_iviter")
	private Integer tbIviter;
	/**
	 * 被邀请人
	 */
	@TableField("tb_ivitee")
	private Integer tbIvitee;
	/**
	 * 父节点
	 */
	@TableField("parent_id")
	private Integer parentId;

//	/**
//	 * 回填状态
//	 */
//	@TableField("status")
//	private Integer status;
//	/**
//	 * 邀请时间
//	 */
//	@TableField(value = "create_time", fill = FieldFill.INSERT)
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//	private Date createTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbInvite{" + ", id=" + id + ", tbIviter=" + tbIviter + ", tbIvitee=" + tbIvitee + "}";
	}
}
