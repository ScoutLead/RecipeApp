package com.demo.service;

import com.demo.model.Ingredient;
import com.demo.model.Product;
import com.demo.model.Recipe;

import com.demo.repository.RecipeRepository;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RecipeServiceImpl implements RecipeService {

  private final RecipeRepository recipeRepository;

  @Inject
  public RecipeServiceImpl(RecipeRepository recipeRepository) {
    this.recipeRepository = recipeRepository;
  }

  public Optional<Recipe> findRecipeById(String id) {
    return recipeRepository.findById(id);
  }

  @Override
  public List<Recipe> findAll() {
    return recipeRepository.findAll();
  }

  @Override
  public List<Recipe> findRecipesByNameWithIngredients(String name,
      List<String> includeIngredients, List<String> excludeIngredients) {
    if(includeIngredients.isEmpty() && excludeIngredients.isEmpty()) {
      return recipeRepository.findByName(name);
    } else {
      return recipeRepository
          .findByNameAndIncludingIngredientsAndExcludingIngredients(name, includeIngredients, excludeIngredients);
    }
  }
}
