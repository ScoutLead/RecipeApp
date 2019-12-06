package com.demo.controller;

import com.demo.exception.RecipeNotFoundException;
import com.demo.model.Recipe;
import com.demo.service.RecipeService;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/recipes")
public class RecipeController {

  private final RecipeService recipeService;

  @Inject
  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Recipe findRecipeById(@PathParam("id") String id) {
    return recipeService.findRecipeById(id)
        .orElseThrow(RecipeNotFoundException::new);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Recipe> findRecipesByIngredient(@QueryParam("name") @DefaultValue("") String name,
       @QueryParam("includeIngredient") List<String> includeIngredients,
      @QueryParam("excludeIngredient") List<String> excludeIngredients) {
    if(Objects.nonNull(name) && Objects.nonNull(includeIngredients) && Objects.nonNull(excludeIngredients)) {
      return recipeService.findRecipesByNameWithIngredients(name, includeIngredients, excludeIngredients);
    } else {
      return recipeService.findAll();
    }
  }
}
