package addressbook;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    private WebTestClient webClient;

    private String json = "{\"surname\":\"Chea\",\"firstName\":\"Makera\",\"postcode\":\"JK21 4PG\"}";

    @Test
    public void contextLoads() {
    }

    @Before
    public void setUp() {

        webClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }

    @Test
    public void shouldBeAbleToAddANewCustomer() {

        webClient.post().uri("/customers")
                .bodyValue(json)
                .exchange().expectStatus().isOk();

    }

    @Test
    public void shouldBeAbleToGetACustomerDetailById() throws JSONException {

        final WebTestClient.ResponseSpec response = webClient.post().uri("/customers")
                .bodyValue(json)
                .exchange();
        response.expectStatus().isOk();

        String id = extractIdFromResponse(response);

        final WebTestClient.ResponseSpec request = webClient.get().uri("/customers/" + id)
                .exchange();
        request
                .expectStatus().isOk()
                .expectBody()
                .json(json);

    }

    @Test
    public void shouldBeAbleToReturnAllCustomers(){
        webClient.get().uri("/customers")
                .exchange()
                .expectStatus().isOk();
    }

    private String extractIdFromResponse(WebTestClient.ResponseSpec response) throws JSONException {
        final byte[] responseBody = response.expectBody().returnResult().getResponseBodyContent();
        final JSONObject responseBodyJson = new JSONObject(new String(responseBody));
        return responseBodyJson
                .get("id").toString();
    }

}
