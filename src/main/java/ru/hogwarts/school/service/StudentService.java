package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    Map<Long, Student> students = new HashMap<Long, Student>();

    private long id = 0;

    public Student createStudent(Student student) {
        student.setId(++id);
        students.put(id, student);
        return student;
    }

    public Student findStudent(long id){
        return students.get(id);
    }

    public Student editStudent(Student student){
        students.put(student.getId(),student);
        return student;
    }

    public Student deleteStudent(long id){
        return students.remove(id);
    }

    public Collection<Student> getStudentsByAge(int age){
        return students.values()
                .stream()
                .filter(p->(p.getAge()>age))
                .collect(Collectors.toList());
    }
}
