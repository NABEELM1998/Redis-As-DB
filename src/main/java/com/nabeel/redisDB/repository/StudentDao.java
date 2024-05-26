package com.nabeel.redisDB.repository;

import com.nabeel.redisDB.model.Student;

import java.util.Map;

public interface StudentDao {

    Student save(Student student);
    Student getStudentById(Integer id);
    Map<Object, Object> getAllStudents();
    void deleteStudentById(Integer id);
    Student updateStudentById(Integer id, Student student);
}
