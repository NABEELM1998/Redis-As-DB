package com.nabeel.redisDB.repository;

import com.nabeel.redisDB.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class StudentDaoImpl implements StudentDao{

    @Autowired
    public RedisTemplate<String,Student> redisTemplate;

    private static final String KEY = "Student";
    @Override
    public Student save(Student student) {
        boolean isSaved = redisTemplate.opsForHash().putIfAbsent(KEY,student.getId(),student);
        return isSaved ? student : null;
    }

    @Override
    public Student getStudentById(Integer id) {
        return (Student) redisTemplate.opsForHash().get(KEY,id);
    }

    @Override
    public Map<Object, Object> getAllStudents() {
        return redisTemplate.opsForHash().entries(KEY);
    }

    @Override
    public void deleteStudentById(Integer id) {
        redisTemplate.opsForHash().delete(KEY,id);

    }

    @Override
    public Student updateStudentById(Integer id, Student student) {
        redisTemplate.opsForHash().put(KEY,id,student);
        return student;
    }
}
