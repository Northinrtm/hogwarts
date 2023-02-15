package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByColorLike(String color);

    List<Faculty> findByNameLikeIgnoreCaseOrColorLikeIgnoreCase(String name, String color);

}
