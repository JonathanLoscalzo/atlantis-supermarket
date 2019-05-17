package com.atlantis.supermarket.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.atlantis.supermarket.core.user.User;
import com.atlantis.supermarket.core.user.generator.SaveUser;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SupermarketIntegrationTests {
    
    @Before
    public void setup() {
	RestAssured.baseURI = "http://localhost";
	RestAssured.port = this.port;
    }

    @LocalServerPort
    private int port;
    
    

}
