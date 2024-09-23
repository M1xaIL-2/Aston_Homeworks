import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class postmanEchoTests {

    public static class Car {
        private String name;

        public Car(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Truck {
        private String name;

        public Truck(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Test
    public void testGetMethod() {
        String foo1Value = "bar1";
        String foo2Value = "bar2";

        RequestSpecification requestSpec = given()
                .baseUri("https://postman-echo.com")
                .queryParam("foo1", foo1Value)
                .queryParam("foo2", foo2Value);

        Response response = requestSpec.when()
                .get("/get")
                .then()
                .statusCode(200)
                .extract().response();

        response.then().body("args.foo1", equalTo(foo1Value));
        response.then().body("args.foo2", equalTo(foo2Value));
        response.then().body("url", equalTo("https://postman-echo.com/get" + "?foo1=" + foo1Value + "&foo2=" + foo2Value));
    }

    @Test
    public void carCreate_200() {
        Car car = new Car("Mercedez");

        ValidatableResponse carRequest = given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("Car:" + car.getName())
                .when()
                .post("/post")
                .then().log().body()
                .statusCode(200);
    }

    @Test
    public void testPostMethod() {

        String url = "https://postman-echo.com/post";
        String jsonValue = null;

        RequestSpecification requestPost = given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json");

        Response response = requestPost.given()
                .param("Car", "1")
                .param("Car", "2")
                .when()
                .post("/post");
        //после выполнения запроса передачи данных в data содержится "Car=1&Car=2",

        assertEquals(response.getStatusCode(), 200);
        response.then().body("data", equalTo("Car=1&Car=2"));
        response.then().body("json", equalTo(jsonValue));
        response.then().body("url", equalTo(url));
    }

    @Test
    public void testPutMethod() {
        String url = "https://postman-echo.com/put";
        String jsonValue = null;

        carCreate_200();

        Car car2 = new Car("Volvo");

        ValidatableResponse requestPut = given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("Car:" + car2.getName())
                .when()
                .put("/put")
                .then().log().body()
                .statusCode(200);
        //после выполнения запроса передачи данных в data содержится "Car:Volvo",

        assertEquals(200, requestPut.extract().statusCode());
        assertEquals("Car:Volvo", requestPut.extract().body().jsonPath().getString("data"));
        assertEquals(jsonValue, requestPut.extract().body().jsonPath().getString("json"));
        assertEquals(url, requestPut.extract().body().jsonPath().getString("url"));
    }

    @Test
    public void testPatchMethod() {
        String url = "https://postman-echo.com/patch";
        String jsonValue = null;

        Car car = new Car("Volvo");
        Truck truck = new Truck("BelAZ");


        ValidatableResponse truCarRequest = given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .param("Car", car.getName())
                .param("Truck", truck.getName())
                .when()
                .post("/post")
                .then().log().body()
                .statusCode(200);


        ValidatableResponse requestPatch = given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("Car=Volvo&Truck=GAZ")
                .when()
                .patch("/patch")
                .then().log().body()
                .statusCode(200);

        assertEquals(200, requestPatch.extract().statusCode());
        assertEquals("Car=Volvo&Truck=GAZ", requestPatch.extract().body().jsonPath().getString("data"));
        assertEquals(jsonValue, requestPatch.extract().body().jsonPath().getString("json"));
        assertEquals(url, requestPatch.extract().body().jsonPath().getString("url"));
    }

    @Test
    public void testDeleteMethod() {
        String url = "https://postman-echo.com/delete";
        String jsonValue = null;

        Car car = new Car("Volvo");
        Truck truck = new Truck("BelAZ");


        ValidatableResponse truCarRequest = given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .param("Car", car.getName())
                .param("Truck", truck.getName())
                .when()
                .post("/post")
                .then().log().body()
                .statusCode(200);

        ValidatableResponse requestDelete = given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body("Car=Volvo")
                .when()
                .delete("/delete")
                .then().log().body()
                .statusCode(200);

        assertEquals(200, requestDelete.extract().statusCode());
        assertEquals("Car=Volvo", requestDelete.extract().body().jsonPath().getString("data"));
        assertEquals(jsonValue, requestDelete.extract().body().jsonPath().getString("json"));
        assertEquals(url, requestDelete.extract().body().jsonPath().getString("url"));
    }
}
