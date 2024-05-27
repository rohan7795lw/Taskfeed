package com.example.taskmanager.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.taskmanager.dto.CreateTaskDto;
import com.example.taskmanager.dto.ErrorResponseDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.dto.UpdateTaskDto;
import com.example.taskmanager.entity.TaskEntity;
import com.example.taskmanager.service.NoteService;
import com.example.taskmanager.service.TaskService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final NoteService noteService;

    public TaskController(TaskService taskService, NoteService noteService) {
        this.taskService = taskService;
        this.noteService = noteService;
    }

    @GetMapping("") 
    public ResponseEntity<List<TaskEntity>> getTasks() {
        var tasks = taskService.getTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable("id") Integer id) {
        var task = taskService.getTask(id);
        var notes = noteService.getNotes(id);
        
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(new TaskResponseDto(task,notes));
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDto body) throws ParseException {
        var task = taskService.addTask(body.getTitle(), body.getDesc(), body.getDeadline());
        return ResponseEntity.ok(task);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDto body) throws Exception{
    	var task = taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
    	if(task!=null)
    		return ResponseEntity.ok(task);
    	throw new Exception("Not able to find");
    	
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> errorResponse(Exception e){
    	
    	if(e instanceof ParseException)
    		return ResponseEntity.badRequest().body(new ErrorResponseDto("Inalid Date Format"));
    	
    
    	e.printStackTrace();
		return ResponseEntity.internalServerError().body(new ErrorResponseDto("Server Error"));
    	
    }
}