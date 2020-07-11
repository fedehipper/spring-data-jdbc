package com.demo.jdbc.controller.rest;

import com.demo.jdbc.domain.Registration;
import com.demo.jdbc.domain.Student;
import com.demo.jdbc.repository.StudentRepository;
import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRestController {

    private final StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    @GetMapping("/api/student")
    public List<Student> buscarPorNombre(@RequestParam String name) {
        return studentRepository.findByName(name);
    }

 
    public Student guardar() {
        Registration oneRegistration = new Registration();
        oneRegistration.setTipo("Un tipo");

        Registration otherRegistration = new Registration();
        otherRegistration.setTipo("Otro tipo");

        Student student = new Student();
        student.setName("Federico");
        student.setRegistrations(Set.of(oneRegistration, otherRegistration));

        return studentRepository.save(student);
    }

}
