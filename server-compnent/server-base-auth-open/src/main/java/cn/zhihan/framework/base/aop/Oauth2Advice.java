package cn.zhihan.framework.base.aop;

import cn.zhihan.framework.base.constant.MyConstant;
import cn.zhihan.framework.base.util.MyJsonUtil;
import cn.zhihan.framework.base.vo.ApiVo;
import cn.zhihan.framework.base.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;


/**
 * 全局处理Oauth2抛出的异常
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Oauth2Advice implements ResponseBodyAdvice {
    
    @Autowired
    private HttpServletRequest request;
    
    //aspect-advice-interceptor-filter
    
    @ModelAttribute
    public void modelAttribute(Model model) {
    
    }
    
    @InitBinder
    public void initBinder(HttpServletRequest request, WebDataBinder binder) {
    
    }
    
    @ExceptionHandler(OAuth2Exception.class)
    public Result oAuth2ExceptionHandler(OAuth2Exception e) {
        log.info("[oAuth2ExceptionHandler]");
        return Result.failed(e.getMessage());
    }
    
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }
    
    
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (Result.class.isAssignableFrom(o.getClass())) {
            ApiVo apiVo = (ApiVo) request.getAttribute(MyConstant.apivo);
            if (apiVo != null) {
                log.info("[beforeBodyWrite]");
                apiVo.setResult(MyJsonUtil.write(o));
            }
        }
        return o;
    }
    
}
