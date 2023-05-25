package com.exlservice.demoLogger.Layout;

import java.text.SimpleDateFormat;
import java.util.*;

import ch.qos.logback.contrib.json.classic.JsonLayout;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

//@Aspect
@Component
public class CustomJsonLayout extends JsonLayout {

    public static final String APPLICATION = "CareRadius";
    public static final String ENVIRONMENT = "Dev";
    public static final String HEADERS = "http://webservice.careradius.landacorp.com";
    private static final Logger logger = LoggerFactory.getLogger(CustomJsonLayout.class);

    public static String methodName = "";

    //    //&& execution(* com.exlservice.demoLogger.Layout.CustomJsonLayout.addCustomDataToJsonMap(..))
////    execution(* com.exlservice.demoLogger.Controller.*.*(..))
//    @Pointcut(value = "execution(* com.exlservice.demoLogger.Layout.CustomJsonLayout.addCustomDataToJsonMap(..))")
//    public void myPointcut() {
//    }
//
//    @Around("myPointcut()")
//    public Object logMethodCall(ProceedingJoinPoint pjr) throws Throwable {
//        System.out.println("Line 37");
////        Signature signature = pjr.getSignature();
////        Logger logger = LoggerFactory.getLogger(signature.getDeclaringType());
////
////        // These are the information that can be extracted from both ILoggingEvent and ProceedingJoinPoint objects (Although Logger message isn't
////        // available in ProceedingJoinPoint object which forces us to override the method written down below).
////        String loggerName = pjr.getTarget().getClass().getName();
////        methodName = pjr.getSignature().getNaame(); // This can't be retrieved from ILoggingEvent (main point of concern).
////        String loggerLevel = "";
////        if (logger.isTraceEnabled()) {
////            loggerLevel = "TRACE";
////        } else if (logger.isDebugEnabled()) {
////            loggerLevel = "DEBUG";
////        } else if (logger.isInfoEnabled()) {
////            loggerLevel = "INFO";
////        } else if (logger.isWarnEnabled()) {
////            loggerLevel = "WARN";
////        } else if (logger.isErrorEnabled()) {
////            loggerLevel = "ERROR";
////        }
//        methodName = pjr.getSignature().getName();
////        MDC.put("methodName", "getMethodName");
////        map.put("methodName", methodName);
////        System.out.println(logger.getClass().getEnclosingMethod().getName());
//        Object result = pjr.proceed();
////        MDC.remove("methodName");
//        return result;
//    }

    @Override
    protected void addCustomDataToJsonMap(Map<String, Object> map, ILoggingEvent event) {
//        System.out.println("Line 40");
//        System.out.println(event.getMessage());
//        String s = new String(event.getMessage());
//        String str[] = s.split("#");
//        System.out.println("First one :" + str[0]);
//        System.out.println("Second one :" + str[1]);
//        System.out.println(event.getMessage().split("|"));
//        System.out.println("EVENT : " + event.getMDCPropertyMap());
//        System.out.println("Line 82");
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timestamp = format.format(new Date());
        Map<String, String> identifiers = new HashMap<String, String>();
        Map<String, String> feature = new HashMap<String, String>();

//        map.putAll(event.getMDCPropertyMap());
        map.put("Starttime", timestamp);
        identifiers.put("organizationName", APPLICATION);
        identifiers.put("logger", event.getLoggerName());
        feature.put("featureClient", "Horizon");
        feature.put("featureName", event.getLoggerName());
        feature.put("featureVersion", "");
        feature.put("featureInstanceId", "");
        map.put("level", event.getLevel().levelStr);
        map.put("Message", event.getMessage());
        map.put("time", timestamp);
        map.put("identifiers", identifiers);
        map.put("feature", feature);
        map.put("methodName", methodName);
//        map.put("methodName", event.getMessage().split("|")[0]); // This gives the internal methods but not the actual api method (main point of concern).
        map.put("componentType", event.getMessage().split(";")[1]);
        map.put("url", "");
        map.put("httpMethod", HEADERS);
        map.put("payload", "");
        map.put("environment", ENVIRONMENT);
        map.put("ExceptionMessage", "");
        map.put("EndTime", timestamp);
//        System.out.println(event.getMDCPropertyMap());
        super.addCustomDataToJsonMap(map, event);
    }
}
