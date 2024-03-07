package com.dmm.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.input.EditForm;
import com.dmm.task.data.repository.TaskRepository;

@Controller
public class EditController {
	
	@Autowired
	public TaskRepository repo;
	
	@GetMapping("/main/edit/{id}")
	public String editPage(Model model, @PathVariable("id") Integer id) {
		
		Tasks task = repo.getById(id);
		model.addAttribute("task", task);
		
		return "edit";
	}
	
	@PostMapping("/main/edit/{id}")
	public String edit( EditForm editForm, Model model, @PathVariable("id") Integer id) {
		
		Tasks task = repo.getById(id);
		
		task.setTitle(editForm.getTitle());
		task.setDate(editForm.getDate());
		task.setText(editForm.getText());
		task.setDone(editForm.getDone());
		
		repo.save(task);
		
		return "redirect:/main";
	}
	
	@PostMapping("/main/edit/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		
		repo.deleteById(id);
		
		return "redirect:/main";
	}
	

}
