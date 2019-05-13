package com.stys.ipfs.dto;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author dp
 * @since 2019-03-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbUsdtVO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 地址
	 */
	private String usdtAddr;

	/**
	 * 私钥
	 */
	private String usdtPrivatekey;

	/**
	 * 余额
	 */
	private Float usdtBalance;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 状态 1转账成功 0 转账失败 2 已经发送给总账户
	 */
	private Integer status;

	/**
	 * 二维码图片路径
	 */
	@TableField("address_imgpath")
	private String addressImgpath;

	/**
	 * userid
	 */
	@TableField("user_id")
	private Integer userId;

	@TableField("usdt_id")
	private Integer usdtId;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 昵称
	 */
	@TableField("nickname")
	private String nickname;

}
