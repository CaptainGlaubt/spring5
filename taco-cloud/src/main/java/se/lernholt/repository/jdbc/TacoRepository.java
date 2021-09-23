package se.lernholt.repository.jdbc;

import se.lernholt.tacos.Taco;

public interface TacoRepository {
    Taco save(Taco design);
}
