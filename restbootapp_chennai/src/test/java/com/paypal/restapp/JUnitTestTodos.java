package com.paypal.restapp;

import org.hamcrest.Matcher;
import org.junit.BeforeClass;

import org.junit.Test;

import com.paypal.restapp.resources.Todo;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.sql.Date;
import java.util.List;



public class JUnitTestTodos {
	@BeforeClass

    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 7070;
    }

	@Test
	public void testGetAllTodos() {
		get("/paypal/restapp/todos")
		.then()
		.assertThat()
		.body("$", hasSize(greaterThan(0)));
	}
	@Test
	public void testRetrieveTodo() {
		get("/paypal/restapp/todos/{id}",1)
		.then()
		.assertThat()
		.body("id", equalTo(1));
		
	}
	@Test
	public void testDeleteTodo() {
		 given().pathParam("id", 4)
	        .when().delete("/paypal/restapp/todos/{id}")
	        .then().statusCode(200);
		
	}
	
	}
