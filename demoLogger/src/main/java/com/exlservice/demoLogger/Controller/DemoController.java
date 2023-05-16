package com.exlservice.demoLogger.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/methodName")
    public String getMethodName() {
        return getMethodNameHelper();
    }

    public String getMethodNameHelper() {
        return "Test";
    }

}
