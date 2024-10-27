package bj.fruitsetlegumes.api.stub;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class InMemoryFruitsRepository implements FruitsRepository {

    private final List<Fruit> fruits;

    InMemoryFruitsRepository() {
        fruits = new ArrayList<>();
    }

    @Override
    public List<Fruit> findAll() {
        return fruits;
    }

    @Override
    public Fruit save(Fruit fruit) {
        fruits.add(fruit);
        return fruit;
    }

    @Override
    public Optional<Fruit> finfById(UUID id) {
        return fruits
            .stream()
            .filter(fruit -> fruit.id().equals(id))
            .findFirst();
    }
}
