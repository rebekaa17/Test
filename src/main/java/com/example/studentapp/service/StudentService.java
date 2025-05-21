package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student update(Long id, Student updatedStudent) {
        Student s = repository.findById(id).orElseThrow();
        s.setName(updatedStudent.getName());
        s.setAge(updatedStudent.getAge());
        return repository.save(s);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
