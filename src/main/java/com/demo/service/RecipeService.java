package com.demo.service;

import com.demo.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

  Optional<Recipe> findRecipeById(String id);

  List<Recipe> findAll();

  List<Recipe> findRecipesByNameWithIngredients(String name,
      List<String> includeIngredients, List<String> excludeIngredients);
}
