package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message = "Hello World";
    @Value("${welcome.login}")
    private String login;
    @Value("${welcome.name}")
    private String name;
    @Value("${welcome.password}")
    private String password;

    @RequestMapping("/welcome")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }

    @RequestMapping("/auth")
    public String auth(@RequestParam(value = "login", required = false) String login, @RequestParam(value = "password", required = false) String password, Map<String, Object> model) {
        if (password.equals(this.password)) {
            model.put("login", login);
            model.put("password", password);
            return "profile";
        } else {
            return "authDecline";
        }
    }

    @RequestMapping("/")
    public String login(Map<String, Object> model) {
        return "login";
    }

    @RequestMapping("/profile")
    public String profile(Map<String, Object> model) {
        model.put("login", this.login);
        model.put("name", this.name);
        return "profile";
    }

}