package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {

    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Was invoked method for find faculty");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for edit faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getFacultiesByColor(String color) {
        logger.info("Was invoked method for find faculties by color");
        return facultyRepository.findByColorLike(color);
    }

    public Collection<Faculty> getFacultiesByNameOrColor(String nameOrColor) {
        logger.info("Was invoked method for find faculties by name or color");
        return facultyRepository.findByNameLikeIgnoreCaseOrColorLikeIgnoreCase(nameOrColor, nameOrColor);
    }

    public String longestName() {
        return facultyRepository.findAll().stream()
                .map(s -> s.getName())
                .sorted((str1, str2) -> str2.length() - str1.length())
                .findFirst()
                .get();
    }
}
