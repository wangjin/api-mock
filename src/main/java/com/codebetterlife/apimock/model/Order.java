package com.codebetterlife.apimock.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author Jin Wang
 * @version 1.0
 * @date 2020-11-25 9:28 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("订单对象")
@TableName(value = "t_order")
public class Order {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty("名称")
    @Size(max = 50)
    private String name;

    @Min(1)
    @ApiModelProperty("数量")
    private Integer amount;

    @NotNull
    @ApiModelProperty("订单号")
    private String orderNo;

    @NotNull
    @ApiModelProperty("订单日期")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime orderDate;

    @ApiModelProperty("订单状态：1-已支付 2-已发货 3-取消订单 4-已完成")
    @Size(max = 50)
    private Integer status;
}
