package com.example.aop.aop;

import com.example.aop.dto.User;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DecodeAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void pointCut() {
    }

    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    private void enableDecode() {

    }

    //pointCut이면서 enableDeocde인 지점에 적용
    @Before("pointCut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof User) {
                User user = (User) arg;
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email.getBytes()), StandardCharsets.UTF_8);
                user.setEmail(email);
            } else {
                //한번도 안 들어옴
                System.out.println(arg);
            }
        }
    }

    @AfterReturning(value = "pointCut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        if (returnObj instanceof User) {
            User user = (User) returnObj;
            String encodedEmail = Base64.getEncoder().encodeToString(user.getEmail().getBytes(StandardCharsets.UTF_8));
            user.setEmail(encodedEmail);
        }
    }
}
