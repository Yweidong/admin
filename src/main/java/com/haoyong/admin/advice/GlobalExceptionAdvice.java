package com.haoyong.admin.advice;


import com.haoyong.admin.Enum.CommonEnum;
import com.haoyong.admin.common.ResultBody;
import com.haoyong.admin.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: preview
 * @description: 自定义全局异常处理类
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-28 19:09
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {
    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResultBody bizExceptionHandler(HttpServletRequest req, BizException e){
        log.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR,e.getMessage());
    }

    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, AccessDeniedException e){
        log.error("权限不足:",e);
        return ResultBody.error(CommonEnum.NO_AUTH,e.getMessage());
    }
}
