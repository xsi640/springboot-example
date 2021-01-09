package com.suyang.controller;

import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentDao studentDao;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Student findOne(@PathVariable("id") int id) {
        return studentDao.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> findAll() {
        return studentDao.selectAll();
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Student create(@RequestBody Student student) {
        return studentDao.insert(student);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        studentDao.delete(id);
    }
}
