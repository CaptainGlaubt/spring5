package se.lernholt.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import se.lernholt.tacos.Ingredient;
import se.lernholt.tacos.Ingredient.Type;

@Repository
@RequiredArgsConstructor
public class JdbcIngredientRepository implements IngredientRepository {

    private final JdbcTemplate jdbc;

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, type from Ingredient", JdbcIngredientRepository::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbc.queryForObject("select id, name, type from Ingredient where id=?",
                JdbcIngredientRepository::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        String id = ingredient.getId();
        String name = ingredient.getName();
        String type = ingredient.getType().toString();
        jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)", id, name, type);
        return ingredient;
    }

    private static Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        String id = rs.getString("id");
        String name = rs.getString("name");
        String typeString = rs.getString("type");
        Type type = Type.valueOf(typeString);
        return new Ingredient(id, name, type);
    }

}
