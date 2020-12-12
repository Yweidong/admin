package com.haoyong.admin.advice;

import com.haoyong.admin.common.pojo.Result;
import com.haoyong.admin.exception.ResultException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

/**
 * @program: admin
 * @description: 全局异常处理
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-05 09:43
 **/
@RestControllerAdvice
public class ExceptAdvice {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Result<?>> exceptionHandler(Exception ex, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof ResultException ) {
            return this.handleResultException((ResultException) ex, headers, request);
        }

        //springsecrity 权限注解捕获异常
        if(ex instanceof AccessDeniedException) {
            return this.handleAuthException(ex,headers,request);
        }

        return this.handleException(ex, headers, request);
    }

    /** 对ResultException类返回返回结果的处理 */
    protected ResponseEntity<Result<?>> handleResultException(ResultException ex, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.failure(ex.getResultStatus(),ex.getMessage());
        HttpStatus status = ex.getResultStatus().getHttpStatus();
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    /** 异常类的统一处理 */
    protected ResponseEntity<Result<?>> handleException(Exception ex, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.failure();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    /** 权限类的统一处理 */
    protected ResponseEntity<Result<?>> handleAuthException(Exception ex, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.authfailure(ex.getMessage());
        HttpStatus status = HttpStatus.FORBIDDEN;
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }


    protected ResponseEntity<Result<?>> handleExceptionInternal(
            Exception ex, Result<?> body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }
}
