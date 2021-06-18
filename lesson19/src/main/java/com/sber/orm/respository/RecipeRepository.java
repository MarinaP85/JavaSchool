package com.sber.orm.respository;

import com.sber.orm.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findByTitleLikeOrderByTitle(String title);
}
