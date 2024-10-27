package bj.fruitsetlegumes.api.domain.entities;

import java.util.UUID;

public record Fruit(UUID id, String name) {
    public FruitSnapshot takeSnapshot() {
        return new FruitSnapshot(id, name);
    }

    public record FruitSnapshot(UUID id, String name) {}
}
