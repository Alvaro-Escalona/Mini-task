package com.example.minitasks.web;

import com.example.minitasks.domain.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1/tasks")
public class TaskControllerV1 {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(0);

    public Task create(Task task) {
        task.setId(counter.incrementAndGet());
        tasks.add(task);
        return task;
    }

    public List<Task> list() {
        return tasks;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable("id") Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}