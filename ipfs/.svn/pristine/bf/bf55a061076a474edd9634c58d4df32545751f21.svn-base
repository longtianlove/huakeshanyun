package com.stys.ipfs.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbInviteVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; // 主键ID

	private String iviterNickname; // 邀请人昵称

	private String iviteeNickname; // 被邀请人昵称
    
	@TableField("tb_iviter")
	private Integer tbIviter; //邀请人

    @TableField("tb_ivitee") //被邀请人
	private Integer tbIvitee;

    @TableField("parent_id")
    private Integer parentId;//父节点
    
    
    //邀请人手机
    private String phone;
    
    //被邀请人手机
    private String phone2;

    // 邀请人等级名称
    private String name;
    
    // 被邀请人等级名称
    private String name2;
}
