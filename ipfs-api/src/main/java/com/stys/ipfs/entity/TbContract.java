package com.stys.ipfs.entity;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author dp
 * @since 2019-05-07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_contract")
public class TbContract extends Model<TbContract> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 合同编号
     */
    @TableField("contract_number")
	private String contractNumber;
    /**
     * 创建时间
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createDate;
    /**
     * 合同地址
     */
    @TableField("contract_address")
	private String contractAddress;
    /**
     * 用户id
     */
    @TableField("user_id")
	private Integer userId;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbContract{" +
			", id=" + id +
			", contractNumber=" + contractNumber +
			", createDate=" + createDate +
			", contractAddress=" + contractAddress +
			", userId=" + userId +
			"}";
	}
}
