package cn.zhihan.framework.base.exception;

import cn.zhihan.framework.base.vo.Result;
import lombok.Data;

@Data
public class MyResultException extends RuntimeException {
    
    private Object[] codemessage;
    
    public MyResultException() {
        this.codemessage = Result.StatusCode.FAIL;
    }
    
    public MyResultException(Object[] codemessage) {
        this.codemessage = codemessage;
    }
    
    public MyResultException(int code, String message) {
        this(new Object[]{code, message});
    }
    
    public MyResultException(Object[] codemessage, String message) {
        this(new Object[]{codemessage[0], message});
    }
    
    
}
