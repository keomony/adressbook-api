package addressbook;

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

    private String json = "{\"id\":\"1\",\"surname\":\"Chea\",\"firstName\":\"Makera\",\"postcode\":\"JK21 4PG\"}";

    @Test
    public void contextLoads() {
    }

    @Before
    public void setUp(){

        webClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }

    @Test
    public void shouldBeAbleToAddANewCustomer() {

        webClient.post().uri("/customers/")
                .syncBody(json)
                .exchange().expectStatus().isOk();

    }

}
