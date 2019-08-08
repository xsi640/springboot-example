package com.suyang.dao;

import com.suyang.domain.Student;

import java.util.List;

public interface StudentDao {
    Student findOne(String id);

    List<Student> findAll();

    Student save(Student s);

    void delete(String id);

    void deleteAll();
}
