package com.msr.cloudv2.basic.handler;

import com.msr.cloudv2.basic.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 定义通用的统一异常处理，需要用到的，直接继承即可
 * 例如 {@link com.msr.cloudv2.gateway.handler.GatewayExceptionHandler}
 *
 * @author MaiShuRen
 * @site https://www.maishuren.top
 * @date 2022/4/28
 */
@Slf4j
public abstract class AbstractExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public R<?> forbiddenException(RuntimeException ex) {
        log.warn("BizException:", ex);
        return null;
//        return R.result(ex.getCode(), (Object) null, ex.getMessage(), ex.getLocalizedMessage()).setPath(this.getPath());
    }
}
