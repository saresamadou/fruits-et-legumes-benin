package bj.fruitsetlegumes.api.infrastructure.secondary.repositories.jpa.persistencemodels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "t_fruits")
public class FruitPm {

    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    public FruitPm() {
    }

    public FruitPm(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
