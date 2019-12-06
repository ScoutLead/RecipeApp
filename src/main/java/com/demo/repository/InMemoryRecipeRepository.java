package com.demo.repository;

import com.demo.model.Ingredient;
import com.demo.model.Recipe;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class InMemoryRecipeRepository implements RecipeRepository {

  private List<Recipe> recipes;

  public InMemoryRecipeRepository(@ConfigProperty(name = "recipes.file.name") String fileName) {
    try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.registerModule(new JavaTimeModule());
      recipes = objectMapper.readValue(input, new TypeReference<List<Recipe>>() {
      });
    } catch (IOException e) {
      e.printStackTrace();
      recipes = new ArrayList<>();
    }
  }


  @Override
  public Optional<Recipe> findById(String id) {
    return recipes.stream()
        .filter(r -> r.getId().equals(id))
        .findFirst();
  }

  @Override
  public List<Recipe> findAll() {
    return Collections.unmodifiableList(recipes);
  }

  @Override
  public List<Recipe> findByNameAndIncludingIngredientsAndExcludingIngredients(String name,
      List<String> includeIngredients, List<String> excludeIngredients) {

    return recipes.stream().filter(r -> r.getName().startsWith(name) &&
        ingredientCriteria(includeIngredients, excludeIngredients, r.getIngredients()))
        .collect(Collectors.toList());
  }

  private boolean ingredientCriteria(List<String> includeIngredients,
      List<String> excludeIngredients, List<Ingredient> ingredients) {
    return includeIngredients.stream()
        .allMatch(inc -> ingredients.stream().anyMatch(ingredient ->
            inc.equals(ingredient.getProduct().getName()))) &&
        excludeIngredients.stream().allMatch(exc ->
            ingredients.stream().noneMatch(ingr ->
                exc.equals(ingr.getProduct().getName())));
  }
}
