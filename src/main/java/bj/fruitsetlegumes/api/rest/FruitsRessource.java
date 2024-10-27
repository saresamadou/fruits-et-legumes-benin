package bj.fruitsetlegumes.api.rest;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.usecases.CreateFruitUseCase;
import bj.fruitsetlegumes.api.domain.usecases.GetAllFruitsUsecase;
import bj.fruitsetlegumes.api.domain.usecases.GetFruitUseCase;
import bj.fruitsetlegumes.api.domain.usecases.command.CreateFruitCommand;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FruitsRessource {

    private final GetAllFruitsUsecase getAllFruitsUsecase;
    private final CreateFruitUseCase createFruitUseCase;
    private final GetFruitUseCase getFruitUseCase;

    public FruitsRessource(
        GetAllFruitsUsecase getAllFruitsUsecase,
        CreateFruitUseCase createFruitUseCase,
        GetFruitUseCase getFruitUseCase
    ) {
        this.getAllFruitsUsecase = getAllFruitsUsecase;
        this.createFruitUseCase = createFruitUseCase;
        this.getFruitUseCase = getFruitUseCase;
    }

    @GetMapping("/fruits")
    public @ResponseBody List<Fruit> getFruits() {
        return getAllFruitsUsecase.getFruitList();
    }

    @PostMapping(value = "/fruits", produces = "application/json")
    public ResponseEntity<Fruit> createFruit(
        @RequestBody CreateFruitRequest request
    ) {
        CreateFruitCommand command = new CreateFruitCommand(request.name());
        Fruit createdFruit = createFruitUseCase.createFruit(command);
        return new ResponseEntity<>(createdFruit, HttpStatus.CREATED);
    }

    @GetMapping("/fruits/{id}")
    public ResponseEntity<Fruit> getFruit(@PathVariable String id) {
        return getFruitUseCase
            .getFruit(UUID.fromString(id))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
