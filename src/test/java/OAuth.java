
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath; 
public class OAuth {

	public static void main(String[] args) {
		
		//Post Method
		String responce = given().
		formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
		formParam("grant_type", "client_credentials").
		formParam("scope", "trust").
		when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		System.out.println(responce);
		
		JsonPath jsonPath= new JsonPath(responce);
		String access_token= jsonPath.getString("access_token");
		
		//Get Method
		
		String responce2=given().queryParam("access_token", access_token).
		when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
		System.out.println(responce2);
		
	
	}

}
