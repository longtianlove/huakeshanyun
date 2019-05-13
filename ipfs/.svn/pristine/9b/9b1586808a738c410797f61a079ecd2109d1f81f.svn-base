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
 * @since 2019-02-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_order")
public class TbOrder extends Model<TbOrder> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 订单编号
     */
    @TableField("order_no")
	private String orderNo;
    /**
     * 订单价格
     */
    @TableField("order_price")
	private Float orderPrice;
    /**
     * 用户Id
     */
    @TableField("user_id")
	private Integer userId;
    /**
     * 订单创建时间
     */
    @TableField(value = "order_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date orderTime;
    /**
     * 商品Id
     */
    @TableField("product_id")
	private Integer productId;
    /**
     * 订单状态 0.未支付,1支付成功，2 支付失败
     */
    @TableField("order_status")
	private Integer orderStatus;
    /**
     * 商品数量
     */
    @TableField("product_num")
	private Integer productNum;
    /**
     * 打款凭证
     */
    @TableField("voucher_path")
    private String  voucherPath;
    
    @TableField(exist=false)
	private String nickname; //用户昵称
	 
	@TableField(exist=false)
	private String merchandiseName; //账变类型
	
	@TableField(exist=false)
	private TbProduct tbMerchandise; //商品
    
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbOrder{" +
			", id=" + id +
			", orderNo=" + orderNo +
			", orderPrice=" + orderPrice +
			", userId=" + userId +
			", orderTime=" + orderTime +
			", productId=" + productId +
			", orderStatus=" + orderStatus +
			", productNum=" + productNum +
			"}";
	}
}
