package bj.fruitsetlegumes.api;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FruitsRessourcesIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FruitsRepository fruitsRepository;


    @Test
    void shouldReturnAllFruits() throws Exception {

        saveFruits(
                List.of(
                        new Fruit(UUID.fromString("a546cc47-28e1-48e9-b28e-088b90a72798"), "Mangue"),
                        new Fruit(UUID.fromString("4ee87008-29d4-41f2-8355-dffcbf2fbcdf"), "Ananas"),
                        new Fruit(UUID.fromString("2f11ba73-049e-4062-9f8c-96ed337b21db"), "Coco")
                )
        );

        String expectedFruitsJson =
                """
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
        String newFruitJson =
                """
                        {"name": "Pomme"}
                        """;

        this.mockMvc.perform(
                post("/fruits")
                        .content(newFruitJson)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    void shouldSearchFruitById() throws Exception {
        Fruit fruit = saveNewFruit();

        this.mockMvc.perform(get("/fruits/" + fruit.id()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"id": "%s", "name": "%s"}
                        """.formatted(fruit.id(), fruit.name())));
    }

    @Test
    void shouldReturnFruitNotFoundWhenFruitDoesNotExists() throws Exception {
        this.mockMvc.perform(get("/fruits/" + UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    private Fruit saveNewFruit() {
        return fruitsRepository.save(new Fruit(UUID.randomUUID(), "Pomme"));
    }

    private void saveFruits(List<Fruit> fruits)  {
        fruits.forEach(fruit -> fruitsRepository.save(fruit));
    }
}
