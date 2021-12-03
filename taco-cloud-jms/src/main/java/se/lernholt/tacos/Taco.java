package se.lernholt.tacos;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Taco {

    private Long             id;
    private Date             createdAt;
    private String           name;
    private List<Ingredient> ingredients;
}
