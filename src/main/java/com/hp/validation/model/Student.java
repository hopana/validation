package com.hp.validation.model;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

/**
 * 待校验的Model
 *
 * @author hupan
 * @date 2019/10/18
 */
@Data
public class Student implements Serializable {

    private static final long serialVersionUID = -2426591526038126741L;

    @NotNull(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Positive(message = "年龄必须为正数")
    private Integer age;

    @NotNull(message = "分数不能为空")
    @Digits(integer = 2, fraction = 2, message = "分数格式不对")
    private Float score;

    private String mobile;

    private String address;


}
