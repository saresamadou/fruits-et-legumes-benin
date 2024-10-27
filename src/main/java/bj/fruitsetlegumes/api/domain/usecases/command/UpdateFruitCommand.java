package bj.fruitsetlegumes.api.domain.usecases.command;

import java.util.UUID;

public record UpdateFruitCommand(UUID id, String name) {}
