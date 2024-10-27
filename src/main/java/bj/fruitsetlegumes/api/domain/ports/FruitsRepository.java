package bj.fruitsetlegumes.api.domain.ports;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import java.util.List;
import java.util.UUID;

public interface FruitsRepository {
    List<Fruit> findAll();

    Fruit save(Fruit fruit);

    Fruit finfById(UUID id);
}
