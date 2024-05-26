package com.nabeel.redisDB.controller;

import com.nabeel.redisDB.exceptions.ResourceNotFoundException;
import com.nabeel.redisDB.model.Student;
import com.nabeel.redisDB.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentDao studentDao;

    @Autowired
    public StudentController(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student){
        return studentDao.save(student);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id){
        return studentDao.getStudentById(id);
    }

    @GetMapping()
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        studentDao.getAllStudents().forEach((key, value) -> studentList.add((Student) value));
        return studentList;

    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        Student existingStudent = studentDao.getStudentById(id);
        if (existingStudent == null) {
            throw new ResourceNotFoundException("Student doesn't exist");
        }
        existingStudent.setName(student.getName());
        existingStudent.setFees(student.getFees());
        studentDao.updateStudentById(id,existingStudent);
        return existingStudent;

    }
}
