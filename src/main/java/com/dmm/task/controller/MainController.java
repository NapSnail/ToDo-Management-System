package com.dmm.task.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.dmm.task.data.Timer;
import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.repository.TaskRepository;
import com.dmm.task.data.service.AccountUserDetails;
@Controller
public class MainController {

	@Autowired
	private TaskRepository trepo;

	@GetMapping("/main")
	public String mainPage(@AuthenticationPrincipal AccountUserDetails user, Model model) {
		YearMonth toMonth = YearMonth.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月");
		String month = dtf.format(toMonth);
		model.addAttribute("month", month);
		
		
		Timer timer = new Timer();
		List<List<LocalDate>> matrix = new ArrayList<>();
		List<LocalDate> week1 = new ArrayList<>();
		List<LocalDate> week2 = new ArrayList<>();
		List<LocalDate> week3 = new ArrayList<>();
		List<LocalDate> week4 = new ArrayList<>();
		List<LocalDate> week5 = new ArrayList<>();
		List<LocalDate> week6 = new ArrayList<>();

		week1 = timer.week1();
		week2 = timer.week2();
		week3 = timer.week3();
		week4 = timer.week4();
		week5 = timer.week5();
		week6 = timer.week6();

		matrix.addAll(Arrays.asList(week1, week2, week3, week4, week5, week6));
		model.addAttribute("matrix", matrix);
		
		
		MultiValueMap<LocalDate, Tasks> tasks = new LinkedMultiValueMap<LocalDate, Tasks>();
		List<Tasks> task = trepo.findByDateBetween(week1.get(0).atStartOfDay(),week6.get(6).atStartOfDay(), user.getUsername());
		
		if(user.getUsername().equals("admin")) {
			
			task = trepo.findByDateBetweenAd(week1.get(0).atStartOfDay(),week6.get(6).atStartOfDay());
		}
		
		timer.setUpTasks(tasks, task);
		
		model.addAttribute("tasks", tasks);
		

		return "main";
	}
	
	

}
