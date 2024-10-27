package bj.fruitsetlegumes.api.stub;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryFruitsRepository implements bj.fruitsetlegumes.api.domain.ports.FruitsRepository{
    @Override
    public List<Fruit> findAll() {
        return List.of(new Fruit(1, "Mangue"), new Fruit(2, "Ananas"), new Fruit(3, "Coco"));
    }
}
