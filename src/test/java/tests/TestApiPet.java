package tests;

import endpoints.EndPointPet;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Category;
import model.Pet;
import model.TagPet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utilsAPI.PetApiSpecification;
import utilsAPI.PetStatus;

import java.util.ArrayList;
import java.util.Collections;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("API tests for PET")
public class TestApiPet {

    private static RequestSpecification requestSpec = PetApiSpecification.getRequestSpecification();

    @Test
    @Order(1)
    public void testGet() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(EndPointPet.GET_STATUS + "?status=" + PetStatus.AVAILABLE);

        response.prettyPrint();
    }

    @Test
    @Order(2)
    public void testPost(){
        TagPet tag = new TagPet(2020327, "Mestizo cat");
        Category category = new Category(202, "Cat");
        Pet pet = new Pet(0327, category, "Leopold", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tag)), PetStatus.AVAILABLE);

        given()
                .spec(requestSpec)
                .when()
                .body(pet)
                .post(EndPointPet.POST_STATUS + PetStatus.AVAILABLE)
                .then()
                .assertThat();

    }

    @Test
    @Order(3)
    public void testPut(){
        TagPet tag = new TagPet(2020327, "Persid cat");
        Category category = new Category(202, "Cat");
        Pet pet = new Pet(0327, category, "Leopold", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tag)), PetStatus.AVAILABLE);

        given()
                .spec(requestSpec)
                .when()
                .body(pet)
                .put(EndPointPet.PUT_STATUS + PetStatus.AVAILABLE)
                .then()
                .assertThat();

    }

    @Test
    @Order(4)
    public void testDelete(){
        TagPet tag = new TagPet(2020327, "Persid cat");
        Category category = new Category(202, "Cat");
        Pet pet = new Pet(0327, category, "Leopold", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tag)), PetStatus.AVAILABLE);

        given()
                .spec(requestSpec)
                .when()
                .body(pet)
                .post(EndPointPet.DELETE_STATUS + PetStatus.AVAILABLE)
                .then()
                .assertThat();

    }
}
