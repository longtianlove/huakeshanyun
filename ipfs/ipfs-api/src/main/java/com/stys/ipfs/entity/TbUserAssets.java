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
@TableName("tb_user_assets")
public class TbUserAssets extends Model<TbUserAssets> {

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
     * 金币
     */
	private Float coin;
    /**
     * 存储
     */
	private Integer storage;
    /**
     * 推广金币
     */
    @TableField("gift_coin")
	private Float giftCoin;
    
    @TableField("limit_coin")
    private Float limitCoin;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbUserAssets{" +
			", id=" + id +
			", userId=" + userId +
			", coin=" + coin +
			", storage=" + storage +
			", giftCoin=" + giftCoin +
			"}";
	}
}