package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FacultyController.class)
class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void testFacultyController() throws Exception {
        long id1 = 1;
        String name1 = "First";
        String color1 = "Color1";
        long id2 = 2;
        String name2 = "Second";
        String color2 = "Color2";

        JSONObject facultyObject1 = new JSONObject();
        facultyObject1.put("id", id1);
        facultyObject1.put("name", name1);
        facultyObject1.put("color", color1);

        Faculty faculty1 = new Faculty();
        faculty1.setId(id1);
        faculty1.setName(name1);
        faculty1.setColor(color1);
        Faculty faculty2 = new Faculty();
        faculty2.setId(id2);
        faculty2.setName(name2);
        faculty2.setColor(color2);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject1.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.color").value(color1));

        when(facultyRepository.findById(any())).thenReturn(Optional.of(faculty1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.color").value(color1));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject1.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.color").value(color1));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(facultyRepository, atLeastOnce()).deleteById(id1);

        when(facultyRepository.findByColorLike(color1)).thenReturn(List.of(faculty1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("faculty/bycolor/{color)", color1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString((List.of(faculty1)))));
    }
}