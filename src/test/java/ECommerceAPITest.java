import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojoClass.LogInResponse;
import pojoClass.Login;

import static io.restassured.RestAssured.*;

import java.io.File;

public class ECommerceAPITest {

	public static void main(String[] args) {
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
		
		
		Login login= new Login();
		login.setUserEmail("postmanLupin@gmail.com");
		login.setUserPassword("Test@1234");
		
		RequestSpecification reqLogin=given().log().all().spec(req).body(login);
		LogInResponse loginresponse=reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract().response()
				.as(LogInResponse.class);
		System.out.println(loginresponse.getToken());
		String token=loginresponse.getToken();
		System.out.println(loginresponse.getUserId());
		String userId=loginresponse.getUserId();
		
		//Add Product
		RequestSpecification addproductBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("Authorization", token)
				.build();
		
		RequestSpecification addingproduct= given().log().all().spec(addproductBaseReq).param("productName", "Laptop").
		param("productAddedBy", userId)
		.param("productCategory", "Electronic")
		.param("productSubCategory", "Devices")
		.param("productPrice", "61000")
		.param("productDescription", "HP Pavilion intel Core i5")
		.param("productFor", "Any")
		.multiPart("productImage", new File("C:\\Users\\dibya\\OneDrive\\Desktop\\Laptop Pic.png"));
		
		String addproductresponse=addingproduct.when().post("/api/ecom/product/add-product")
		.then().log().all().extract().response().asString();
		
		JsonPath js= new JsonPath(addproductresponse);
		String productId=js.get("productId");
		
		
		
		
		
	}

}
