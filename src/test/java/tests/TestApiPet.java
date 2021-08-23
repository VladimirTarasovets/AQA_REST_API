package tests;

import endpoints.EndPointPet;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Category;
import model.Pet;
import model.TagPet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utilsAPI.PetApiSpecification;
import utilsAPI.PetStatus;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("API tests for PET")
public class TestApiPet {

    private static RequestSpecification requestSpec = PetApiSpecification.getRequestSpecification();
    private static File jsonSchema = new File("src/test/resources/json/petJsonSchema.json");


    @Test
    @DisplayName("Запрос GET")
    public void testGet() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(EndPointPet.GET_STATUS + "?status=" + PetStatus.AVAILABLE);

        response.prettyPrint();
    }

    @Test
    @DisplayName("Запрос POST")
    public void testPost(){
        TagPet tag = new TagPet(1, "Mestizo cat");
        Category category = new Category(2, "Cat");
        Pet pet = new Pet(3, category, "Leopold", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tag)), PetStatus.AVAILABLE);

        given()
                .spec(requestSpec)
                .when()
                .body(pet)
                .post(EndPointPet.POST_STATUS)
                .then()
                .statusCode(200);

//                .then()
//                .assertThat()
//                .body(matchesJsonSchema(jsonSchema));

    }

    @Test
    @DisplayName("Запрос PUT")
    public void testPut(){
        TagPet tag = new TagPet(1, "Persid cat");
        Category category = new Category(2, "Cat");
        Pet pet = new Pet(3, category, "Leopold", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tag)), PetStatus.AVAILABLE);

        given()
                .spec(requestSpec)
                .when()
                .body(pet)
                .put(EndPointPet.PUT_STATUS)
                .then()
                .statusCode(200);

    }

    @Test
    @DisplayName("Запрос DELETE")
    public void testDelete(){
        int petId = 1;

        given()
                .spec(requestSpec)
                .when()
                .body(petId)
                .delete(EndPointPet.DELETE_STATUS)
                .then()
                .statusCode(200);

    }
}
