package com.stys.ipfs.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * <p>
 * 
 * </p>
 *
 * @author dp
 * @since 2019-03-15
 */
@TableName("tb_backfill")
public class TbBackfill extends Model<TbBackfill> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 回填值
     */
    @TableField("backfill_value")
	private Float backfillValue;
    /**
     * 回填结束时间
     */
    @TableField("backfill_time")
	private Date backfillTime;
    /**
     * 创建时间
     */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
    /**
     * 用户编号
     */
    @TableField("user_id")
	private Integer userId;
    /**
     * 回填状态
     */
	private String status;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getBackfillValue() {
		return backfillValue;
	}

	public void setBackfillValue(Float backfillValue) {
		this.backfillValue = backfillValue;
	}

	public Date getBackfillTime() {
		return backfillTime;
	}

	public void setBackfillTime(Date backfillTime) {
		this.backfillTime = backfillTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbBackfill{" +
			", id=" + id +
			", backfillValue=" + backfillValue +
			", backfillTime=" + backfillTime +
			", createTime=" + createTime +
			", userId=" + userId +
			", status=" + status +
			"}";
	}
}
