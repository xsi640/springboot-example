package com.suyang.controller;

import com.suyang.domain.Student;
import com.suyang.repository.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "学生接口")
@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

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

    @GetMapping("generate")
    public String generate() {
        SchemaExport export = new SchemaExport();
        export.setDelimiter(";");
        export.setHaltOnError(true);
        export.setFormat(true);
        EnumSet<TargetType> enumSet = EnumSet.of(TargetType.SCRIPT);

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .applySetting(Environment.DIALECT, entityManagerFactory.getProperties().get("hibernate.dialect").toString())
                .build();
        MetadataSources metadata = new MetadataSources(standardRegistry);
        metadata.addAnnotatedClass(Student.class);
        export.setFormat(true);
        export.setHaltOnError(true);
        export.setDelimiter(";");
        export.setOutputFile("d:\\db-schema.sql");
        export.execute(enumSet, SchemaExport.Action.CREATE, metadata.buildMetadata());
        return "";
    }
}
