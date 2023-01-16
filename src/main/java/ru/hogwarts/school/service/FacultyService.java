package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    Map<Long, Faculty> faculties = new HashMap<Long, Faculty>();

    private long id;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++id);
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id){
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty){
        faculties.put(faculty.getId(),faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id){
        return faculties.remove(id);
    }
}
