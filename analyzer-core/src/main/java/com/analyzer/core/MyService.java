package com.analyzer.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Value("${default.text}")
    private String defaultText;

    public String hello() {
        return defaultText;
    }
}
