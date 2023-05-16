package com.exlservice.demoLogger.Layout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import ch.qos.logback.contrib.json.classic.JsonLayout;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomJsonLayout extends JsonLayout {

    public static final String APPLICATION = "CareRadius";
    public static final String ENVIRONMENT = "Dev";
    public static final String HEADERS = "http://webservice.careradius.landacorp.com";
    private static final Logger logger = LoggerFactory.getLogger(CustomJsonLayout.class);
    public String methodName = "";

    @Pointcut(value = "execution(* com.exlservice.demoLogger.Controller.*.*(..) )")
    public void myPointcut() {
    }

    @Around("myPointcut()")
    public Object logMethodCall(ProceedingJoinPoint pjr) throws Throwable {
        methodName = pjr.getSignature().getName();
        System.out.println("Line 36 : " + methodName);
        Object result = pjr.proceed();
        return result;
    }

    @Override
    protected void addCustomDataToJsonMap(Map<String, Object> map, ILoggingEvent event) {
        System.out.println("Line 44 : " + methodName);
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timestamp = format.format(new Date());
        Map<String, String> identifiers = new HashMap<String, String>();
        Map<String, String> feature = new HashMap<String, String>();
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
        map.put("componentType", "");
        map.put("url", "");
        map.put("httpMethod", HEADERS);
        map.put("payload", "");
        map.put("environment", ENVIRONMENT);
        map.put("ExceptionMessage", "");
        map.put("EndTime", timestamp);
        map.putAll(event.getMDCPropertyMap());
        super.addCustomDataToJsonMap(map, event);
    }

}
