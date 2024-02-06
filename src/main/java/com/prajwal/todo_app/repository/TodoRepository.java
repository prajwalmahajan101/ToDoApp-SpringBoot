package com.prajwal.todo_app.repository;

import com.prajwal.todo_app.model.Todo;
import com.prajwal.todo_app.util.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByStatus(TaskStatus status);
}
