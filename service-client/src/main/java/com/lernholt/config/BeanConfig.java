package com.lernholt.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean
    @LoadBalanced //Qualifier, needs to be annoteded on injections.
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
//    public Ingredient getIngredientById(String ingredientId) {
//        return rest.getForObject(
//        "http://ingredient-service/ingredients/{id}",
//        Ingredient.class, ingredientId);
//       }
    
//    public Mono<Ingredient> getIngredientById(String ingredientId) {
//        return wcBuilder.build()
//        .get()
//        .uri("http://ingredient-service/ingredients/{id}", ingredientId)
//        .retrieve().bodyToMono(Ingredient.class);
//       }
    
    
}
