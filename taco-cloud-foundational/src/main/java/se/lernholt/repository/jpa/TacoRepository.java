package se.lernholt.repository.jpa;

import org.springframework.data.repository.CrudRepository;

import se.lernholt.tacos.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
