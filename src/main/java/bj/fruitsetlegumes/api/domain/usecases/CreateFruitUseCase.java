package bj.fruitsetlegumes.api.domain.usecases;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

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
}
