//package com.exlservice.demoLogger.Logger;
//
//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//
//@Aspect
//@Component
//public class LoggerDemo {
//
//    public Logger logger = LoggerFactory.getLogger(LoggerDemo.class);
//
//    @Pointcut(value = "execution(* com.exlservice.demoLogger.Controller.*.*(..) )")
//    public void myPointcut() {
//    }
//
//    @Around("myPointcut()")
//    public Object appLogger(ProceedingJoinPoint pjr) throws Throwable {
//        String methodName = pjr.getSignature().getName();
//        String className = pjr.getTarget().getClass().toString();
//        ArrayList<String> list = new ArrayList<>();
//        list.add(methodName);
//        list.add(className);
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("methodName", methodName);
//        jsonObject.put("className", className);
//
//        logger.info("Logger object meta-data in JSON format (before API call): " + jsonObject.toString());
//        Object response = pjr.proceed();
//        logger.info("Logger object meta-data in JSON format (after API call): " + jsonObject.toString() + "\n" + "Response object: " + new JSONObject(response));
//
//        return response;
//    }
//
//}
