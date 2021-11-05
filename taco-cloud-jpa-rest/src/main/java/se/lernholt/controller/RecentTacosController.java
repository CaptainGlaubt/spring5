package se.lernholt.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import se.lernholt.repository.jpa.TacoRepository;
import se.lernholt.tacos.Taco;
import se.lernholt.tacos.web.api.TacoEntityModel;
import se.lernholt.tacos.web.api.TacoRepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RepositoryRestController
public class RecentTacosController {
    private TacoRepository tacoRepo;

    public RecentTacosController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TacoEntityModel>> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();
        CollectionModel<TacoEntityModel> tacoEntityModels = new TacoRepresentationModelAssembler()
                .toCollectionModel(tacos);
        tacoEntityModels.add(linkTo(methodOn(RecentTacosController.class).recentTacos()).withRel("recents"));
        return new ResponseEntity<>(tacoEntityModels, HttpStatus.OK);
    }
}
