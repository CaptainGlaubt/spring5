package se.lernholt.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.lernholt.tacos.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
