package com.example.taskmanager.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskDto {

	private String description;
	private Boolean completed;
	private String deadline;
}
