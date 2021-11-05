package se.lernholt.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import se.lernholt.repository.jpa.IngredientRepository;
import se.lernholt.tacos.Ingredient;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") String id) {
        Optional<Ingredient> ingredientOpt = ingredientRepository.findById(id);
        if (ingredientOpt.isPresent()) {
            Ingredient ingredient = ingredientOpt.get();
            return ResponseEntity.ok(ingredient);
        }
        return ResponseEntity.notFound().build();
    }

}
