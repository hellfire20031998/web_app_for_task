package com.hellFire.web.Repository;

import com.hellFire.web.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByTaskStatusTrue();
    List<Task> findByTaskStatusFalse();

}
