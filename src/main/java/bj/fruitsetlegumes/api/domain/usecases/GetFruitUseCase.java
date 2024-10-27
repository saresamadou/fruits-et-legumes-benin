package bj.fruitsetlegumes.api.domain.usecases;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
public class GetFruitUseCase {

    private final FruitsRepository fruitsRepository;


    public GetFruitUseCase(FruitsRepository fruitsRepository) {
        this.fruitsRepository = fruitsRepository;
    }

    public Optional<Fruit> getFruit(UUID id) {
        return fruitsRepository.finfById(id);
    }
}
