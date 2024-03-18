package com.capstone.feelcheck.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Controller를 넣어야 Spring이 메모리에 넣음
public class FeelCheckControllerTest {

    // http://localhost:8080/test/hello
    @GetMapping("/test/hello")
    public String hello() {
        return "<h1>Hello Spring Boot</h1>";
    }
}
