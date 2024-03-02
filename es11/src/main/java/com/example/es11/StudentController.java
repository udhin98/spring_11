package com.example.es11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;

    @PostMapping("/createStudent")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/allStudent")
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/getStudent/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/changeId/{id}")
    public Student changeStudentId(@PathVariable Long id, @RequestBody Student student) {
        return studentService.changeStudentId(id, student);

    }

    @PatchMapping("/changeIsWorking/{id}")
    public Student changeIsWorking(@PathVariable Long id, @RequestParam Boolean working) {
        return studentService.changeIsWorking(id, working);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

}
