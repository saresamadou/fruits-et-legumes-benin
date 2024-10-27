package bj.fruitsetlegumes.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FruitsRessourcesIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllFruits() throws Exception {
        String expectedFruitsJson = """
                [
                    {"id": "a546cc47-28e1-48e9-b28e-088b90a72798", "name": "Mangue"},
                    {"id": "4ee87008-29d4-41f2-8355-dffcbf2fbcdf", "name": "Ananas"},
                    {"id": "2f11ba73-049e-4062-9f8c-96ed337b21db", "name": "Coco"}
                ]
                """;

        this.mockMvc.perform(get("/fruits"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedFruitsJson));
    }

    @Test
    void shouldAddNewFruit() throws Exception {
        String newFruitJson = """
                {"name": "Pomme"}
                """;

        this.mockMvc.perform(post("/fruits")
                        .content(newFruitJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
