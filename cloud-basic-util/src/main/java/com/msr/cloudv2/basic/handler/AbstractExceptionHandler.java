package com.msr.cloudv2.basic.handler;

import cn.hutool.core.util.StrUtil;
import com.msr.cloudv2.basic.exption.SysStatusCode;
import com.msr.cloudv2.basic.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * 定义通用的统一异常处理，需要用到的，直接继承即可
 *
 * @author MaiShuRen
 * @site https://www.maishuren.top
 * @date 2022/4/28
 */
@Slf4j
public abstract class AbstractExceptionHandler {


    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.warn("HttpMessageNotReadableException:", ex);
        String message = ex.getMessage();
        if (StrUtil.containsAny(message, "Could not read document:")) {
            String msg = String.format("无法正确的解析json类型的参数：%s", StrUtil.subBetween(message, "Could not read document:", " at "));
            return R.result(SysStatusCode.PARAM_EX.getCode(), (Object) null, msg, ex.getMessage()).setPath(this.getPath());
        } else {
            return R.result(SysStatusCode.PARAM_EX.getCode(), (Object) null, SysStatusCode.PARAM_EX.getMsg(), ex.getMessage()).setPath(this.getPath());
        }
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> bindException(BindException ex) {
        log.warn("BindException:", ex);

        try {
            String msg = ((FieldError) Objects.requireNonNull(ex.getBindingResult().getFieldError())).getDefaultMessage();
            if (StrUtil.isNotEmpty(msg)) {
                return R.result(SysStatusCode.PARAM_EX.getCode(), (Object) null, msg, ex.getMessage()).setPath(this.getPath());
            }
        } catch (Exception var4) {
            log.debug("获取异常描述失败", var4);
        }

        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        fieldErrors.forEach((oe) -> {
            msg.append("参数:[").append(oe.getObjectName()).append(".").append(oe.getField()).append("]的传入值:[").append(oe.getRejectedValue()).append("]与预期的字段类型不匹配.");
        });
        return R.result(SysStatusCode.PARAM_EX.getCode(), (Object) null, msg.toString(), ex.getMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.warn("MethodArgumentTypeMismatchException:", ex);
        String msg = "参数：[" + ex.getName() + "]的传入值：[" + ex.getValue() + "]与预期的字段类型：[" + ((Class) Objects.requireNonNull(ex.getRequiredType())).getName() + "]不匹配";
        return R.result(SysStatusCode.PARAM_EX.getCode(), (Object) null, msg, ex.getMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> illegalStateException(IllegalStateException ex) {
        log.warn("IllegalStateException:", ex);
        return R.result(SysStatusCode.ILLEGAL_ARGUMENT_EX.getCode(), (Object) null, SysStatusCode.ILLEGAL_ARGUMENT_EX.getMsg(), ex.getMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.warn("MissingServletRequestParameterException:", ex);
        return R.result(SysStatusCode.ILLEGAL_ARGUMENT_EX.getCode(), (Object) null, "缺少必须的[" + ex.getParameterType() + "]类型的参数[" + ex.getParameterName() + "]", ex.getMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> nullPointerException(NullPointerException ex) {
        log.warn("NullPointerException:", ex);
        return R.result(SysStatusCode.NULL_POINT_EX.getCode(), (Object) null, SysStatusCode.NULL_POINT_EX.getMsg(), ex.getMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> illegalArgumentException(IllegalArgumentException ex) {
        log.warn("IllegalArgumentException:", ex);
        return R.result(SysStatusCode.ILLEGAL_ARGUMENT_EX.getCode(), (Object) null, SysStatusCode.ILLEGAL_ARGUMENT_EX.getMsg(), ex.getMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.warn("HttpMediaTypeNotSupportedException:", ex);
        MediaType contentType = ex.getContentType();
        return contentType != null ? R.result(SysStatusCode.MEDIA_TYPE_EX.getCode(), (Object) null, "请求类型(Content-Type)[" + contentType + "] 与实际接口的请求类型不匹配", ex.getMessage()).setPath(this.getPath()) : R.result(SysStatusCode.MEDIA_TYPE_EX.getCode(), (Object) null, "无效的Content-Type类型", ex.getMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({MissingServletRequestPartException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> missingServletRequestPartException(MissingServletRequestPartException ex) {
        log.warn("MissingServletRequestPartException:", ex);
        return R.result(SysStatusCode.REQUIRED_FILE_PARAM_EX.getCode(), (Object) null, SysStatusCode.REQUIRED_FILE_PARAM_EX.getMsg(), ex.getMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({ServletException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> servletException(ServletException ex) {
        log.warn("ServletException:", ex);
        String msg = "UT010016: Not a multi part request";
        return msg.equalsIgnoreCase(ex.getMessage()) ? R.result(SysStatusCode.REQUIRED_FILE_PARAM_EX.getCode(), (Object) null, SysStatusCode.REQUIRED_FILE_PARAM_EX.getMsg(), ex.getMessage()) : R.result(SysStatusCode.SYSTEM_BUSY.getCode(), (Object) null, ex.getMessage(), ex.getMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({MultipartException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> multipartException(MultipartException ex) {
        log.warn("MultipartException:", ex);
        return R.result(SysStatusCode.REQUIRED_FILE_PARAM_EX.getCode(), (Object) null, SysStatusCode.REQUIRED_FILE_PARAM_EX.getMsg(), ex.getMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn("MethodArgumentNotValidException:", ex);
        return R.result(SysStatusCode.BASE_VALID_PARAM.getCode(), (Object) null, ((FieldError) Objects.requireNonNull(ex.getBindingResult().getFieldError())).getDefaultMessage(), ex.getMessage()).setPath(this.getPath());
    }

    private String getPath() {
        String path = "";
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            path = request.getRequestURI();
        }

        return path;
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.warn("HttpRequestMethodNotSupportedException:", ex);
        return R.result(SysStatusCode.METHOD_NOT_ALLOWED.getCode(), (Object) null, SysStatusCode.METHOD_NOT_ALLOWED.getMsg(), ex.getMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({SQLException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> sqlException(SQLException ex) {
        log.warn("SQLException:", ex);
        return R.result(SysStatusCode.SQL_EX.getCode(), (Object) null, SysStatusCode.SQL_EX.getMsg(), ex.getMessage()).setPath(this.getPath());
    }

}
