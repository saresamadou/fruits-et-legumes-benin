package bj.fruitsetlegumes.api.infrastructure.secondary.repositories.jpa;

import bj.fruitsetlegumes.api.infrastructure.secondary.repositories.jpa.persistencemodels.FruitPm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringJpaRepository extends JpaRepository<FruitPm, UUID> {

}
