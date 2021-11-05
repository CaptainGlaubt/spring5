package se.lernholt.client;

import java.util.Collection;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import se.lernholt.tacos.Ingredient;
import se.lernholt.tacos.Taco;

@Component
@RequiredArgsConstructor
public class TacoCloudTraversonClient {

    private final Traverson        traverson;
    private final TestRestTemplate restTemplate;

    public Collection<Ingredient> findAllIngredients() {
        ParameterizedTypeReference<CollectionModel<Ingredient>> ingredientType = new ParameterizedTypeReference<CollectionModel<Ingredient>>() {
        };
        return traverson.follow("ingredients").toObject(ingredientType).getContent();
    }

    public Collection<Taco> findRecentTacos() {
        ParameterizedTypeReference<CollectionModel<Taco>> tacoType = new ParameterizedTypeReference<CollectionModel<Taco>>() {
        };
        return traverson.follow("tacos").follow("recents").toObject(tacoType).getContent();
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        String ingredientsUrl = traverson.follow("ingredients").asLink().getHref();
        return restTemplate.postForObject(ingredientsUrl, ingredient, Ingredient.class);
    }
}
