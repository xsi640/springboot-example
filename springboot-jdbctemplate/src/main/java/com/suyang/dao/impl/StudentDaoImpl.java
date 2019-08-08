package com.suyang.dao.impl;

import com.suyang.dao.StudentDao;
import com.suyang.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@SuppressWarnings("Duplicates")
@Repository
public class StudentDaoImpl implements StudentDao {
    private static final String GET = "SELECT * FROM student WHERE id=?";
    private static final String INSERT = "INSERT INTO student(name, age, birthday) VALUES(?,?,?)";
    private static final String UPDATE = "UPDATE student SET name=?, age=?, birthday=? WHERE id=?";
    private static final String DELETE = "DELETE FROM student WHERE id=?";
    private static final String SELECT_ALL = "SELECT * FROM student";
    private static final String DELETE_ALL = "DELETE FROM student";

    private JdbcTemplate jdbcTemplate;

    @Override
    public Student get(int id) {
        return jdbcTemplate.queryForObject(GET, new Object[]{id}, (rs, rowNum) -> {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setAge(rs.getInt("age"));
            s.setBirthday(rs.getDate("birthday"));
            return s;
        });
    }

    @Override
    public Student insert(Student s) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, s.getName());
            statement.setInt(2, s.getAge());
            statement.setDate(3, new Date(s.getBirthday().getTime()));
            return statement;
        }, keyHolder) == 0) {
            return null;
        }
        s.setId(keyHolder.getKey().intValue());
        return null;
    }

    @Override
    public Student update(Student s) {
        if (jdbcTemplate.update(UPDATE, s.getName(), s.getAge(), s.getBirthday(), s.getId()) == 0)
            return null;
        else
            return s;
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<Student> selectAll() {
        return jdbcTemplate.query(SELECT_ALL, (rs, rowNum) -> {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setAge(rs.getInt("age"));
            s.setBirthday(rs.getDate("birthday"));
            return s;
        });
    }

    @Override
    public int clear() {
        return jdbcTemplate.update(DELETE_ALL);
    }
}
