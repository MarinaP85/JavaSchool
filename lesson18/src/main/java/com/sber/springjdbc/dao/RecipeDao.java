package com.sber.springjdbc.dao;

import com.sber.springjdbc.model.Recipe;

import java.util.List;

public interface RecipeDao {

    Recipe createRecipe(Recipe recipe);

    List<Recipe> searchRecipe(String title);

    void deleteRecipe(int id);
}
