package bj.fruitsetlegumes.api.rest;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.usecases.CreateFruitCommand;
import bj.fruitsetlegumes.api.domain.usecases.CreateFruitUseCase;
import bj.fruitsetlegumes.api.domain.usecases.GetAllFruitsUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FruitsRessource {

    private final GetAllFruitsUsecase getAllFruitsUsecase;
    private final CreateFruitUseCase createFruitUseCase;

    public FruitsRessource(GetAllFruitsUsecase getAllFruitsUsecase, CreateFruitUseCase createFruitUseCase) {
        this.getAllFruitsUsecase = getAllFruitsUsecase;
        this.createFruitUseCase = createFruitUseCase;
    }

    @GetMapping("/fruits")
    public @ResponseBody List<Fruit> getFruits() {
        return getAllFruitsUsecase.getFruitList();
    }

    @PostMapping(value = "/fruits", produces = "application/json")
    public ResponseEntity<Fruit> createFruit(@RequestBody CreateFruitRequest request) {
        CreateFruitCommand command = new CreateFruitCommand(request.name());
        Fruit createdFruit = createFruitUseCase.createFruit(command);
        return new ResponseEntity<>(createdFruit, HttpStatus.CREATED);
    }


}
