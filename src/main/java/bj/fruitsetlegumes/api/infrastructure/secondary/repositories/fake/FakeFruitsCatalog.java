package bj.fruitsetlegumes.api.infrastructure.secondary.repositories.fake;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsCatalog;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Profile("test")
public class FakeFruitsCatalog implements FruitsCatalog {

    private final List<Fruit> fruits;

    FakeFruitsCatalog() {
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

    @Override
    public Optional<Fruit> update(Fruit fruit) {
        Fruit fruitToUpdate = fruits
            .stream()
            .filter(f -> f.id().equals(fruit.id()))
            .findFirst()
            .orElseThrow();

        Fruit updatedFruit = new Fruit(fruitToUpdate.id(), fruit.name());
        fruits.remove(fruitToUpdate);
        fruits.add(updatedFruit);

        return Optional.of(updatedFruit);
    }

    @Override
    public void delete(Fruit fruit) {
        fruits.remove(fruit);
    }
}
