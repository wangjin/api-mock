package com.codebetterlife.apimock.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * @author Jin Wang
 * @version 1.0
 * @date 2020-11-25 9:28 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户对象")
@TableName(value = "t_user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty("姓名")
    @Size(max = 20)
    private String name;

    @Min(1)
    @Max(150)
    @ApiModelProperty("年龄")
    private Integer age;

    @NotNull
    @ApiModelProperty("地址")
    private String address;

    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户名")
    @Size(max = 50)
    private String username;

    @Size(min = 8, max = 50)
    @ApiModelProperty("密码")
    private String password;

}
