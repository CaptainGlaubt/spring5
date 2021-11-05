package se.lernholt.tacos.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import se.lernholt.controller.DesignTacoController;
import se.lernholt.tacos.Taco;

public class TacoRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoEntityModel> {

    public TacoRepresentationModelAssembler() {
        super(DesignTacoController.class, TacoEntityModel.class);
    }

    @Override
    protected TacoEntityModel instantiateModel(Taco entity) {
        return new TacoEntityModel(entity);
    }

    @Override
    public TacoEntityModel toModel(Taco entity) {
        Long id = entity.getId();
        return createModelWithId(id, entity);
    }
}
