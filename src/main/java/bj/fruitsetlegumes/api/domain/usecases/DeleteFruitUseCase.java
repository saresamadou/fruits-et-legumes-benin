package bj.fruitsetlegumes.api.domain.usecases;

import bj.fruitsetlegumes.api.domain.ports.FruitsCatalog;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteFruitUseCase {

    private final FruitsCatalog fruitsCatalog;

    public DeleteFruitUseCase(FruitsCatalog fruitsCatalog) {
        this.fruitsCatalog = fruitsCatalog;
    }

    public void deleteFruit(UUID id) {
        fruitsCatalog
            .finfById(id)
            .ifPresent(fruitsCatalog::delete);
    }
}
