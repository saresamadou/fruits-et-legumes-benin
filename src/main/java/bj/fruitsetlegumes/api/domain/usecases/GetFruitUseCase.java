package bj.fruitsetlegumes.api.domain.usecases;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsCatalog;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class GetFruitUseCase {

    private final FruitsCatalog fruitsCatalog;

    public GetFruitUseCase(FruitsCatalog fruitsCatalog) {
        this.fruitsCatalog = fruitsCatalog;
    }

    public Optional<Fruit> getFruit(UUID id) {
        return fruitsCatalog.finfById(id);
    }
}
