package com.dmm.task.data.input;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreateForm {
	
	private String title;
	
	private String date;
	
	private String text;
	
	private LocalDate dates;
}
