package ru.hogwarts.school.controller;

import liquibase.pro.packaged.S;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> studentInfo(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> editFaculty(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/byage/{age}")
    public ResponseEntity<Collection<Student>> getStudentsByAge(@PathVariable("age") int age) {
        return ResponseEntity.ok(studentService.getStudentsByAge(age));
    }

    @GetMapping("/byagebetween")
    public ResponseEntity<Collection<Student>> getStudentsByAgeBetween(
            @RequestParam int min,
            @RequestParam int max) {
        return ResponseEntity.ok(studentService.getStudentsByAgeBetween(min, max));
    }

    @GetMapping("/faculty")
    public ResponseEntity<Faculty> getFaculty(@RequestParam Long studentId) {
        return ResponseEntity.ok(studentService.findStudent(studentId).getFaculty());
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countStudents() {
        return ResponseEntity.ok(studentService.countStudents());
    }

    @GetMapping("/age")
    public ResponseEntity<Integer> averageAge() {
        return ResponseEntity.ok(studentService.averageAge());
    }

    @GetMapping("/limit5")
    public ResponseEntity<Collection<Student>> last5() {
        return ResponseEntity.ok(studentService.last5());
    }

    @GetMapping("/namesa")
    public ResponseEntity<List<String>> listNamesOfStudentsStartsWithA() {
        return ResponseEntity.ok(studentService.listNamesOfStudentsStartsWithA());
    }

    @GetMapping("/print")
    public void printNames() {
        studentService.printNames();
    }

    @GetMapping("/print2")
    public void print2() {
        studentService.print2();
    }
}
