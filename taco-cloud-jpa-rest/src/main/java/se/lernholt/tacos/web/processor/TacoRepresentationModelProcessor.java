package se.lernholt.tacos.web.processor;

import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import se.lernholt.tacos.Taco;
import se.lernholt.tacos.web.api.TacoEntityModel;

@Component
@RequiredArgsConstructor
public class TacoRepresentationModelProcessor implements RepresentationModelProcessor<TacoEntityModel> {

    private final EntityLinks entityLinks;

    @Override
    public TacoEntityModel process(TacoEntityModel model) {
        model.add(entityLinks.linkFor(Taco.class).slash("recent").withRel("recents"));
        return model;
    }
}
