package bj.fruitsetlegumes.api.infrastructure.jpa;

import bj.fruitsetlegumes.api.domain.entities.Fruit;
import bj.fruitsetlegumes.api.infrastructure.jpa.persistencemodels.FruitPm;

public class FruitPmConverter {

    public static FruitPm toPm(Fruit fruit) {
        var fruitSnapashot = fruit.takeSnapshot();

        return new FruitPm(fruitSnapashot.id(), fruitSnapashot.name());
    }

    public static Fruit toDomain(FruitPm fruitPm) {
        return new Fruit(fruitPm.getId(), fruitPm.getName());
    }
}

