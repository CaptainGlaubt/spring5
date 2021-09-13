package se.lernholt.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.RequiredArgsConstructor;
import se.lernholt.repository.IngredientRepository;
import se.lernholt.repository.TacoRepository;
import se.lernholt.tacos.Ingredient;
import se.lernholt.tacos.Ingredient.Type;
import se.lernholt.tacos.Order;
import se.lernholt.tacos.Taco;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@RequiredArgsConstructor
public class DesignTacoController {

    private static final List<Ingredient> INGREDIENTS = Arrays.asList(
            new Ingredient("FLTO", "Flour Tortilla", Type.WRAP), new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            new Ingredient("GRBF", "Ground Beef", Type.PROTEIN), new Ingredient("CARN", "Carnitas", Type.PROTEIN),
            new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), new Ingredient("LETC", "Lettuce", Type.VEGGIES),
            new Ingredient("CHED", "Cheddar", Type.CHEESE), new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
            new Ingredient("SLSA", "Salsa", Type.SAUCE), new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

    private final TacoRepository tacoRepository;
    private final IngredientRepository ingredientRepository;

    @GetMapping
    public String showDesignForm(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        for (Type type : Ingredient.Type.values()) {
            String typeNameLower = type.name().toLowerCase();
            List<Ingredient> typeIngredients = StreamSupport.stream(ingredients.spliterator(), false)
                    .filter(ingredient -> ingredient.getType().equals(type))
                    .collect(Collectors.toList());
            model.addAttribute(typeNameLower, typeIngredients);
            model.addAttribute("design", new Taco());
        }
        return "design";
    }

    @PostMapping
    public String processDesign(Model model, @ModelAttribute("design") @Valid Taco design, Errors errors,
            @ModelAttribute("order") Order order) {
        if (errors.hasErrors()) {
            appendIngredientsToModel(model);
            return "design";
        }
        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }

    @ModelAttribute("order")
    public Order getOrder() {
        return new Order();
    }

    @ModelAttribute("taco")
    public Taco getTaco() {
        return new Taco();
    }

    private static void appendIngredientsToModel(Model model) {
        for (Type type : Ingredient.Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(INGREDIENTS, type));
        }
    }

    private static List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
