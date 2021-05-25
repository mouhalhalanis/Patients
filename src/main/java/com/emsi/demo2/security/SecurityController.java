package com.emsi.demo2.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

public class SecurityController {
	@GetMapping("/error")
	public String error(){
		return "error";
	}
	
}
