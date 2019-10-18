package com.hp.validation.exception;


import com.hp.validation.model.CodeMsg;
import com.hp.validation.model.Result;
import com.sun.corba.se.impl.io.TypeMismatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    private ObjectError error;

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("系统异常", e);

        if (e instanceof BindException) {
            BindException ex = (BindException) e;
            String field = ex.getFieldErrors().get(0).getField();
            ObjectError error = ex.getAllErrors().get(0);

            if (error.contains(IllegalArgumentException.class)) {
                return Result.error(CodeMsg.INVALID_PARAMETERS.fillArgs(field));
            } else {
                return Result.error(CodeMsg.BIND_ERROR.fillArgs(error.getDefaultMessage()));
            }
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @ResponseBody
    @ExceptionHandler(BizException.class)
    public Result handleControllerException(HttpServletRequest request, Throwable e) {
        log.error("业务异常", e);

        if (e instanceof BizException) {
            BizException ex = (BizException) e;
            return Result.error(ex.getCodeMsg());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }

        //HttpStatus status = getStatus(request);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return HttpStatus.valueOf(statusCode);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Result<String> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s-%s", field, code);
        return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<String> handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException e) {
        String name = e.getParameterName();
        return Result.error(CodeMsg.BIND_ERROR.fillArgs(name + " parameter is missing"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<String> handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException ex) {
        String name = ex.getName();
        String type = ex.getRequiredType().getSimpleName();
        Object value = ex.getValue();
        String message = String.format("'%s' should be a valid '%s' and '%s' isn't", name, type, value);
        return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
    }
}