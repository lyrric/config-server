package com.github.lyrric.plugin;

import com.github.lyrric.model.BusinessException;
import com.github.lyrric.model.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理异常
     * @param e 异常
     * @return 统一返回
     */
    @ExceptionHandler({Exception.class})
    public HttpResult allException(Exception e) {
       if (e instanceof BusinessException) {
            //业务异常处理
            return HttpResult.failure(e.getMessage());
        } else {
            logger.error("系统发生了不可预知异常",e);
            return HttpResult.error();
        }
    }
}
