package com.dmm.task.data.input;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EditForm {
	
	
	private Integer id;
	
	@Size(min = 1)
	private String title;
	@Size(min = 1)
	private String text;
	private LocalDateTime date;
	private Boolean done;

}
