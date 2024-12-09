package com.hellFire.web.Service;

import com.hellFire.web.Entity.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task getTaskById(int id);
    Task updateTask(int id,Task task);
    List<Task> getAllTask();
    void deleteTask(int id);
    void creatMultiTask(List<Task> tasks);
    boolean findID(int id);
    List<Task> findByTaskStatusTrue();
    List<Task> findByTaskStatusFalse();
    void deleteCompletedTasks();
    void deleteIncompletedTasks();
    boolean completeTask(int id);
}
