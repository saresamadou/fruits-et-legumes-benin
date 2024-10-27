package bj.fruitsetlegumes.api.stub;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class InMemoryFruitsRepository implements FruitsRepository {

    private final JsonMapper jsonMapper = new JsonMapper();

    String defaultFruits =
            """
                    [
                        {"id": "a546cc47-28e1-48e9-b28e-088b90a72798", "name": "Mangue"},
                        {"id": "4ee87008-29d4-41f2-8355-dffcbf2fbcdf", "name": "Ananas"},
                        {"id": "2f11ba73-049e-4062-9f8c-96ed337b21db", "name": "Coco"}
                    ]
                    """;

    InMemoryFruitsRepository() {
        try {
            jsonMapper.writeValue(
                    new File(Objects.requireNonNull(getClass().getClassLoader().getResource("fruits.json")).getFile()),
                    jsonMapper.readValue(defaultFruits, new TypeReference<>() {
                    })
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Fruit> findAll() {
        return readFruits();
    }

    private List<Fruit> readFruits() {
        try (
            InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("fruits.json")
        ) {
            if (inputStream == null) {
                throw new RuntimeException("fruits.json not found");
            }
            return jsonMapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Fruit save(Fruit fruit) {
        List<Fruit> fruits = readFruits();

        fruits.add(fruit);

        updateFruits(fruits);

        return fruit;
    }

    private void updateFruits(List<Fruit> fruits) {
        try {
            jsonMapper.writeValue(
                    new File(Objects.requireNonNull(getClass().getClassLoader().getResource("fruits.json")).getFile()),
                    fruits
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Fruit> finfById(UUID id) {
        List<Fruit> fruits = readFruits();

        return fruits.stream()
            .filter(fruit -> fruit.id().equals(id))
            .findFirst();
    }
}
