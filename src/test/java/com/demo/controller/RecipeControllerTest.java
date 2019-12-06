package com.demo.controller;

import io.quarkus.test.junit.QuarkusTest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class RecipeControllerTest {

    @Test
    public void testFindRecipeByIdEndpoint() throws IOException, URISyntaxException {
        String expected = getStringFromFile("find_recipe_by_id/varenyky-recipe.json");

        given()
          .when().get("/api/v1/recipes/id1")
          .then()
             .statusCode(200)
             .body(is(expected));
    }

    @Test
    public void testFindRecipeByIdEndpoint_WhenIdIsNotFound() {
        given()
            .when().get("/api/v1/recipes/id0")
            .then()
            .statusCode(404);
    }

    @ParameterizedTest
    @MethodSource("provideStringsForFindRecipeByNameAndIngredients")
    public void testFindRecipeByNameAndIngredientsEndpoint(String params, String expected)  {
        given()
            .when().get("/api/v1/recipes/?" + params)
            .then()
            .statusCode(200)
            .body(is(expected));
    }

    private static Stream<Arguments> provideStringsForFindRecipeByNameAndIngredients()
        throws IOException, URISyntaxException {
        return Stream.of(
            Arguments.of("name=varenyky",
                getStringFromFile("find_recipe_by_name_and_ingredients/varenyky-recipes.json")),
            Arguments.of("includeIngredient=flour&includeIngredient=cherry",
                getStringFromFile("find_recipe_by_name_and_ingredients/recipes-flour-cherry.json")),
            Arguments.of("includeIngredient=flour&includeIngredient=potato&excludeIngredient=mushroom",
                getStringFromFile("find_recipe_by_name_and_ingredients/recipes-flour-potato-exclude-mushroom.json")));
    }

    private static String getStringFromFile(String fileName) throws URISyntaxException, IOException {
        return new String(Files.readAllBytes(Paths
            .get(RecipeControllerTest.class.getClassLoader().getResource(fileName).toURI())),
            StandardCharsets.UTF_8);
    }

}