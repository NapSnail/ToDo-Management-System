package com.dmm.task.data.input;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RegistForm {
	
	private String title;
	private String text;
	private LocalDateTime date;
	
}
