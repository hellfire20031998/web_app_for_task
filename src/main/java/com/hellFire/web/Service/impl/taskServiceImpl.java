package com.hellFire.web.Service.impl;

import com.hellFire.web.Entity.Task;
import com.hellFire.web.Repository.TaskRepository;
import com.hellFire.web.Service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Stream;

@Service
public class taskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    public taskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
      return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(int id) {
     Task task = taskRepository.findById(id).orElseThrow(()->new RuntimeException());
     return task;
    }

    @Override
    public Task updateTask(int id,Task newTask) {
        Task task = taskRepository.findById(id).orElseThrow(()->new RuntimeException());
        task.setTaskDescription(newTask.getTaskDescription());
        task.setTaskName(newTask.getTaskName());
        task.setTaskStatus(newTask.getTaskStatus());

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTask() {
        List<Task> tasks = taskRepository.findAll();
        return  tasks;
    }

    @Override
    public void deleteTask(int id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            taskRepository.delete(task);
        }
    }

    @Override
    public void creatMultiTask(@RequestBody List<Task> tasks) {
        taskRepository.saveAll(tasks);
    }

    public boolean findID(int id){
        return taskRepository.findById(id).isPresent();
    }

    public List<Task> findByTaskStatusTrue() {
        return  taskRepository.findByTaskStatusTrue();
    }

    @Override
    public List<Task> findByTaskStatusFalse() {
        return taskRepository.findByTaskStatusFalse();
    }

    @Override
    public void deleteCompletedTasks() {
        List<Task> tasks =findByTaskStatusTrue();
        tasks.forEach(task -> deleteTask(task.getTaskId()));
    }

    public void deleteIncompletedTasks() {
        List<Task> tasks =findByTaskStatusFalse();
        tasks.forEach(task -> deleteTask(task.getTaskId()));
    }

    @Override
    public boolean completeTask(int id) {
        if(findID(id)){
            Task task = getTaskById(id);
            task.setTaskStatus(!task.getTaskStatus());
            taskRepository.save(task);
            return true;
        }
        return false;

    }

}
