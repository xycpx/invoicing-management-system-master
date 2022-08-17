package com.px.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 报溢单商品表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_overflow_list_goods")
@ApiModel(value="OverflowListGoods对象", description="报溢单商品表")
public class OverflowListGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "型号")
    private String model;

    @ApiModelProperty(value = "商品名")
    private String name;

    @ApiModelProperty(value = "数量")
    private Integer num;

    @ApiModelProperty(value = "价格")
    private Float price;

    @ApiModelProperty(value = "总价")
    private Float total;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "报溢单id")
    private Integer overflowListId;

    @ApiModelProperty(value = "商品类别id")
    private Integer typeId;

    @ApiModelProperty(value = "商品id")
    private Integer goodsId;


}
