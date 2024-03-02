package com.example.es11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student changeIsWorking(Long id, Boolean working) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) return null;
        student.setIsWorking(working);
        return studentRepository.save(student);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student changeStudentId(Long id, Student updateStudent) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) return null;
        student.setId(updateStudent.getId());
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
