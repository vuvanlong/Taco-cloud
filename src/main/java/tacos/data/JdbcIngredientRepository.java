package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;

@Repository
public class JdbcIngredientRepository
implements IngredientRepository {
private JdbcTemplate jdbc;
@Autowired
public JdbcIngredientRepository(JdbcTemplate jdbc) {
this.jdbc = jdbc;
}
...
}