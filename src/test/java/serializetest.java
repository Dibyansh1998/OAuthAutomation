import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojoClass.AddPlace;
import pojoClass.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serializetest {

	public static void main(String[] args) {
		
		
		AddPlace a= new AddPlace();
		a.setAccuracy(50);
		a.setAddress("29, side layout, cohen 09");
		a.setLanguage("English");
		a.setPhone_number("+91-6394127923");
		a.setName("Dibyansh Verma");
		a.setWebsite("https://dibyansh_verma.com");
		
		List<String> myList= new ArrayList<String>();
		myList.add("Shoe park");
		myList.add("shop");
		
		Location loc= new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		a.setLocation(loc);
		
		a.setTypes(myList);
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		Response res=given().log().all().queryParam("key", "qaclick123").body(a)
		.when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().response();
		
		String responseString=res.asString();
		System.out.println(responseString);

	}

}
