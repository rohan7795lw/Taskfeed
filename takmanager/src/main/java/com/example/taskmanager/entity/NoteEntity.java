package com.example.taskmanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoteEntity {

	private int id;
	private String title;
	private String body;
}
