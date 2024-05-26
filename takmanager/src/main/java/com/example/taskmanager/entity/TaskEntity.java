package com.example.taskmanager.entity;



import java.util.Date;

import lombok.Data;

@Data
public class TaskEntity {

	private Integer id;
	private String title;
	private String desc;
	private Date deadline;
	private boolean completed;
	
}
