package com.example.taskmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.NoteEntity;
import com.example.taskmanager.entity.TaskEntity;

@Service
public class NoteService {

	class TaskNoteHolder {
		protected int noteId = 1;
		protected ArrayList<NoteEntity> notes = new ArrayList<>();
	}

	private TaskService taskService;
	private HashMap<Integer, TaskNoteHolder> holderMap = new HashMap<>();

	public NoteService(TaskService taskService) {
		this.taskService = taskService;
	}

	public NoteEntity getNote(int taskId, int noteId) {

		TaskEntity task = taskService.getTask(taskId);
		if (task == null)
			return null;
		if (holderMap.get(taskId) == null)
			return null;

		return holderMap.get(taskId).notes.get(noteId - 1);
	}

	public List<NoteEntity> getNotes(int id) {
		TaskEntity taskEntity = taskService.getTask(id);
		if (taskEntity == null)
			return null;

		if (holderMap.get(taskEntity.getId()) == null)
			holderMap.put(id, new TaskNoteHolder());

		return holderMap.get(id).notes;
	}

	public List<NoteEntity> deleteNote(int taskId, int noteId) {

		TaskEntity task = taskService.getTask(taskId);
		if (task == null)
			return null;
		if (holderMap.get(taskId) == null)
			return null;
		
		 holderMap.get(taskId).notes.remove(noteId-1);
		 
		 return holderMap.get(taskId).notes;

	}

	public List<NoteEntity> addNotes(int id, String title, String body) {
		TaskEntity taskEntity = taskService.getTask(id);
		if (taskEntity == null)
			return null;

		if (holderMap.get(taskEntity.getId()) == null)
			holderMap.put(id, new TaskNoteHolder());

		TaskNoteHolder taskNoteHolder = holderMap.get(id);
		NoteEntity noteEntity = new NoteEntity();
		noteEntity.setId(taskNoteHolder.noteId);
		noteEntity.setTitle(title);
		noteEntity.setBody(body);

		taskNoteHolder.notes.add(noteEntity);
		taskNoteHolder.noteId++;
		return taskNoteHolder.notes;
	}
}
