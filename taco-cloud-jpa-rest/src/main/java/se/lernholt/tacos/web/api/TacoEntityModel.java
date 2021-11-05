package se.lernholt.tacos.web.api;

import java.util.Date;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import se.lernholt.tacos.Taco;

@Getter
@Relation(itemRelation = "taco", collectionRelation = "tacos")
public class TacoEntityModel extends EntityModel<Taco> {

    private static final IngredientRepresentationModelAssembler INGREDIENT_REPRESENTATION_MODEL_ASSEMBLER = new IngredientRepresentationModelAssembler();

    private final String                                        name;
    private final Date                                          createdAt;
    private final CollectionModel<IngredientEntityModel>        ingredients;

    public TacoEntityModel(Taco taco) {
        name = taco.getName();
        createdAt = taco.getCreatedAt();
        ingredients = INGREDIENT_REPRESENTATION_MODEL_ASSEMBLER.toCollectionModel(taco.getIngredients());
    }
}
