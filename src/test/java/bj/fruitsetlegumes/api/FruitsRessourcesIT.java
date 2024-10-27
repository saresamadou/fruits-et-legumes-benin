package bj.fruitsetlegumes.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                    {"id": 1, "name": "Mangue"},
                    {"id": 2, "name": "Ananas"},
                    {"id": 3, "name": "Coco"}
                ]
                """;

        this.mockMvc.perform(get("/fruits"))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedFruitsJson));
    }
}
