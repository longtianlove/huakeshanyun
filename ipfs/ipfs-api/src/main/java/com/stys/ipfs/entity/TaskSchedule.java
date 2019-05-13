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
 * 系统任务调度表
 * </p>
 *
 * @author dp
 * @since 2018-10-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_task_schedule")
public class TaskSchedule extends Model<TaskSchedule> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 任务名称
     */
    @TableField("job_name")
	private String jobName;
    /**
     * 任务分组
     */
    @TableField("job_group")
	private String jobGroup;
    /**
     * 任务别名
     */
    @TableField("alias_name")
	private String aliasName;
    /**
     * 任务路径
     */
    @TableField("job_class")
	private String jobClass;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 规则说明
     */
	private String cronexpression;
    /**
     * 描述
     */
	private String description;
    /**
     * 创建时间
     */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;
    /**
     * 规则说明解释
     */
    @TableField("cronexpression_explain")
	private String cronexpressionExplain;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TaskSchedule{" +
			", id=" + id +
			", jobName=" + jobName +
			", jobGroup=" + jobGroup +
			", aliasName=" + aliasName +
			", jobClass=" + jobClass +
			", status=" + status +
			", cronexpression=" + cronexpression +
			", description=" + description +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", cronexpressionExplain=" + cronexpressionExplain +
			"}";
	}
}
