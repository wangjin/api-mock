package com.codebetterlife.apimock.handler;

import com.codebetterlife.apimock.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * @author Jin Wang
 * @version 1.0
 * @date 2020-12-21 5:24 下午
 */
@ControllerAdvice
@Slf4j
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "";
        BindingResult result = ex.getBindingResult();
        //组装校验错误信息
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            StringBuilder errorMsgBuffer = new StringBuilder();
            for (ObjectError error : list) {
                if (error instanceof FieldError) {
                    FieldError errorMessage = (FieldError) error;
                    errorMsgBuffer.append(String.format("字段[%s]", errorMessage.getField())).append(errorMessage.getDefaultMessage()).append(",");
                }
            }
            //返回信息格式处理
            message = errorMsgBuffer.substring(0, errorMsgBuffer.length() - 1);
        }

        return new ResponseEntity<>(R.fail(1, message), headers, status);
    }
}
