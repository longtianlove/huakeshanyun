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
 * 
 * </p>
 *
 * @author dp
 * @since 2018-10-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_dic")
public class Dic extends Model<Dic> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id; 
    /**
     * 分组名称
     */
    @TableField("group_name")
    private String groupName;
    /**
     * 字典名称
     */
    private String name;
    /**
     * 字典值
     */
    private String value1;
    /**
     * 字典值2
     */
    private String value2;
    /**
     * 排序
     */
    private String sort;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;  
    /**
     * 状态（0：禁用，1：启用）
     */
    private Integer state; 
    
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Dic{" +
        ", id=" + id +
        ", groupName=" + groupName +
        ", name=" + name +
        ", value1=" + value1 +
        ", value2=" + value2 +
        ", sort=" + sort +
        ", createTime=" + createTime +
        ", state=" + state +
        "}";
    }
}
