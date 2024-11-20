package com.task.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.dao.TaskRepository;
import com.task.entity.Task;

@Service
public class TaskService {
	private final TaskRepository taskRepository;

	@Autowired
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	@Transactional
	public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
	
	@Transactional
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

	@Transactional
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

	@Transactional
    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setDueDate(updatedTask.getDueDate());
//                    task.setStatus(updatedTask.getStatus());
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

	
	@Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

	
	@Transactional
    public Task markTaskAsComplete(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setStatus(Task.Status.COMPLETED);
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
	
	@Transactional
    public Task markTaskAsProgress(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setStatus(Task.Status.IN_PROGRESS);
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
}
