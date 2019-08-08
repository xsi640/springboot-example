package com.suyang.controller;

import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentDao studentDao;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Student findOne(@PathVariable("id") String id) {
        return studentDao.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        studentDao.delete(id);
    }
}
