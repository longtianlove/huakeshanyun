package com.stys.ipfs.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author dp
 * @since 2019-04-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_feimauser")
public class TbFeimauser extends Model<TbFeimauser> {

	private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 飞码用户Id
	 */
	private String account;
	/**
	 * app用户id
	 */
	@TableField("user_id")
	private Integer userId;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbFeimauser{" + ", id=" + id + ", account=" + account + ", userId=" + userId + "}";
	}
}
