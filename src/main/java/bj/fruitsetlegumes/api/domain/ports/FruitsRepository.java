package bj.fruitsetlegumes.api.domain.ports;

import bj.fruitsetlegumes.api.domain.entities.Fruit;

import java.util.List;

public interface FruitsRepository {
    List<Fruit> findAll();
}
