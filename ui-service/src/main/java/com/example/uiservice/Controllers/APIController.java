package com.example.uiservice.Controllers;

import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class APIController {

    @Autowired
    public TeacherRepository repository;

    @GetMapping("/test1")
    public ArrayList<Teacher> showWorkForm() {
        return repository.findAll();
    }
}
