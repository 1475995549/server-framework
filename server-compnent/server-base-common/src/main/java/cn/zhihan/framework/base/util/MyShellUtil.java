package cn.zhihan.framework.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;

@Slf4j
public class MyShellUtil {
    
    public static class Result {
        
        
        public int status;
        public String read;
        
        private Result(int status) {
            this.status = status;
        }
        
        public boolean succ() {
            return status == 0;
        }
    }
    
    
    public static Result exec(String shell, String... params) {
        Result result = new Result(-1);
        try {
            String[] cmd = new String[params.length + 1];
            cmd[0] = shell;
            for (int i = 0; i < params.length; i++) {
                cmd[i + 1] = params[i];
            }
            log.info("[shell start]:================");
            log.info("[shell shell]:%s", shell);
            log.info("[shell param]:%s", StringUtils.join(params, " "));
            Process process = Runtime.getRuntime().exec(cmd);
            
            InputStream inputStream = process.getInputStream();
            String read = MyIOUtil.read(inputStream);
            result.read = read;
            log.info("[shell read]:%s", read);
            int status = process.waitFor();
            result.status = status;
            log.info("[shell status]:%d", status);
            log.info("[shell end]:================");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
