package com.wangxiaobao.estate.facedetect.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 2018/10/31 14:17.
 *
 * @author xiaobo.xiong
 */
@Slf4j
@RestControllerAdvice
public class GlobalAdvice {

    @Autowired
    private ObjectMapper objectMapper;


    public GlobalAdvice() {
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage<Void> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        log.error("method: {} uri: {} errMsg: {} params:{}", request.getMethod(), uri, ex.getMessage(), this.buildParamsStr(request), ex);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        return new ResponseMessage<>(String.valueOf(HttpStatus.BAD_REQUEST.value()), uri, ex.getMessage(), null);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage<Void> handleIllegalArgumentException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        log.error("method: {} uri: {} errMsg: {} params:{}", request.getMethod(), uri, ex.getMessage(), this.buildParamsStr(request), ex);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        return new ResponseMessage<>(String.valueOf(HttpStatus.BAD_REQUEST.value()), uri, "参数异常", null);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage<Object> constraintViolationException(ConstraintViolationException ex, HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        HashSet messages = new HashSet(constraintViolations.size());
        log.error("method: {} uri: {} errMsg: {} params:{}", request.getMethod(), uri, messages, this.buildParamsStr(request), ex);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        Map a = constraintViolations.stream().collect(Collectors.toMap(k -> k.getPropertyPath().toString(), ConstraintViolation::getMessage));
        return new ResponseMessage<Object>(String.valueOf(HttpStatus.BAD_REQUEST.value()), uri, "Resource Not Found", a);
    }

    @ExceptionHandler({NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseMessage<Void> noHandlerFoundException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        log.error("method: {} uri: {} errMsg: {} params:{}", request.getMethod(), uri, ex.getMessage(), this.buildParamsStr(request), ex);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        return new ResponseMessage<>(String.valueOf(HttpStatus.NOT_FOUND.value()), uri, "Resource Not Found", null);
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage<Void> beanPropertyBindingResult(BindException ex, HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        log.error("method: {} uri: {} errMsg: {} params:{}", request.getMethod(), uri, ex.getMessage(), this.buildParamsStr(request), ex);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        String message = ex.getAllErrors().stream().findFirst().get().getDefaultMessage();
        message = !message.contains("IllegalArgumentException") ?message:"参数异常";
        return new ResponseMessage<>(String.valueOf(HttpStatus.BAD_REQUEST.value()), uri, message, null);
    }


    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage<Void> invalidFormatException(HttpMessageNotReadableException ex, HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        log.error("method: {} uri: {} errMsg: {} params:{}", request.getMethod(), uri, ex.getMessage(), this.buildParamsStr(request), ex);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        return new ResponseMessage<>(String.valueOf(HttpStatus.BAD_REQUEST.value()), uri, "参数异常", null);
    }


    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseMessage<Void> handleException(Throwable throwable,
                                                    HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        log.error(throwable.getMessage(), throwable);
        log.error("method: {} uri: {} errMsg: {} params:{}", request.getMethod(), uri, throwable.getMessage(), this.buildParamsStr(request), throwable);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        return new ResponseMessage<>(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), uri, "服务器异常", null);
    }


    private String buildParamsStr(HttpServletRequest request) {
        try {
            return this.objectMapper.writeValueAsString(request.getParameterMap());
        } catch (JsonProcessingException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseMessage<Void> multipartExceptionHandler(MultipartException e,HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseMessage<>(String.valueOf(HttpStatus.BAD_REQUEST.value()), uri, e.getCause().getMessage(), null);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseMessage<Void> runtimeExceptionHandler(RuntimeException e,HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseMessage<>(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), uri, Optional.ofNullable(e.getCause()).map(Throwable::getMessage).orElse("内部服务错误"), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public List<ErrorMessage> exception(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<ErrorMessage> errorMessages = new ArrayList<>();

        allErrors.forEach(objectError -> {
            ErrorMessage errorMsg = new ErrorMessage();
            FieldError fieldError = (FieldError) objectError;
            errorMsg.setField(fieldError.getField());
            errorMsg.setObjectName(fieldError.getObjectName());
            errorMsg.setMessage(fieldError.getDefaultMessage());
            errorMessages.add(errorMsg);
        });
        return errorMessages;
    }

}
