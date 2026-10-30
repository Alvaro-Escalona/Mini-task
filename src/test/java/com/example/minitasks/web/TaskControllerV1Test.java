package com.example.minitasks.web;

import com.example.minitasks.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskControllerV1.class)
class TaskControllerV1Test {

    @Autowired
    MockMvc mvc;

    @Autowired
    TaskControllerV1 controller;

    @Test
    void getsExistingTask() throws Exception {
        Task task = controller.create(new Task("Comprar leche"));

        mvc.perform(get("/v1/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Comprar leche"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void missingTaskReturns404() throws Exception {
        mvc.perform(get("/v1/tasks/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""));
    }
}