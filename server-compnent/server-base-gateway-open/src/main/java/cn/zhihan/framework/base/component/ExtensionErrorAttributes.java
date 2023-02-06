package cn.zhihan.framework.base.component;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import cn.zhihan.framework.base.vo.Result;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.MergedAnnotations.SearchStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * description: 自定义全局异常处理 重写并替代 DefaultErrorAttributes
 * date: 2021/9/8 9:24 上午
 * version: 1.0
 * author: suzui
 */

public class ExtensionErrorAttributes implements ErrorAttributes {
    private static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";
    private final boolean includeException;
    
    public ExtensionErrorAttributes() {
        this(false);
    }
    
    public ExtensionErrorAttributes(boolean includeException) {
        this.includeException = includeException;
    }
    
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap();
        errorAttributes.put("timestamp", new Date());
        errorAttributes.put("path", request.path());
        Throwable error = this.getError(request);
        MergedAnnotation<ResponseStatus> responseStatusAnnotation = MergedAnnotations.from(error.getClass(), SearchStrategy.TYPE_HIERARCHY).get(ResponseStatus.class);
        HttpStatus errorStatus = this.determineHttpStatus(error, responseStatusAnnotation);
        errorAttributes.put("status", errorStatus.value());
        errorAttributes.put("error", errorStatus.getReasonPhrase());
        errorAttributes.put("message", this.determineMessage(error, responseStatusAnnotation));
        errorAttributes.put("requestId", request.exchange().getRequest().getId());
        this.handleException(errorAttributes, this.determineException(error), includeStackTrace);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status",errorStatus.value());
        //result.put("status", Result.Status.FAIL);
        result.put("code", Result.Status.FAILCODE);
        result.put("message", errorStatus.getReasonPhrase());
        result.put("data", null);
        result.put("systemTime", System.currentTimeMillis());
        return result;
        //return errorAttributes;
    }
    
    private HttpStatus determineHttpStatus(Throwable error, MergedAnnotation<ResponseStatus> responseStatusAnnotation) {
        return error instanceof ResponseStatusException ? ((ResponseStatusException) error).getStatus() : (HttpStatus) responseStatusAnnotation.getValue("code", HttpStatus.class).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private String determineMessage(Throwable error, MergedAnnotation<ResponseStatus> responseStatusAnnotation) {
        if (error instanceof WebExchangeBindException) {
            return error.getMessage();
        } else if (error instanceof ResponseStatusException) {
            return ((ResponseStatusException) error).getReason();
        } else {
            String reason = (String) responseStatusAnnotation.getValue("reason", String.class).orElse("");
            if (StringUtils.hasText(reason)) {
                return reason;
            } else {
                return error.getMessage() != null ? error.getMessage() : "";
            }
        }
    }
    
    private Throwable determineException(Throwable error) {
        if (error instanceof ResponseStatusException) {
            return error.getCause() != null ? error.getCause() : error;
        } else {
            return error;
        }
    }
    
    private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        errorAttributes.put("trace", stackTrace.toString());
    }
    
    private void handleException(Map<String, Object> errorAttributes, Throwable error, boolean includeStackTrace) {
        if (this.includeException) {
            errorAttributes.put("exception", error.getClass().getName());
        }
        
        if (includeStackTrace) {
            this.addStackTrace(errorAttributes, error);
        }
        
        if (error instanceof BindingResult) {
            BindingResult result = (BindingResult) error;
            if (result.hasErrors()) {
                errorAttributes.put("errors", result.getAllErrors());
            }
        }
        
    }
    
    @Override
    public Throwable getError(ServerRequest request) {
        return (Throwable) request.attribute(ERROR_ATTRIBUTE).orElseThrow(() -> {
            return new IllegalStateException("Missing exception attribute in ServerWebExchange");
        });
    }
    
    @Override
    public void storeErrorInformation(Throwable error, ServerWebExchange exchange) {
        exchange.getAttributes().putIfAbsent(ERROR_ATTRIBUTE, error);
    }
}
