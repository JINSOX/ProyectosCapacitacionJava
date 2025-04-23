package com.example;

import Entities.Usuario;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
class UserResourceTest {
    /*
    @Test
    void testCreateUser_ValidData() {
        // Usuario con email válido
        Usuario usuario = new Usuario();
        usuario.setName("John Doe");
        usuario.setEmail("john.doe@example.com");
        usuario.setSex("male");
        usuario.setAvatar("https://example.com/avatar.jpg");
        usuario.setCardNumber("1234567890123456");
        usuario.setCardCvv("123");

        given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when().post("/users")
                .then()
                .statusCode(201)
                .body("name", is("John Doe"))
                .body("email", is("john.doe@example.com"));
    }

    @Test
    void testCreateUser_InvalidEmail() {
        // Usuario con email nulo o vacío
        Usuario usuario = new Usuario();
        usuario.setName("User Without Email");
        usuario.setEmail("");  // Email vacío
        usuario.setSex("male");
        usuario.setAvatar("https://example.com/avatar.jpg");
        usuario.setCardNumber("1234567890123456");
        usuario.setCardCvv("123");

        given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when().post("/users")
                .then()
                .statusCode(500) // Aquí verificamos que se devuelve un error de servidor
                .body(containsString("Error en el servidor al crear el usuario"));
    }

    @Test
    void testGetUserById_ValidData() {
        long userId = 3002;

        given()
                .when().get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("id", is(userId));
    }

    @Test
    void testGetUserById_InvalidEmail() {
        long userId = 2001L;

        given()
                .when().get("/users/" + userId)
                .then()
                .statusCode(202) // Aquí verificamos que se devuelve 202 si el email es nulo
                .body("id", is(userId))
                .body("email", is(""));
    }

    @Test
    void testUpdateUser_ValidData() {
        long userId = 3001L;
        Usuario usuario = new Usuario();
        usuario.setName("Updated John Doe");
        usuario.setEmail("updated.john.doe@example.com");
        usuario.setSex("male");
        usuario.setAvatar("https://example.com/avatar-updated.jpg");
        usuario.setCardNumber("8765432187654321");
        usuario.setCardCvv("123");

        given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when().put("/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", is("Updated John Doe"))
                .body("email", is("updated.john.doe@example.com"));
    }

    @Test
    void testUpdateUser_InvalidEmail() {
        long userId = 3001L;
        Usuario usuario = new Usuario();
        usuario.setName("Updated John Doe");
        usuario.setEmail("");  // Email vacío
        usuario.setSex("male");
        usuario.setAvatar("https://example.com/avatar-updated.jpg");
        usuario.setCardNumber("8765432187654321");
        usuario.setCardCvv("123");

        given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when().put("/users/" + userId)
                .then()
                .statusCode(200)
                .body("email", is(""));  // Verificamos que el email es vacío
    }
    */
}