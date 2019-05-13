package com.stys.ipfs.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author dp
 * @since 2019-03-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_product")
public class TbProduct extends Model<TbProduct> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 名称
     */
    @TableField("product_name")
	private String productName;
    /**
     * 类型
     */
    @TableField("product_type")
	private Integer productType;
    /**
     * 销量
     */
    @TableField("product_sales")
	private Integer productSales;
    /**
     * 单价
     */
    @TableField("product_unit_price")
	private Float productUnitPrice;
    /**
     * 产品图片
     */
    @TableField("product_pictures")
	private String productPictures;
    /**
     * 基本信息
     */
    @TableField("product_detail")
	private String productDetail;
    /**
     * 详细配置
     */
    @TableField("prodcut_property")
	private String prodcutProperty;
    /**
     * 首页展示图片
     */
    @TableField("home_product_img")
	private String homeProductImg;
    
    /**
     * 特惠价格
     */
    @TableField("product_preferential_price")
    private Float productPreferentialPrice;
    
    /**
     * 售后服务信息
     */
    @TableField("after_sale_service")
    private String afterSaleService;
    /**
     *是否发布
     */
    @TableField("status")
    private Integer status;
    /**
     *热销状态(0否 1是)
     */
    @TableField("hot_sale_product")
    private Integer hotSaleProduct;
    /**
     *产品缩略图
     */
    @TableField("product_thumbnail")
    private String productThumbnail;
    /**
     *出局倍数
     */
    @TableField("out_number")
    private Integer outNumber;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TbProduct{" +
			", id=" + id +
			", productName=" + productName +
			", productType=" + productType +
			", hotSaleProduct=" + hotSaleProduct +
			", status=" + status +
			", productSales=" + productSales +
			", productUnitPrice=" + productUnitPrice +
			", productPictures=" + productPictures +
			", productDetail=" + productDetail +
			", prodcutProperty=" + prodcutProperty +
			", homeProductImg=" + homeProductImg +
			", afterSaleService=" + afterSaleService +
			", productPreferentialPrice=" + productPreferentialPrice +
			", outNumber=" + outNumber +
			"}";
	}
}
