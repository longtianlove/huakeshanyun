package com.stys.ipfs.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author dp
 * @since 2019-04-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_co_creator")
public class TbCoCreator extends Model<TbCoCreator> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户id
     */
    @TableField("user_id")
	private Integer userId;
    
	@TableField(exist=false)
	private String phone; //账号
	
	@TableField(exist=false)
	private String realName; //真实姓名
    

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbCoCreator{" +
			", id=" + id +
			", userId=" + userId +
			"}";
	}
}
