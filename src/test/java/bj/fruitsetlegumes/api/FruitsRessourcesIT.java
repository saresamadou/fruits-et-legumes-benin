package bj.fruitsetlegumes.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FruitsRessourcesIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FruitsRepository fruitsRepository;

    @Test
    void shouldReturnAllFruits() throws Exception {
        // given
        saveFruits(
            List.of(
                new Fruit(
                    UUID.fromString("a546cc47-28e1-48e9-b28e-088b90a72798"),
                    "Mangue"
                ),
                new Fruit(
                    UUID.fromString("4ee87008-29d4-41f2-8355-dffcbf2fbcdf"),
                    "Ananas"
                ),
                new Fruit(
                    UUID.fromString("2f11ba73-049e-4062-9f8c-96ed337b21db"),
                    "Coco"
                )
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

        // when
        this.mockMvc.perform(get("/fruits"))
            // then
            .andExpect(status().isOk())
            .andExpect(content().json(expectedFruitsJson));
    }

    @Test
    void shouldAddNewFruit() throws Exception {
        // given
        String newFruitJson =
            """
            {"name": "Pomme"}
            """;

        // when
        this.mockMvc.perform(
                post("/fruits")
                    .content(newFruitJson)
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isCreated()); // then
    }

    @Test
    void shouldSearchFruitById() throws Exception {
        // given
        Fruit fruit = saveNewFruit();

        // when
        this.mockMvc.perform(get("/fruits/" + fruit.id()))
            // then
            .andExpect(status().isOk())
            .andExpect(
                content()
                    .json(
                        """
                        {"id": "%s", "name": "%s"}
                        """.formatted(fruit.id(), fruit.name())
                    )
            );
    }

    @Test
    void shouldReturnFruitNotFoundWhenFruitToGetDoesNotExists()
        throws Exception {
        this.mockMvc.perform(get("/fruits/" + UUID.randomUUID())).andExpect(
                status().isNotFound()
            );
    }

    @Test
    void shouldUpdateAFruit() throws Exception {
        // given
        Fruit fruit = saveNewFruit();

        // when
        var response =
            this.mockMvc.perform(
                    put("/fruits/" + fruit.id())
                        .content(
                            """
                            {"name": "Goyave"}
                            """
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                )
                // then
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        Fruit updatedFruit = getUpdatedFruit(response);
        assertThat(updatedFruit.name()).isEqualTo("Goyave");
    }

    @Test
    void shouldReturnFruitNotFoundWhenFruitToUpdateDoesNotExists()
        throws Exception {
        this.mockMvc.perform(
                put("/fruits/" + UUID.randomUUID())
                    .content(
                        """
                        {"name": "Goyave"}
                        """
                    )
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isNotFound());
    }

    private Fruit getUpdatedFruit(MockHttpServletResponse response)
        throws JsonProcessingException, UnsupportedEncodingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(
            response.getContentAsString(),
            Fruit.class
        );
    }

    private Fruit saveNewFruit() {
        return fruitsRepository.save(new Fruit(UUID.randomUUID(), "Pomme"));
    }

    private void saveFruits(List<Fruit> fruits) {
        fruits.forEach(fruit -> fruitsRepository.save(fruit));
    }
}
