package bj.fruitsetlegumes.api.domain.usecases;

import bj.fruitsetlegumes.api.domain.ports.FruitsRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteFruitUseCase {

    private final FruitsRepository fruitsRepository;

    public DeleteFruitUseCase(FruitsRepository fruitsRepository) {
        this.fruitsRepository = fruitsRepository;
    }

    public void deleteFruit(UUID id) {
        fruitsRepository
            .finfById(id)
            .ifPresent(fruitsRepository::delete);
    }
}
