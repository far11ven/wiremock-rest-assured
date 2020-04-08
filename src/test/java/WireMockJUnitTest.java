import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

class WireMockJUnitTest {

    WireMockServer wireMockServer;

    @BeforeEach
    public void setup () {
        System.out.println("Before each>>>>");
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
        setupStub();
    }

    @AfterEach
    public void teardown () {
        System.out.println("After each>>>>");
        wireMockServer.stop();
    }

    public void setupStub() {
        System.out.println("Seting up stub>>>>");
        wireMockServer.stubFor(get(urlEqualTo("/an/endpoint"))
                .willReturn(aResponse().withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBodyFile("glossary.json")));
    }

    @Test
    public void testStatusCodePositive() {
        System.out.println("Test 1 >>>>");
        given().
                when().
                get("http://localhost:8090/an/endpoint").
                then().
                assertThat().statusCode(200);
    }

    @Test
    public void testStatusCodeNegative() {
        System.out.println("Test 2 >>>>");
        given().
                when().
                get("http://localhost:8090/another/endpoint").
                then().
                assertThat().statusCode(404);
    }

    @Test
    public void testResponseContents() {
        System.out.println("Test 3 >>>>");
        Response response =  given().when().get("http://localhost:8090/an/endpoint");

        System.out.println("Response >>>>" + response.asString());
        String title = response.jsonPath().get("glossary.title");
        System.out.println("Title >>>>" + title);
        Assert.assertEquals("example glossary", title);
    }
}