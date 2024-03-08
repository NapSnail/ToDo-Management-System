package com.dmm.task.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dmm.task.data.Timer;
import com.dmm.task.data.entity.Tasks;
import com.dmm.task.data.repository.TaskRepository;
import com.dmm.task.data.service.AccountUserDetails;

@Controller
public class MainController {

	@Autowired
	private TaskRepository trepo;

	@GetMapping("/main")
	public String mainPage(
			@RequestParam(name = "date", defaultValue = "#{T(java.time.LocalDate).now()}", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
			@AuthenticationPrincipal AccountUserDetails user, Model model) {

		LocalDate toMonth = date;

		LocalDate prev = toMonth.minusMonths(1);
		model.addAttribute("prev", prev);
		LocalDate next = toMonth.plusMonths(1);
		model.addAttribute("next", next);

		if (!(toMonth.equals(date))) {
			if (date.equals(prev)) {
				toMonth = prev;
			} else {
				toMonth = next;
			}

		};

		DateTimeFormatter dtfYM = DateTimeFormatter.ofPattern("yyyy年MM月");
		String month = dtfYM.format(YearMonth.from(toMonth));
		model.addAttribute("month", month);

		Timer timer = new Timer(toMonth);
		List<List<LocalDate>> matrix = new ArrayList<>();
		
		timer.listSet(matrix);
		
		model.addAttribute("matrix", matrix);

		MultiValueMap<LocalDate, Tasks> tasks = new LinkedMultiValueMap<LocalDate, Tasks>();
		List<Tasks> task = trepo.findByDateBetween(matrix.get(0).get(0).atStartOfDay(), matrix.get(matrix.size() - 1).get(6).atStartOfDay(),
				user.getUsername());

		if (user.getUsername().equals("admin")) {

			task = trepo.findByDateBetweenAd(matrix.get(0).get(0).atStartOfDay(), matrix.get(matrix.size() - 1).get(6).atStartOfDay());
		}

		timer.setUpTasks(tasks, task);

		model.addAttribute("tasks", tasks);

		return "main";
	}

}
