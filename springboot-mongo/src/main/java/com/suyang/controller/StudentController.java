package com.suyang.controller;

import com.suyang.domain.Student;
import com.suyang.repository.StudentRepository;
import org.intellij.lang.annotations.RegExp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Student findOne(@PathVariable("id") String id) {
        return studentRepository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Student save(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(String id) {
        studentRepository.deleteById(id);
    }
}
