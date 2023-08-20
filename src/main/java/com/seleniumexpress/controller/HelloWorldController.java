package com.seleniumexpress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	@GetMapping("/helloWorld") // don't secure this
	public String getHelloWorld() {
		return "hello-world";
	}
	@ResponseBody
	@GetMapping("/hello") //secure it
	public String getHello() {
		return "hello from Selenium Express";
	}
	@ResponseBody
	@GetMapping("/bye") //secure it
	public String getBye() {
		return "bye bye guys";
	}
}
