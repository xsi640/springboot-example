package com.suyang.dao;

import com.suyang.domain.Student;

import java.util.List;

public interface StudentDao {
    Student get(int id);

    Student insert(Student s);

    Student update(Student s);

    int delete(int id);

    List<Student> selectAll();

    int clear();
}
