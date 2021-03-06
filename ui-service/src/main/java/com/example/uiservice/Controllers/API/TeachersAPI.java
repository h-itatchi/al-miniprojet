package com.example.uiservice.Controllers.API;

import com.example.uiservice.DATA.Entities.DTO.ResponseMessage;
import com.example.uiservice.DATA.Entities.Teacher;
import com.example.uiservice.DATA.Repositories.Implimentations.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class TeachersAPI {

    @Autowired
    public TeacherRepository teacherRepo;

    @PostMapping(value = "/api/teacher/exist")
    public ResponseMessage checkExist(@RequestBody Teacher teacher) {
        ResponseMessage r = new ResponseMessage();
        r.setResult("exists");
        if (teacherRepo.existByFirstName(teacher.getFirstName())) {
            r.setReason("First name already exists");
            return r;
        }
        if (teacherRepo.existByEmail(teacher.getEmail())) {
            r.setReason("Email already exists");
            return r;
        }
        r.setResult("true");
        return r;
    }

    @PostMapping(value = "/api/teacher/save")
    public ResponseEntity<Teacher> save(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherRepo.save(teacher));
    }

    @GetMapping(value = "/api/teacher/")
    public ResponseEntity<ArrayList<Teacher>> getAllTeachersIds() {
        return ResponseEntity.ok(teacherRepo.findAll());
    }

    @DeleteMapping("/api/teacher/delete")
    public ResponseEntity<ResponseMessage> delete(@RequestBody Teacher teacher) {
        teacherRepo.deleteById(teacher.getId());
        return ResponseEntity.ok(new ResponseMessage("true",""));
    }

}
