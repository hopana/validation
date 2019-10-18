package com.hp.validation.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务响应对象
 *
 * @author hupan
 * @date 2019/10/18
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeMsg {

    /**
     * 状态码
     */
    private int code;
    /**
     * 错误消息
     */
    private String msg;

    /**
     * 通用的错误码
     */
    public static final CodeMsg SUCCESS = new CodeMsg(200, "success");

    public static final CodeMsg PARAM_ERROR = new CodeMsg(400, "参数错误");
    public static final CodeMsg CONTENT_NOT_FOUND = new CodeMsg(404, "主贴不存在");

    public static final CodeMsg NO_PIC = new CodeMsg(400, "未上传图片");
    public static final CodeMsg NO_PIC_VIDEO = new CodeMsg(400, "图片和视频不能同时为空");

    public static final CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static final CodeMsg SIGN_ERROR = new CodeMsg(500101, "签名错误");
    public static final CodeMsg EMPTY_TOKEN = new CodeMsg(500102, "TOKEN为空");
    public static final CodeMsg AUTH_FAIL = new CodeMsg(500103, "认证失败");
    public static final CodeMsg INVALID_PARAMETERS = new CodeMsg(500104, "参数不合法：%s");
    public static final CodeMsg BIND_ERROR = new CodeMsg(500105, "参数校验异常：%s");
    public static final CodeMsg REQUEST_ILLEGAL = new CodeMsg(500106, "请求非法");
    public static final CodeMsg ACCESS_LIMIT_REACHED = new CodeMsg(500107, "访问太频繁！");
    public static final CodeMsg UPLOAD_FAILED = new CodeMsg(500108, "文件上传失败！");

    /**
     * 登录模块 5002XX
     */
    public static final CodeMsg NOT_REGISTER = new CodeMsg(500210, "用户未注册");
    public static final CodeMsg SESSION_ERROR = new CodeMsg(500211, "Session不存在或者已经失效");
    public static final CodeMsg PASSWORD_EMPTY = new CodeMsg(500212, "登录密码不能为空");
    public static final CodeMsg MOBILE_EMPTY = new CodeMsg(500213, "手机号不能为空");
    public static final CodeMsg MOBILE_ERROR = new CodeMsg(500214, "手机号格式错误");
    public static final CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500215, "手机号不存在");
    public static final CodeMsg USERNAME_OR_PASSWORD_ERROR = new CodeMsg(500216, "用户名或密码错误");

    //秒杀模块 5005XX
    public static final CodeMsg MIAO_SHA_OVER = new CodeMsg(500500, "商品已经秒杀完毕");
    public static final CodeMsg REPEATE_MIAOSHA = new CodeMsg(500501, "不能重复秒杀");
    public static final CodeMsg MIAOSHA_FAIL = new CodeMsg(500502, "秒杀失败");

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

}