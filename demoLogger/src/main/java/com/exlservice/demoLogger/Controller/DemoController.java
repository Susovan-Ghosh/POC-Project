package com.exlservice.demoLogger.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Value("${MY_VARIABLE}")
    private String myVariable;

    @GetMapping("/methodName")
    public String getMethodName() {
        long sT = System.currentTimeMillis();
        System.out.println(myVariable);
        Logger logger = LoggerFactory.getLogger(DemoController.class);
        logger.info("abc;xyz");
        long eT = System.currentTimeMillis();
        System.out.println("Time : " + (eT - sT));
        return getMethodNameHelper();
    }

    public String getMethodNameHelper() {
        return "Test";
    }

}
