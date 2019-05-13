package com.stys.ipfs.dto;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExperienceVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private Integer id;
    //用户id
    private Integer userId;
    //用户累积存储
    private Integer experience;
    //用户累积存储
    private String  nickname;
    //创建时间
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
