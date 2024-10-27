package bj.fruitsetlegumes.api.domain.usecases;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsCatalog;
import bj.fruitsetlegumes.api.domain.usecases.command.CreateFruitCommand;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

}
