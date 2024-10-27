package bj.fruitsetlegumes.api.stub;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.InputStream;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class InMemoryFruitsRepository implements FruitsRepository {

    private final JsonMapper jsonMapper = new JsonMapper();

    @Override
    public List<Fruit> findAll() {
        try (
            InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream("fruits.json")
        ) {
            if (inputStream == null) {
                throw new RuntimeException("fruits.json not found");
            }
            return jsonMapper.readValue(inputStream, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Fruit save(Fruit fruit) {
        return fruit;
    }
}
