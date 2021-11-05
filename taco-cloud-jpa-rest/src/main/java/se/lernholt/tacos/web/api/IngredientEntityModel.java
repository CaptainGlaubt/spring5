package se.lernholt.tacos.web.api;

import org.springframework.hateoas.EntityModel;

import lombok.Getter;
import se.lernholt.tacos.Ingredient;
import se.lernholt.tacos.Ingredient.Type;

@Getter
public class IngredientEntityModel extends EntityModel<Ingredient> {

    private final String name;
    private final Type   type;

    public IngredientEntityModel(Ingredient ingredient) {
        name = ingredient.getName();
        type = ingredient.getType();
    }
}
