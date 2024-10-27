package bj.fruitsetlegumes.api.domain.usecases;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsCatalog;
import bj.fruitsetlegumes.api.domain.usecases.command.UpdateFruitCommand;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateFruitUseCase {

    private final FruitsCatalog fruitsCatalog;

    public UpdateFruitUseCase(FruitsCatalog fruitsCatalog) {
        this.fruitsCatalog = fruitsCatalog;
    }

    public Optional<Fruit> updateFruit(UpdateFruitCommand command) {
        return fruitsCatalog
            .finfById(command.id())
            .map(foundFruit -> new Fruit(foundFruit.id(), command.name()))
            .flatMap(fruitsCatalog::update);
    }
}
