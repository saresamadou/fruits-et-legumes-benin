package bj.fruitsetlegumes.api.domain.usecases;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsCatalog;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetAllFruitsUsecase {

    private final FruitsCatalog fruitsCatalog;

    public GetAllFruitsUsecase(FruitsCatalog fruitsCatalog) {
        this.fruitsCatalog = fruitsCatalog;
    }

    public List<Fruit> getFruitList() {
        return fruitsCatalog.findAll();
    }
}
