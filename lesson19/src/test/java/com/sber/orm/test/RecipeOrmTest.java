package com.sber.orm.test;

import com.sber.orm.model.Ingredient;
import com.sber.orm.model.Recipe;
import com.sber.orm.respository.RecipeRepository;
import org.h2.tools.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EntityScan("com.sber.orm.model")
public class RecipeOrmTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private RecipeRepository recipeRepository;

    @AfterEach
    public void clear() {
        JdbcTestUtils.deleteFromTables(new JdbcTemplate(dataSource), "INGREDIENT", "RECIPE");
    }

    @BeforeAll
    public static void startServer() throws SQLException {
        Server.createTcpServer().start();
    }

    private void doInTransaction(Consumer<EntityManager> consumer) {
        transactionTemplate.executeWithoutResult(transactionStatus -> consumer.accept(entityManager));
    }

    @Test
    public void testRepository() {
        Recipe recipe1 = new Recipe();
        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        Ingredient ingredient3 = new Ingredient();

        Recipe recipe2 = new Recipe();
        Ingredient ingredient4 = new Ingredient();
        Ingredient ingredient5 = new Ingredient();
        Ingredient ingredient6 = new Ingredient();

        doInTransaction(em -> {
            recipe1.setTitle("Салат греческий");
            em.persist(recipe1);

            ingredient1.setProduct("Оливки");
            ingredient1.setAmount("50 гр");
            ingredient1.setRecipe(recipe1);
            entityManager.persist(ingredient1);

            ingredient2.setProduct("Сыр");
            ingredient2.setAmount("100 гр");
            ingredient2.setRecipe(recipe1);
            entityManager.persist(ingredient2);

            ingredient3.setProduct("Орурец");
            ingredient3.setAmount("1 шт");
            ingredient3.setRecipe(recipe1);
            entityManager.persist(ingredient3);

            recipe2.setTitle("Салат весенний");
            em.persist(recipe2);

            ingredient4.setProduct("Помидор");
            ingredient4.setAmount("3 шт");
            ingredient4.setRecipe(recipe2);
            entityManager.persist(ingredient4);

            ingredient5.setProduct("Редис");
            ingredient5.setAmount("5 шт");
            ingredient5.setRecipe(recipe2);
            entityManager.persist(ingredient5);

            ingredient6.setProduct("Орурец");
            ingredient6.setAmount("2 шт");
            ingredient6.setRecipe(recipe2);
            entityManager.persist(ingredient6);

        });

        System.out.println("recipe1:");
        System.out.println(recipe1.toString());
        System.out.println("recipe2:");
        System.out.println(recipe2.toString());

        doInTransaction(em -> {
            Iterable<Recipe> repositoryAll = recipeRepository.findAll();
            Recipe recipe = entityManager.find(Recipe.class, recipe1.getId());
            System.out.println("recipe:");
            System.out.println(recipe.toString());
            assertThat(repositoryAll).contains(recipe);
        });

        doInTransaction(em -> {
            List<Recipe> salads = recipeRepository.findByTitleLikeOrderByTitle("Салат%");
            Recipe saladFind = entityManager.find(Recipe.class, recipe2.getId());
            System.out.println("salads:");
            salads.forEach(salad -> System.out.println(salad.toString()));
            Assertions.assertEquals(saladFind, salads.get(0));
        });
    }
}
