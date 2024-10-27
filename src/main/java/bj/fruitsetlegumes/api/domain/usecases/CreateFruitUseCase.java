package bj.fruitsetlegumes.api.domain.usecases;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsRepository;
import bj.fruitsetlegumes.api.domain.usecases.command.CreateFruitCommand;
import bj.fruitsetlegumes.api.domain.usecases.command.UpdateFruitCommand;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateFruitUseCase {

    private final FruitsRepository fruitsRepository;

    public CreateFruitUseCase(FruitsRepository fruitsRepository) {
        this.fruitsRepository = fruitsRepository;
    }

    public Fruit createFruit(CreateFruitCommand command) {
        Fruit fruit = new Fruit(UUID.randomUUID(), command.name());
        return fruitsRepository.save(fruit);
    }

    public Optional<Fruit> updateFruit(UUID uuid, UpdateFruitCommand command) {
        return fruitsRepository
            .finfById(uuid)
            .map(foundFruit -> new Fruit(foundFruit.id(), command.name()))
            .flatMap(fruitsRepository::update);
    }
}
