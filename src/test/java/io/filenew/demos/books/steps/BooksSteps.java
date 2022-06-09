package io.filenew.demos.books.steps;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class BooksSteps {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;

	private final String GET_BOOK_VOLUME_ENDPOINT = "https://www.googleapis.com/books/v1/volumes";

	private boolean singleVolume = false;

	@Given("^a book with (?:isbn|ISBN) (.*)$")
	public void book_exists_with_isbn(String isbn){
		request = rest().given().param("q", "isbn:" + isbn);
	}

	@Given("^a book with volume ID (.*)$")
	public void book_exists_with_volume_id(String volumeId){
		request = rest().given().pathParam("volumeId", volumeId);
	}

	@When("^.* search for the book$")
	public void search_for_book(){
		response = request.when().get(GET_BOOK_VOLUME_ENDPOINT);
		json = response.then().statusCode(200);
	}

	@When("^.* retrieve the book$")
	public void get_book_by_volume_id(){
		singleVolume = true;
		response = request.when().get(GET_BOOK_VOLUME_ENDPOINT + "/{volumeId}");
		json = response.then().statusCode(200);
	}
	
	@Then("^the book title should be \"(.*)\"$")
	public void verify_title(String title) {
		verify_property("volumeInfo.title", title);
	}

	@Then("^the book subtitle should be \"(.*)\"$")
	public void verify_subtitle(String subtitle) {
		verify_property("volumeInfo.subtitle", subtitle);
	}
	
	@Then("^the response includes the following$")
	public void response_contains(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), equalTo(field.getValue()));
			}
		}
	}

	@Then("the response includes the following in any order")
	public void response_contains_in_any_order(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), containsInAnyOrder(field.getValue()));
			}
		}
	}

	public void verify_property(String name, String value) {
		if (singleVolume) {
			json.body( name, equalTo(value));
		} else {
			json.body( "items." + name, equalTo(Collections.singletonList(value)));
		}
	}
}
