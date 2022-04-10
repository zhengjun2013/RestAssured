package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

public class APISamples {
	
	@Test
	public void test_1() {
		
		baseURI = "https://reqres.in/api";
		
		given()
			.get("users?page=2")
		.then()
			.statusCode(200)
			.body("data[1].id", equalTo(8));
	}
	
	@Test(priority = 1)
	public void testGet() {
		
		baseURI = "https://reqres.in/api";
		
		given()
			.get("/users?page=2")
		.then()
			.statusCode(200)
			.body("data[4].first_name", equalTo("George"))
			.body("data.first_name", hasItems("George", "Rachel"));
	}
	
	@Test
	public void testPost() {
	  JSONObject req = new JSONObject();
	  req.put("name", "Test");
	  req.put("job", "Tester");
	  
	  System.out.println(req.toJSONString());
	  
	  baseURI = "https://reqres.in/api";
	  
	  given()
	  	.header("Content-Type", "application/json")
	  	.contentType(ContentType.JSON)
	  	.accept(ContentType.JSON)
	  	.body(req.toJSONString())
	  .when()
	  	.post("/users")
	  .then()
	  	.statusCode(201)
	  	.log()
	  	.all();
	}
	
	@Test
	public void testPut() {
		JSONObject req = new JSONObject();
		req.put("name", "Test");
		req.put("Job", "Software Testing");
		
		System.out.println(req.toJSONString());
		
		baseURI = "https://reqres.in/api";
		
		given()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(req.toJSONString())
		.when()
			.put("/users/2")
		.then()
			.statusCode(200)
			.log()
			.all();

	}
	
	@Test
	public void testDelete() {
		
		baseURI = "https://reqres.in/api";
		
		given()
		.when()
			.delete("/users/2")
		.then()
			.statusCode(200)
			.log()
			.all();

	}
	
	
}
