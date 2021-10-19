package se.lernholt.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import se.lernholt.repository.jdbc.IngredientRepository;
import se.lernholt.tacos.Ingredient;

@Component
@RequiredArgsConstructor
public class IngredientConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findOne(id);
    }
}
