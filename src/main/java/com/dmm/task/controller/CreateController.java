package com.dmm.task.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	public String create (Model model,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		
		
		return "create";
	}

	@PostMapping("/main/create")
	public String task(CreateForm createForm,
			@AuthenticationPrincipal AccountUserDetails user, Model model) {
		
		createForm.setDates(LocalDate.parse(createForm.getDate()));
		
		Tasks task = new Tasks();
		task.setTitle(createForm.getTitle());
		task.setName(user.getUsername());
		task.setText(createForm.getText());
		task.setDate(createForm.getDates().atStartOfDay());
		task.setDone(false);

		repo.save(task);

		return "redirect:/main";

	}

}
