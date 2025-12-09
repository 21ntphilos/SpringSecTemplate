package com._nt.SpingSecTemplate;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private final List<Student> students = new ArrayList<Student>(List.of(
            new Student(1, 340, "Tulsa"),
            new Student(2, 440, "King"),
            new Student(3, 150, "Hairly"),
            new Student(4, 525, "Weni")
            ));


    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return this.students;
    }

    @GetMapping("csrf")
    public CsrfToken getToken(HttpServletRequest req){
        return (CsrfToken)req.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Student getAllStudents(@RequestBody Student data){

        students.add(data);
        return  data;
    }
}
