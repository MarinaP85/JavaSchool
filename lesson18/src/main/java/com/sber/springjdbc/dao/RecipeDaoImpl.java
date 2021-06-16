package com.sber.springjdbc.dao;

import com.sber.springjdbc.exception.TableNotExistsException;
import com.sber.springjdbc.model.Ingredient;
import com.sber.springjdbc.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

public class RecipeDaoImpl implements RecipeDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsertOperations insertOperationsRecipe;
    private final SimpleJdbcInsertOperations insertOperationsIngredient;
    private final NamedParameterJdbcOperations parameterJdbcOperations;
    private final RowMapper<Recipe> recipeRowMapper;
    private final RowMapper<Ingredient> ingredientRowMapper;

    public RecipeDaoImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.parameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
        this.insertOperationsRecipe = new SimpleJdbcInsert(dataSource)
                .withTableName("Recipes")
                .usingColumns("title")
                .usingGeneratedKeyColumns("id");

        this.insertOperationsIngredient = new SimpleJdbcInsert(dataSource)
                .withTableName("Ingredients")
                .usingColumns("product", "amount", "recipeID")
                .usingGeneratedKeyColumns("id");

        this.recipeRowMapper = (resultSet, i) -> {
            Recipe recipe = new Recipe();
            recipe.setId(resultSet.getInt(1));
            recipe.setTitle(resultSet.getString(2));
            return recipe;
        };

        this.ingredientRowMapper = (resultSet, i) -> {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(resultSet.getInt(1));
            ingredient.setProduct(resultSet.getString(2));
            ingredient.setAmount(resultSet.getString(3));
            ingredient.setRecipeId(resultSet.getInt(4));
            return ingredient;
        };

        jdbcTemplate.setExceptionTranslator(new TableNotExistsException());
    }

    @Override
    @Transactional()
    public Recipe createRecipe(Recipe recipe) {
        Number returnKey = insertOperationsRecipe.executeAndReturnKey(new BeanPropertySqlParameterSource(recipe));
        recipe.setId((Integer) returnKey);

        recipe.getIngredients().forEach((ingredient) -> {
            ingredient.setRecipeId((Integer) returnKey);
            Number ingredientKey = insertOperationsIngredient.executeAndReturnKey(
                    new BeanPropertySqlParameterSource(ingredient));
            ingredient.setId((Integer) ingredientKey);
        });
        return recipe;
    }

    @Override
    public List<Recipe> searchRecipe(String title) {
        List<Recipe> recipes = parameterJdbcOperations.query("select id, title from Recipes where title like :title order by id",
                new MapSqlParameterSource("title", "%" + title + "%"), this.recipeRowMapper);
        if (!recipes.isEmpty()) {
            recipes.forEach(recipe -> {
                List<Ingredient> ingredients = parameterJdbcOperations.query("select id, product, amount, recipeID from Ingredients where recipeID = :id",
                        new MapSqlParameterSource("id", recipe.getId()), this.ingredientRowMapper);
                recipe.setIngredients(ingredients);
            });
        }
        return recipes;
    }

    @Override
    @Transactional()
    public void deleteRecipe(int id) {
        jdbcTemplate.update("delete from Recipes where id = ?", id);
        jdbcTemplate.update("delete from Ingredients where recipeID = ?", id);
    }
}
