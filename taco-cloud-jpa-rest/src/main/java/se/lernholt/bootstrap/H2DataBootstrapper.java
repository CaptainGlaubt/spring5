package se.lernholt.bootstrap;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import se.lernholt.repository.jpa.IngredientRepository;
import se.lernholt.tacos.Ingredient;
import se.lernholt.tacos.Ingredient.Type;

@Configuration
@RequiredArgsConstructor
public class H2DataBootstrapper {

    private final IngredientRepository ingredientRepository;

    @PostConstruct
    public void bootstrap() {
        storeIngredient("FLTO", "Flour Tortilla", Type.WRAP);
        storeIngredient("COTO", "Corn Tortilla", Type.WRAP);
        storeIngredient("GRBF", "Ground Beef", Type.PROTEIN);
        storeIngredient("CARN", "Carnitas", Type.PROTEIN);
        storeIngredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
        storeIngredient("LETC", "Lettuce", Type.VEGGIES);
        storeIngredient("CHED", "Cheddar", Type.CHEESE);
        storeIngredient("JACK", "Monterrey Jack", Type.CHEESE);
        storeIngredient("SLSA", "Salsa", Type.SAUCE);
        storeIngredient("SRCR", "Sour Cream", Type.SAUCE);
    }

    private void storeIngredient(String id, String name, Type type) {
        Ingredient ingredient = new Ingredient(id, name, type);
        ingredientRepository.save(ingredient);
    }
}
