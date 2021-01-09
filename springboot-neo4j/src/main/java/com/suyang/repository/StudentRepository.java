package com.suyang.repository;

import com.suyang.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {
    Student findByName(String name);
}
