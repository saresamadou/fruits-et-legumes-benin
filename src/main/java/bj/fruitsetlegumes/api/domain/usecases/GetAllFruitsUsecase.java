package bj.fruitsetlegumes.api.domain.usecases;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.ports.FruitsRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllFruitsUsecase {

    private final FruitsRepository fruitsRepository;

    public GetAllFruitsUsecase(FruitsRepository fruitsRepository) {
        this.fruitsRepository = fruitsRepository;
    }

    public List<Fruit> getFruitList() {
        return fruitsRepository.findAll();
    }
}
