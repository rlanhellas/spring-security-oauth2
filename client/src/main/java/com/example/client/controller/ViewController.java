package com.example.client.controller;

import com.example.client.service.WelcomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final WelcomeService service;

    public ViewController(WelcomeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String main(){
        return "welcome";
    }

    @GetMapping("/clientcredentials")
    public void clientCredentials(){
        service.callResourceServer();
    }
}
