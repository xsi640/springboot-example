package com.suyang.controller;

import com.suyang.domain.Student;
import com.suyang.repository.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "学生接口")
@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Operation(summary = "查询一个学生")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Student findOne(@PathVariable("id") int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Operation(summary = "查询所有学生")
    @RequestMapping(method = RequestMethod.GET)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Operation(summary = "创建或修改学生")
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Student create(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @Operation(summary = "删除学生")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        studentRepository.deleteById(id);
    }
}
