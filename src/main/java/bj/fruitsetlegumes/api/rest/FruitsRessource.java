package bj.fruitsetlegumes.api.rest;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.usecases.GetAllFruitsUsecase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FruitsRessource {

    private final GetAllFruitsUsecase getAllFruitsUsecase;

    public FruitsRessource(GetAllFruitsUsecase getAllFruitsUsecase) {
        this.getAllFruitsUsecase = getAllFruitsUsecase;
    }

    @RequestMapping("/fruits")
    public @ResponseBody List<Fruit> getFruits() {
        return getAllFruitsUsecase.getFruitList();
    }


}
