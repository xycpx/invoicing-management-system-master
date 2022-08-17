package com.px.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_goods")
@ApiModel(value="Goods对象", description="商品表")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品编码")
    private String code;

    @ApiModelProperty(value = "库存数量")
    private Integer inventoryQuantity;

    @ApiModelProperty(value = "库存下限")
    private Integer minNum;

    @ApiModelProperty(value = "商品型号")
    private String model;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "生产产商")
    private String producer;

    @ApiModelProperty(value = "采购价格")
    private Float purchasingPrice;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "出售价格")
    private Float sellingPrice;

    @ApiModelProperty(value = "商品单位")
    private String unit;

    @ApiModelProperty(value = "商品类别")
    private Integer typeId;

    //0 初始化状态  1 期初库存入仓库  2 有进货或者销售单据
    @ApiModelProperty(value = "商品状态")
    private Integer state;

    @ApiModelProperty(value = "上次采购价格")
    private Float lastPurchasingPrice;

    @ApiModelProperty(value = "是否删除")
    private Integer isDel;

    @ApiModelProperty(value = "是否删除")
    @TableField(exist = false)
    private String unitName;

    @ApiModelProperty(value = "是否删除")
    @TableField(exist = false)
    private String typeName;

    @ApiModelProperty(value = "销售总数")
    @TableField(exist = false)
    private Integer saleTotal;


}
