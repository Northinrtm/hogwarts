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

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

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
                .map(s -> s.getName().toUpperCase())
                .filter(s -> s.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    public void printNames() {
        List<Student> studentList = studentRepository.findAll();
        String name0 = studentList.get(0).getName();
        String name1 = studentList.get(1).getName();
        String name2 = studentList.get(2).getName();
        String name3 = studentList.get(3).getName();
        String name4 = studentList.get(4).getName();
        String name5 = studentList.get(5).getName();

        System.out.println("0: " + name0);
        System.out.println("1: " + name1);

        new Thread(() -> {
            System.out.println("2: " + name2);
            System.out.println("3: " + name3);
        }).start();

        new Thread(() -> {
            System.out.println("4: " + name4);
            System.out.println("5: " + name5);
        }).start();
    }

    public void print2() {
        printName(0);
        printName(1);

        new Thread(() -> {
            printName(2);
            printName(3);
        }).start();

        new Thread(() -> {
            printName(4);
            printName(5);
        }).start();
    }

    public void printName(int i) {
        System.out.println(i + ": " + studentRepository.findAll().get(i).getName());
    }
}
