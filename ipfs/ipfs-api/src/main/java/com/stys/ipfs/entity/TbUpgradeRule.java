package com.stys.ipfs.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author dp
 * @since 2019-03-29
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@TableName("tb_upgrade_rule")
public class TbUpgradeRule extends Model<TbUpgradeRule> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 字典主键
     */
    @TableField("dic_id")
	private Integer dicId;
    /**
     * 分红比例
     */
	private Float percent;
    /**
     * 累积业绩
     */
	private Integer experience;
    /**
     * 小区业绩
     */
    @TableField("limit_experience")
	private Integer limitExperience;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbUpgradeRule{" +
			", id=" + id +
			", dicId=" + dicId +
			", percent=" + percent +
			", experience=" + experience +
			", limitExperience=" + limitExperience +
			"}";
	}
}
