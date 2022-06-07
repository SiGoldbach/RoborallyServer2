package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.http.HttpHeaders;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping
    public String hello() {
        return "Hello World";
    }
	@GetMapping("2")
	public String hello2(){
		return "hello2";
	}

    @GetMapping("Load3")
    public String load2() {
        return "hej";
    }




}
