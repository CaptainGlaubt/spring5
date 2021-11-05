package se.lernholt.repository.jpa;

import org.springframework.data.repository.CrudRepository;

import se.lernholt.tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
