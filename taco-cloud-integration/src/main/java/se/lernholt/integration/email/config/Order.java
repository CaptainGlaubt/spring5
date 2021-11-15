package se.lernholt.integration.email.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class Order {

    private final String email;
    private List<Taco>   tacos;

    public void addTaco(Taco taco) {
        if (Objects.isNull(tacos)) {
            tacos = new ArrayList<>();
        }
        tacos.add(taco);
    }
}