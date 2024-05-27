package com.example.taskmanager.dto;

import java.util.List;

import com.example.taskmanager.entity.NoteEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateNoteResponseDto {
	

	private Integer id;
	private List<NoteEntity> noteEntity;
}
