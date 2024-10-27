package bj.fruitsetlegumes.api.domain.usecases;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsCatalog;
import bj.fruitsetlegumes.api.domain.usecases.command.CreateFruitCommand;
import bj.fruitsetlegumes.api.domain.usecases.command.UpdateFruitCommand;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CreateFruitUseCase {

    private final FruitsCatalog fruitsCatalog;

    public CreateFruitUseCase(FruitsCatalog fruitsCatalog) {
        this.fruitsCatalog = fruitsCatalog;
    }

    public Fruit createFruit(CreateFruitCommand command) {
        Fruit fruit = new Fruit(UUID.randomUUID(), command.name());
        return fruitsCatalog.save(fruit);
    }

    public Optional<Fruit> updateFruit(UUID uuid, UpdateFruitCommand command) {
        return fruitsCatalog
            .finfById(uuid)
            .map(foundFruit -> new Fruit(foundFruit.id(), command.name()))
            .flatMap(fruitsCatalog::update);
    }
}
