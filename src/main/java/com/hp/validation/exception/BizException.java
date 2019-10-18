package com.hp.validation.exception;

import com.hp.validation.model.CodeMsg;
import lombok.Data;

/**
 * 业务异常
 *
 * @author hupan
 * @date 2019-09-16 16:03
 */
/**
 * 业务异常
 *
 * @author hupan
 * @date 2019-09-16 16:03
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -3135712444892078416L;

    private CodeMsg codeMsg;

    public BizException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

}