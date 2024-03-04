package com.dmm.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dmm.task.data.repository.TaskRepository;

@Controller
public class MainController {
	
	@Autowired
	private TaskRepository repo;
	
	// ログイン確認用（後で消しておく）
	@GetMapping("/main")
	public String mainPage() {
		return "main";
	}

}
