package com.demo.repository;

import com.demo.model.Recipe;
import java.util.List;
import java.util.Optional;

public interface RecipeRepository {
  Optional<Recipe> findById(String id);

  List<Recipe> findAll();

  List<Recipe> findByNameAndIncludingIngredientsAndExcludingIngredients(String name,
      List<String> includeIngredients, List<String> excludeIngredients);

}
