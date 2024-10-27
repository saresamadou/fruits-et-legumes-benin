package bj.fruitsetlegumes.api.domain.usecases;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsRepository;
import bj.fruitsetlegumes.api.domain.usecases.command.UpdateFruitCommand;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateFruitUseCase {

    private final FruitsRepository fruitsRepository;

    public UpdateFruitUseCase(FruitsRepository fruitsRepository) {
        this.fruitsRepository = fruitsRepository;
    }

    public Optional<Fruit> updateFruit(UpdateFruitCommand command) {
        return fruitsRepository
            .finfById(command.id())
            .map(foundFruit -> new Fruit(foundFruit.id(), command.name()))
            .flatMap(fruitsRepository::update);
    }
}
