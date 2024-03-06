package com.dmm.task.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.input.CreateForm;
import com.dmm.task.data.repository.TaskRepository;
import com.dmm.task.data.service.AccountUserDetails;

@Controller
public class CreateController {

	@Autowired
	private TaskRepository repo;

	@GetMapping("/main/create/{date}")
	public String create (@PathVariable("date") String date,  Model model) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate toDay = LocalDate.parse(date, dtf);
		
		CreateForm createForm = new CreateForm();
		createForm.setDate(toDay);
		model.addAttribute("createForm", createForm);

		return "create";
	}

	@PostMapping("/main/create")
	public String task(CreateForm createForm, @AuthenticationPrincipal AccountUserDetails user, Model model) {

		Tasks task = new Tasks();
		task.setTitle(createForm.getTitle());
		task.setName(user.getName());
		task.setText(createForm.getText());
		task.setDate(createForm.getDate().atStartOfDay());
		task.setDone(false);

		repo.save(task);

		return "redirect:/main";

	}

}
