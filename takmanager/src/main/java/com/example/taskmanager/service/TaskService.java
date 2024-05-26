package com.example.taskmanager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.example.taskmanager.dto.ErrorResponseDto;
import com.example.taskmanager.entity.TaskEntity;

@Repository
public class TaskService {

	private ArrayList<TaskEntity> tasks = new ArrayList<>();

	private int taskId = 1;

	private final SimpleDateFormat deadlineDateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	public TaskEntity addTask(String title, String desc, String deadline) throws ParseException {

		TaskEntity task = new TaskEntity();
		task.setId(taskId);
		task.setTitle(title);
		task.setDesc(desc);

		task.setDeadline(deadlineDateFormatter.parse(deadline));
		task.setCompleted(false);
		tasks.add(task);
		taskId++;
		return task;

	}

	public ArrayList<TaskEntity> getTasks() {
		return tasks;
	}

	public TaskEntity getTask(int id) {
		for (TaskEntity task : tasks) {
			if (task.getId() == id)
				return task;
		}
		return null;
	}

	public TaskEntity updateTask(int id, String description, String deadline, Boolean competed)
			throws ParseException {

		TaskEntity task = getTask(id);

		if (task != null) {
			if (description != null)
				task.setDesc(description);
			if (deadline != null) {
				task.setDeadline(deadlineDateFormatter.parse(deadline));
			}
			if (competed != null) {
				task.setCompleted(competed);
			}

			return task;
		}
		return null;
		

	}
}
