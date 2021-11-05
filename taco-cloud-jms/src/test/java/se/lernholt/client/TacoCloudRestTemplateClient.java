package se.lernholt.client;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import se.lernholt.tacos.Ingredient;

@Component
@RequiredArgsConstructor
@Slf4j
public class TacoCloudRestTemplateClient {

    private final TestRestTemplate restTemplate;

    public Ingredient findIngredientById1(String id) {
        return restTemplate.getForObject("/ingredient/{id}", Ingredient.class, id);
    }

    public Ingredient findIngredientById2(String id) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        return restTemplate.getForObject("/ingredient/{id}", Ingredient.class, uriVariables);
    }

    public Ingredient findIngredientById3(String id) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", id);
        URI requestUri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/ingredient/{id}").build(uriVariables);
        return restTemplate.getForObject(requestUri, Ingredient.class);
    }

    public Ingredient getIngredientById4(String ingredientId) {
        ResponseEntity<Ingredient> responseEntity = restTemplate.getForEntity("/ingredients/{id}", Ingredient.class,
                ingredientId);
        log.info("Fetched time: {}.", responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    public void updateIngredient(Ingredient ingredient) {
        String id = ingredient.getId();
        restTemplate.put("/ingredients/{id}", ingredient, id);
    }

    public void deleteIngredient(Ingredient ingredient) {
        String id = ingredient.getId();
        restTemplate.delete("/ingredients/{id}", id);
    }

    public Ingredient createIngredient1(Ingredient ingredient) {
        return restTemplate.postForObject("/ingredients", ingredient, Ingredient.class);
    }

    public URI createIngredient2(Ingredient ingredient) {
        return restTemplate.postForLocation("/ingredients", ingredient);
    }

    public Ingredient createIngredient3(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity = restTemplate.postForEntity("/ingredients", ingredient,
                Ingredient.class);
        log.info("New resource created at {}.", responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }

    
}
