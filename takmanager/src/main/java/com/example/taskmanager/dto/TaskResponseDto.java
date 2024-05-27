package com.example.taskmanager.dto;

import java.util.List;

import com.example.taskmanager.entity.NoteEntity;
import com.example.taskmanager.entity.TaskEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskResponseDto {

	private TaskEntity task;
	private List<NoteEntity> notes;

}
