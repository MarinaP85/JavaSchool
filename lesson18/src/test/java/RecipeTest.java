import com.sber.springjdbc.config.TransactionalConfig;
import com.sber.springjdbc.dao.RecipeDao;
import com.sber.springjdbc.model.Ingredient;
import com.sber.springjdbc.model.Recipe;
import org.h2.tools.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.sql.SQLException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TransactionalConfig.class)
public class RecipeTest {
    @Autowired
    public RecipeDao recipeDao;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @BeforeAll
    public static void startServer() throws SQLException {
        Server.createTcpServer().start();
    }

    @AfterEach
    public void clear() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Recipes", "Ingredients");
    }

    @Test
    public void recipeDaoTest() {
        Recipe recipe1 = new Recipe("Салат греческий");
        recipe1.addIngredients(new Ingredient("Оливки", "50 гр"));
        recipe1.addIngredients(new Ingredient("Сыр", "100 гр"));
        recipe1.addIngredients(new Ingredient("Орурец", "1 шт"));
        recipe1.addIngredients(new Ingredient("Помидор", "2 шт"));
        recipe1.addIngredients(new Ingredient("Лук", "1/2 шт"));
        System.out.println(recipeDao.createRecipe(recipe1).toString());

        Recipe recipe2 = new Recipe("Салат летний");
        recipe2.addIngredients(new Ingredient("Орурец", "2 шт"));
        recipe2.addIngredients(new Ingredient("Помидор", "3 шт"));
        recipe2.addIngredients(new Ingredient("Лук", "1 шт"));
        recipe2.addIngredients(new Ingredient("Редис", "5 шт"));
        recipe2.addIngredients(new Ingredient("Перец сладкий", "1 шт"));
        System.out.println(recipeDao.createRecipe(recipe2).toString());

        List<Recipe> salads = recipeDao.searchRecipe("Салат");
        salads.forEach(salad -> System.out.println(salad.toString()));
        Assertions.assertEquals(salads.get(0).getTitle(), "Салат греческий");
    }
}
