package com.demo.model;

import java.time.Duration;
import java.util.List;

public class Recipe {
  private String id;

  private String name;

  private Duration preparationDuration;

  private Duration cookDuration;

  private List<Ingredient> ingredients;

  private List<Direction> directions;

  public Recipe() {
  }

  public Recipe(String id, String name, Duration preparationDuration, Duration cookDuration,
      List<Ingredient> ingredients, List<Direction> directions) {
    this.id = id;
    this.name = name;
    this.preparationDuration = preparationDuration;
    this.cookDuration = cookDuration;
    this.ingredients = ingredients;
    this.directions = directions;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Duration getPreparationDuration() {
    return preparationDuration;
  }

  public void setPreparationDuration(Duration preparationDuration) {
    this.preparationDuration = preparationDuration;
  }

  public Duration getCookDuration() {
    return cookDuration;
  }

  public void setCookDuration(Duration cookDuration) {
    this.cookDuration = cookDuration;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public List<Direction> getDirections() {
    return directions;
  }

  public void setDirections(List<Direction> directions) {
    this.directions = directions;
  }


}
