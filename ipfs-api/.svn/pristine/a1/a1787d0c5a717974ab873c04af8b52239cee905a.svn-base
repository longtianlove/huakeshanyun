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
 * 广告信息管理表
 * </p>
 *
 * @author dp
 * @since 2018-10-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_banner")
public class TbBanner extends Model<TbBanner> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 广告名称
     */
	private String name;
    /**
     * 图片路径
     */
    @TableField("image_path")
	private String imagePath;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 创建时间
     */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
    /**
     * 结束时间
     */
	@TableField(value = "end_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date endTime;
    /**
     * 	当前状态(0:禁止1：启用)
     */
	private Integer state;
	/**
	 * 	广告地址
	 */
	 @TableField("link_address")
	private String linkAddress;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbAdvert{" +
			", id=" + id +
			", name=" + name +
			", imagePath=" + imagePath +
			", sort=" + sort +
			", createTime=" + createTime +
			", endTime=" + endTime +
			", state=" + state +
			"}";
	}
}
