package com.hp.validation.model;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 接口响应对象
 *
 * @author hupan
 * @date 2019/10/18
 */
@Getter
@ToString
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    private Result(CodeMsg codeMsg) {
        if (codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }

    /**
     * 成功时候的调用
     */
    public static <T> Result<T> success() {
        return new Result<>(CodeMsg.SUCCESS);
    }

    /**
     * 成功时候的调用
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), data);
    }

    /**
     * 失败时候的调用
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<>(codeMsg);
    }

    /**
     * 判断是否成功
     */
    public boolean isSuccess() {
        return this.code == CodeMsg.SUCCESS.getCode();
    }

}