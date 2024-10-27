package bj.fruitsetlegumes.api.domain.ports;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FruitsRepository {
    List<Fruit> findAll();

    Fruit save(Fruit fruit);

    Optional<Fruit> finfById(UUID id);

    Optional<Fruit> update(Fruit fruit);

    void delete(Fruit fruit);
}
