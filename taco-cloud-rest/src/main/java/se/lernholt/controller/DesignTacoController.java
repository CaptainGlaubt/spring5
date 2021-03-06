package se.lernholt.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import se.lernholt.repository.jpa.TacoRepository;
import se.lernholt.tacos.Taco;
import se.lernholt.tacos.web.api.TacoEntityModel;
import se.lernholt.tacos.web.api.TacoRepresentationModelAssembler;

@RestController
@RequestMapping(path = "/design", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DesignTacoController {

    private final TacoRepository tacoRepository;

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> getTacoById(@PathVariable("id") Long id) {
        Optional<Taco> tacoOpt = tacoRepository.findById(id);
        if (tacoOpt.isPresent()) {
            Taco taco = tacoOpt.get();
            return ResponseEntity.ok(taco);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/recent")
    public CollectionModel<TacoEntityModel> getRecentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepository.findAll(page).getContent();
        CollectionModel<TacoEntityModel> tacoEntityModels = new TacoRepresentationModelAssembler()
                .toCollectionModel(tacos);
        tacoEntityModels.add(
                WebMvcLinkBuilder.linkTo(methodOn(DesignTacoController.class).getRecentTacos()).withRel("recents"));
        return tacoEntityModels;
    }

}
