package com.hellFire.web.Controller;

import com.hellFire.web.Entity.Task;
import com.hellFire.web.Service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class taskController {
    private TaskService taskService;

    public taskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task newTask){
        return new ResponseEntity<>(taskService.createTask(newTask), HttpStatus.CREATED);
    }

    @PostMapping("/addAll")
    public ResponseEntity<String> createMultipleTasks(@RequestBody List<Task> tasks){
        taskService.creatMultiTask(tasks);
        return ResponseEntity.ok("All tasks are added");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        if (taskService.findID(id)) {
            taskService.deleteTask(id);
            return ResponseEntity.status(200).body("Task with ID " + id + " deleted successfully.");
        }
        return ResponseEntity.status(404).body("Task with ID " + id + " not found.");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable int id){
        if (taskService.findID(id)) {
            Task task=taskService.getTaskById(id);
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> allTasks= taskService.getAllTask();
        return ResponseEntity.ok(allTasks);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id,@RequestBody Task newTask){
        if (taskService.findID(id)) {
            Task updateTask = taskService.updateTask(id,newTask);
            return ResponseEntity.ok(updateTask);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/completed")
    public  ResponseEntity<List<Task>> getAllCompletedTasks(){
        return new ResponseEntity<>(taskService.findByTaskStatusTrue(),HttpStatus.OK);
    }

    @GetMapping("/incompleted")
    public  ResponseEntity<List<Task>> getAllIncompletedTasks(){
        return new ResponseEntity<>(taskService.findByTaskStatusFalse(),HttpStatus.OK);
    }

    @DeleteMapping("deletecompleted")
    public ResponseEntity<String> deleteCompletedTasks(){
       taskService.deleteCompletedTasks();
       return ResponseEntity.ok("Deleted all compeleted tasks");
    }

    @DeleteMapping("deleteincompleted")
    public ResponseEntity<String> deleteIncompletedTasks(){
        taskService.deleteIncompletedTasks();
        return  ResponseEntity.ok("Deleted all Incompleted tasks");
    }

    @PutMapping("/{id}/completed")
    public ResponseEntity<String> TaskCompleted(@PathVariable int id){
        if(taskService.completeTask(id)){
            return ResponseEntity.ok("Task Completed");
        }
        return ResponseEntity.ok("Task not found");
    }




}