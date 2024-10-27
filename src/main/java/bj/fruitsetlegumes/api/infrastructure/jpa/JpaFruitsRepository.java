package bj.fruitsetlegumes.api.infrastructure.jpa;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsCatalog;
import bj.fruitsetlegumes.api.infrastructure.jpa.persistencemodels.FruitPm;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Profile("!test")
public class JpaFruitsRepository implements FruitsCatalog {


    private final SpringJpaRepository springJpaRepository;

    public JpaFruitsRepository(SpringJpaRepository springJpaRepository) {
        this.springJpaRepository = springJpaRepository;
    }

    @Override
    public List<Fruit> findAll() {
        return springJpaRepository.findAll().stream()
                .map(fruitPm -> new Fruit(fruitPm.getId(), fruitPm.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Fruit save(Fruit fruit) {
        FruitPm fruitPm = springJpaRepository.save(new FruitPm(fruit.id(), fruit.name()));
        return new Fruit(fruitPm.getId(), fruitPm.getName());
    }

    @Override
    public Optional<Fruit> finfById(UUID id) {
        return springJpaRepository.findById(id)
                .map(fruitPm -> new Fruit(fruitPm.getId(), fruitPm.getName()));
    }

    @Override
    public Optional<Fruit> update(Fruit fruit) {
        return Optional.of(springJpaRepository.save(new FruitPm(fruit.id(), fruit.name())))
                .map(fruitPm -> new Fruit(fruitPm.getId(), fruitPm.getName()));
    }

    @Override
    public void delete(Fruit fruit) {
        springJpaRepository.deleteById(fruit.id());
    }
}
