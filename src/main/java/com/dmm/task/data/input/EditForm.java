package com.dmm.task.data.input;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EditForm {
	
	private String title;
	private String text;
	private LocalDateTime date;
	private Boolean done;

}
