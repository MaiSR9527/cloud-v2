package com.msr.cloudv2.basic.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msr.cloudv2.basic.exption.SysStatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义通用的统一异常处理，需要用到的，直接继承即可
 *
 * @author MaiShuRen
 * @site https://www.maishuren.top
 * @since 2022/4/28
 */
public class R<T> {
    public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";
    public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";
    public static final int SUCCESS_CODE = 0;
    public static final int FAIL_CODE = -1;
    public static final int TIMEOUT_CODE = -2;
    public static final int VALID_EX_CODE = -9;
    public static final int OPERATION_EX_CODE = -10;
    private int code;
    @JsonIgnore
    private Boolean defExec;
    private T data;
    private String msg;
    private String path;
    private Map<Object, Object> extra;
    private long timestamp;
    private String errorMsg;

    private R() {
        this.defExec = true;
        this.msg = "ok";
        this.timestamp = System.currentTimeMillis();
        this.errorMsg = "";
        this.defExec = false;
        this.timestamp = System.currentTimeMillis();
    }

    public R(int code, T data, String msg) {
        this.defExec = true;
        this.msg = "ok";
        this.timestamp = System.currentTimeMillis();
        this.errorMsg = "";
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.defExec = false;
        this.timestamp = System.currentTimeMillis();
    }

    public R(int code, T data, String msg, String errorMsg) {
        this(code, data, msg);
        this.errorMsg = errorMsg;
    }

    public R(int code, T data, String msg, boolean defExec) {
        this(code, data, msg);
        this.defExec = defExec;
    }

    public static <E> R<E> result(int code, E data, String msg) {
        return new R(code, data, msg);
    }

    public static <E> R<E> result(int code, E data, String msg, String errorMsg) {
        return new R(code, data, msg, errorMsg);
    }

    public static <E> R<E> success(E data) {
        return new R(0, data, "ok");
    }

    public static R<Boolean> success() {
        return new R(0, true, "ok");
    }

    public static <E> R<E> successDef(E data) {
        return new R(0, data, "ok", true);
    }

    public static <E> R<E> successDef() {
        return new R(0, (Object)null, "ok", true);
    }

    public static <E> R<E> successDef(E data, String msg) {
        return new R(0, data, msg, true);
    }

    public static <E> R<E> success(E data, String msg) {
        return new R(0, data, msg);
    }

    public static <E> R<E> fail(int code, String msg) {
        return new R(code, (Object)null, msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试");
    }

    public static <E> R<E> fail(int code, String msg, String errorMsg) {
        return new R(code, (Object)null, msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试", errorMsg);
    }

    public static <E> R<E> fail(String msg) {
        return fail(-10, msg);
    }

    public static <E> R<E> fail(String msg, Object... args) {
        String message = msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试";
        return new R(-10, (Object)null, String.format(message, args));
    }

    public static <E> R<E> fail(SysStatusCode exceptionCode) {
        return validFail(exceptionCode);
    }

    public static <E> R<E> fail(BizException exception) {
        return exception == null ? fail("系统繁忙，请稍候再试") : new R(exception.getCode(), (Object)null, exception.getMessage(), exception.getMessage());
    }

    public static <E> R<E> fail(Throwable throwable) {
        String msg = throwable != null ? throwable.getMessage() : "系统繁忙，请稍候再试";
        return fail(-1, msg, msg);
    }

    public static <E> R<E> validFail(String msg) {
        return new R(-9, (Object)null, msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试");
    }

    public static <E> R<E> validFail(String msg, Object... args) {
        String message = msg != null && !msg.isEmpty() ? msg : "系统繁忙，请稍候再试";
        return new R(-9, (Object)null, String.format(message, args));
    }

    public static <E> R<E> validFail(BaseExceptionCode exceptionCode) {
        return new R(exceptionCode.getCode(), (Object)null, exceptionCode.getMsg() != null && !exceptionCode.getMsg().isEmpty() ? exceptionCode.getMsg() : "系统繁忙，请稍候再试");
    }

    public static <E> R<E> timeout() {
        return fail(-2, "请求超时，请稍候再试");
    }

    public R<T> put(String key, Object value) {
        if (this.extra == null) {
            this.extra = new HashMap(16);
        }

        this.extra.put(key, value);
        return this;
    }

    public R<T> putAll(Map<Object, Object> extra) {
        if (this.extra == null) {
            this.extra = new HashMap(16);
        }

        this.extra.putAll(extra);
        return this;
    }

    public Boolean getIsSuccess() {
        return this.code == 0 || this.code == 200;
    }

    public String toString() {
        return JsonUtil.toJson(this);
    }

    public int getCode() {
        return this.code;
    }

    public Boolean getDefExec() {
        return this.defExec;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getPath() {
        return this.path;
    }

    public Map<Object, Object> getExtra() {
        return this.extra;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public R<T> setCode(int code) {
        this.code = code;
        return this;
    }

    @JsonIgnore
    public R<T> setDefExec(Boolean defExec) {
        this.defExec = defExec;
        return this;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }

    public R<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public R<T> setPath(String path) {
        this.path = path;
        return this;
    }

    public R<T> setExtra(Map<Object, Object> extra) {
        this.extra = extra;
        return this;
    }

    public R<T> setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public R<T> setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
