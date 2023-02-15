package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for find student");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getStudentsByAge(int age) {
        logger.info("Was invoked method for find students");
        return studentRepository.findByAgeGreaterThan(age);
    }

    public Collection<Student> getStudentsByAgeBetween(int min, int max) {
        logger.info("Was invoked method for find students by age");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Integer countStudents() {
        logger.info("Was invoked method for count of students");
        return studentRepository.countStudents();
    }

    public Integer averageAge() {
        logger.info("Was invoked method for find student with average age");
        return studentRepository.averageAge();
    }

    public Collection<Student> last5() {
        logger.info("Was invoked method for find last 5 students");
        return studentRepository.last5();
    }

    public List<String> listNamesOfStudentsStartsWithA() {
        return studentRepository.findAll()
                .stream()
                .filter(s -> s.getName().toUpperCase().startsWith("A"))
                .map(s -> s.getName())
                .collect(Collectors.toList());
    }
}
