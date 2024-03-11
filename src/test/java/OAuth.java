
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import pojoClass.APIAutomation;
import pojoClass.WebAutomation;
import pojoClass.getCourse; 
public class OAuth {

	public static void main(String[] args) {
		
		
		String[] expcourseTitle= {"Selenium Webdriver Java","Cypress","Protractor"};
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
		
		getCourse gc=given().queryParam("access_token", access_token).
		when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(getCourse.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		List<APIAutomation> apiCourses=gc.getCourses().getApi();
		for(int i=0;i<apiCourses.size();i++)

		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
					{
				 		System.out.println(apiCourses.get(i).getPrice());
					}
		}
		//Get the course name of webAutomation
		ArrayList<String> a= new ArrayList<String>();
		List<WebAutomation> wa=gc.getCourses().getWebAutomation();
		for(int j=0;j<wa.size();j++)
		{
			a.add(wa.get(j).getCourseTitle());
		}
		List<String> expectedList= Arrays.asList(expcourseTitle);
		Assert.assertTrue(a.equals(expectedList));
	}

}
