package com.dmm.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.input.EditForm;
import com.dmm.task.data.repository.TaskRepository;
import com.dmm.task.data.service.AccountUserDetails;

@Controller
public class EditController {
	
	@Autowired
	public TaskRepository repo;
	
	@GetMapping("/main/edit/{id}")
	public String editPage(@PathVariable("id") Integer id,   Model model) {
		
		Tasks task = repo.getById(id);
		EditForm form = new EditForm();
		
		form.setId(task.getId());
		form.setTitle(task.getTitle());
		form.setDate(task.getDate().toLocalDate());
		form.setText(task.getText());
		form.setDone(task.getDone());
		
		model.addAttribute("task", task);
		
		
		return "edit";
	}
	
	@PostMapping("/main/edit/{id}")
	public String edit(@PathVariable("id") Integer id, EditForm editForm, @AuthenticationPrincipal AccountUserDetails user, Model model) {
		
		Tasks task = repo.getById(id);
		
		task.setTitle(editForm.getTitle());
		task.setDate(editForm.getDate().atStartOfDay());
		task.setText(editForm.getText());
		task.setDone(editForm.getDone());
		
		repo.save(task);
		
		return "redirect:/main";
	}

}
