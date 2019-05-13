package com.stys.ipfs.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbInviterExpericence implements Serializable, Comparable<TbInviterExpericence> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableField("tb_iviter")
	private Integer user_id; // 邀请人

	private String name;

	private String nickname;

	private String phone;

	private Integer experience;

	private String create_time;

	@Override
	public int compareTo(TbInviterExpericence o) {

		int flag = this.experience.compareTo(o.experience);
		if (flag == 0) {
			flag = o.experience - this.experience;
		}
		return flag;
	}

}