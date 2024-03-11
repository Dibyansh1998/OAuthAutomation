import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClass.AddPlace;
import pojoClass.Location;

public class SpecBuilderTest {

	public static void main(String[] args) {

		AddPlace a = new AddPlace();
		a.setAccuracy(50);
		a.setAddress("29, side layout, cohen 09");
		a.setLanguage("English");
		a.setPhone_number("+91-6394127923");
		a.setName("Dibyansh Verma");
		a.setWebsite("https://dibyansh_verma.com");

		List<String> myList = new ArrayList<String>();
		myList.add("Shoe park");
		myList.add("shop");

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		a.setLocation(loc);

		a.setTypes(myList);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		RequestSpecification res = given().spec(req).body(a);

		Response response = res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();

		String responseString = response.asString();
		System.out.println(responseString);

	}

}
