package com.dmm.task.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/loginForm")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	String loginForm() {
		return "login";
	}
}
