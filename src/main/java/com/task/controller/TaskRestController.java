package com.task.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.entity.Task;
import com.task.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

	private final TaskService taskService;

	@Autowired
	public TaskRestController(TaskService taskService) {
		this.taskService = taskService;
	}

	// Rest end point to retrieve all the tasks

	@GetMapping
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}
	
	

	// Rest end point to create a new task automatically the status will be set to
	// PENDING
	@PostMapping
	public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
		return ResponseEntity.ok(taskService.createTask(task));
	}

	
	
	// rest end point to get specific task by id
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
		return taskService.getTaskById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	
	
	/*
	 * rest end point to update the task only title, description and dueDate are
	 * able to updated to update status use rest end point find in below end points
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task updatedTask) {
		try {
			return ResponseEntity.ok(taskService.updateTask(id, updatedTask));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	// Deletes a task by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}

	
	/* this end point used to update status to COMPLETED instead of writing in json in updateTask  */
	@PatchMapping("/{id}/complete")
	public ResponseEntity<Task> markTaskAsComplete(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(taskService.markTaskAsComplete(id));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
//	this end point used to update status to IN_PROGRESS instead of writing in json in updateTask
	@PatchMapping("/{id}/progress")
	public ResponseEntity<Task> markTaskAsProgress(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(taskService.markTaskAsProgress(id));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
