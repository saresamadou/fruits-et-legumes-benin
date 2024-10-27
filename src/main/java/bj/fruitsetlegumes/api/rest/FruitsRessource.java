package bj.fruitsetlegumes.api.rest;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.domain.usecases.CreateFruitUseCase;
import bj.fruitsetlegumes.api.domain.usecases.DeleteFruitUseCase;
import bj.fruitsetlegumes.api.domain.usecases.GetAllFruitsUsecase;
import bj.fruitsetlegumes.api.domain.usecases.GetFruitUseCase;
import bj.fruitsetlegumes.api.domain.usecases.command.CreateFruitCommand;
import bj.fruitsetlegumes.api.domain.usecases.command.UpdateFruitCommand;
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

    private final DeleteFruitUseCase deleteFruitUseCase;

    public FruitsRessource(
        GetAllFruitsUsecase getAllFruitsUsecase,
        CreateFruitUseCase createFruitUseCase,
        GetFruitUseCase getFruitUseCase,
        DeleteFruitUseCase deleteFruitUseCase) {
        this.getAllFruitsUsecase = getAllFruitsUsecase;
        this.createFruitUseCase = createFruitUseCase;
        this.getFruitUseCase = getFruitUseCase;
        this.deleteFruitUseCase = deleteFruitUseCase;
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

    @PutMapping("/fruits/{id}")
    public ResponseEntity<Fruit> updateFruit(
        @PathVariable String id,
        @RequestBody UpdateFruitRequest request
    ) {
        UpdateFruitCommand command = new UpdateFruitCommand(request.name());
        return createFruitUseCase
            .updateFruit(UUID.fromString(id), command)
            .map(updatedFruit ->
                new ResponseEntity<>(updatedFruit, HttpStatus.OK)
            )
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/fruits/{id}")
    public ResponseEntity<Void> deleteFruit(@PathVariable String id) {
        deleteFruitUseCase.deleteFruit(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
