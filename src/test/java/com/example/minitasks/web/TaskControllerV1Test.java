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

    @Test
    void getsExistingTask() throws Exception {
        TaskControllerV1 controller = new TaskControllerV1();
        controller.create(new Task("Comprar leche"));
        Long id = controller.list().get(0).getId();

        mvc.perform(get("/v1/tasks/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Comprar leche"))
                .andExpect(jsonPath("$.completed").value(false));
    }
}