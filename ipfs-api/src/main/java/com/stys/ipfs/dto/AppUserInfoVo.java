package com.stys.ipfs.dto;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserInfoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableField("email")
	private String email;

	@TableField("phone")
	private String phone;

	@TableField("dic_id")
	private Integer dicId;
	/**
	 * 支付密码
	 */
	@TableField("payment_code")
	private String paymentCode;

	/**
	 * 昵称
	 */
	@TableField("nickname")
	private String nickname;
	/**
	 * 头像路径
	 */
	@TableField("avater")
	private String avater;

	/**
	 * 邀请人邀请码
	 */
	@TableField("invitation_code")
	private String invitationCode;
	/**
	 * 外键
	 */
	@TableField("user_id")
	private Integer userId;
	/**
	 * 邀请码
	 */
	@TableField("code")
	private String code;
	/**
	 * 邀请码图像路径
	 */
	@TableField("code_path")
	private String codePath;

	/**
	 * 金币
	 */
	@TableField("coin")
	private Float coin;
	/**
	 * 存储
	 */
	@TableField("storage")
	private Integer storage;
	/**
	 * 推广金币
	 */
	@TableField("gift_coin")
	private Float giftCoin;

	@TableField("limit_coin")
	private Float limitCoin;

	/**
	 * 认证状态
	 */
	@TableField("personal_status")
	private Integer personalStatus;

	/**
	 * 二维码
	 */
	@TableField("usdtImgpath")
	private String usdtImgpath;

	/**
	 * 地址
	 */
	@TableField("usdt_addr")
	private String usdtAddr;

	/**
	 * 用户等级
	 */
	@TableField("lv")
	private String lv;

	@TableField("vipminer_status")
	private Integer vipminerStatus;

	@TableField("account")
	private String account;

	/**
	 * USDT手续费
	 */
	private String USDT_handerFee;

	/**
	 * 最小提现数量
	 */
	private String USDT_min_amount;

	/**
	 * 飞码通手续费
	 */
	private String feima_handerFee;

}