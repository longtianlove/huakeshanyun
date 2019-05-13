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
 * @since 2019-03-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_usdt_user")
public class TbUsdtUser extends Model<TbUsdtUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * usdt主键
     */
    @TableField("usdt_id")
	private Integer usdtId;
    /**
     * 用户主键
     */
    @TableField("user_id")
	private Integer userId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbUsdtUser{" +
			", id=" + id +
			", usdtId=" + usdtId +
			", userId=" + userId +
			"}";
	}
}