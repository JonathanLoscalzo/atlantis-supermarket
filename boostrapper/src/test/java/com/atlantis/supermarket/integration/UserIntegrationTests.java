package com.atlantis.supermarket.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.solr.client.solrj.SolrClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.atlantis.supermarket.core.user.User;
import com.atlantis.supermarket.core.user.generator.SaveUser;
import com.atlantis.supermarket.infrastructure.external.email.EmailAdapter;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserIntegrationTests extends SupermarketIntegrationTests{

    @MockBean
    SolrClient client;
    @MockBean
    private EmailAdapter emailAdapter;
    
    @Autowired
    private SaveUser userRepository;

    @Test
    public void whenSignUpUserCanLogin() {

	JSONObject jsonObj = null;

	try {
	    jsonObj = new JSONObject().put("username", "pepo").put("password", "password");
	} catch (JSONException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	RestAssured.given().contentType("application/json").body(jsonObj.toString()).when().post("/api/public/sign-up").then()
		.statusCode(200).extract();

	String authorization = RestAssured.given().contentType("application/json").body(jsonObj.toString())
		.post("/api/authenticate").then().statusCode(200).extract().header("Authorization");
	
	assertThat(authorization).isNotEmpty();
    }
    
    @Test
    public void whenSignUpUserReturn409() {
	
	User user = new User();
	user.setUsername("pepo2");
	user.setPassword("asdfgasdf");
	userRepository.save(user);
	
	JSONObject jsonObj = null;

	try {
	    jsonObj = new JSONObject().put("username", "pepo2").put("password", "password");
	} catch (JSONException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	String msg = RestAssured.given().contentType("application/json").body(jsonObj.toString()).when().post("/api/public/sign-up").then()
		.statusCode(409).extract().body().jsonPath().get("message");
	
	assertThat(msg).contains("El usuario ya existe");
    }
}
