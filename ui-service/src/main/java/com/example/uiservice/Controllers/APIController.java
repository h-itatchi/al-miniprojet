package com.example.uiservice.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestController
public class APIController {

    @GetMapping("/test1")
    public LocalDate showWorkForm() {
        return LocalDate.now();
    }
}
