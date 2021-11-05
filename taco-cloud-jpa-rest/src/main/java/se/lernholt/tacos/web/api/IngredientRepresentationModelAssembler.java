package se.lernholt.tacos.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import se.lernholt.tacos.Ingredient;

public class IngredientRepresentationModelAssembler
        extends RepresentationModelAssemblerSupport<Ingredient, IngredientEntityModel> {

    public IngredientRepresentationModelAssembler() {
        super(null, IngredientEntityModel.class);
    }

    @Override
    protected IngredientEntityModel instantiateModel(Ingredient entity) {
        return new IngredientEntityModel(entity);
    }

    @Override
    public IngredientEntityModel toModel(Ingredient entity) {
        String id = entity.getId();
        return createModelWithId(id, entity);
    }
}
