package com.example.demo.controller;

import com.example.demo.annotation.SignAdvice;
import com.example.demo.entity.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class SignResponseController {

    @SignAdvice(signKey = "signKey:test")
    @GetMapping("/test")
    public Response<String> test() {
        Response<String> response = new Response<>();
        response.setCode(200);
        response.setMessage("success");
        return response;
    }
}
