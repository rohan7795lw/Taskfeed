package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.dto.CreateNoteDto;
import com.example.taskmanager.dto.CreateNoteResponseDto;
import com.example.taskmanager.entity.NoteEntity;
import com.example.taskmanager.service.NoteService;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NoteController {

	public NoteService noteService;

	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}

	@GetMapping("")
	public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer id) {
		var notes = noteService.getNotes(id);
		return ResponseEntity.ok(notes);

	}

	@GetMapping("/{noteId}")
	public ResponseEntity<NoteEntity> getNoteId(@PathVariable("taskId") Integer id,
			@PathVariable("noteId") Integer noteId) {

		var notes = noteService.getNote(id, noteId);
		return ResponseEntity.ok(notes);

	}

	@DeleteMapping("/{noteId}")
	public ResponseEntity<List<NoteEntity>> deleteNoteId(@PathVariable("taskId") Integer id,
			@PathVariable("noteId") Integer noteId) {

		var notes = noteService.deleteNote(id, noteId);
		return ResponseEntity.ok(notes);

	}

	@PostMapping("")
	public ResponseEntity<CreateNoteResponseDto> createNote(@PathVariable("taskId") Integer id,
			@RequestBody CreateNoteDto body) {
		var note = noteService.addNotes(id, body.getTitle(), body.getBody());

		return ResponseEntity.ok(new CreateNoteResponseDto(id, note));
	}

}
