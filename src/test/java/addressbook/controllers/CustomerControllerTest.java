package addressbook.controllers;

import addressbook.models.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @Autowired
    public TestRestTemplate restTemplate;

    public Integer ONE = 1;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        restTemplate = new TestRestTemplate();
    }

    @Test
    public void whenAddACustomerThenReturnNewlyAddedCustomer() {
        Customer customer = new Customer();
        customer.setId(ONE);
        customer.setSurname("Keo");
        customer.setFirstName("Mina");
        customer.setPostcode("JK10 4PG");
        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(getRootUrl() + "/customers", customer, Customer.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void whenSelectACustomerThenGetTheCustomerDetail() {

        Customer customer = restTemplate.getForObject(getRootUrl() + "/customers/" + ONE, Customer.class);

        Assert.assertEquals(ONE, customer.getId());
        Assert.assertEquals("Keo", customer.getSurname());
        Assert.assertEquals("Mina", customer.getFirstName());
        Assert.assertEquals("JK10 4PG", customer.getPostcode());
        Assert.assertNotNull(customer);
    }

    private String getRootUrl() {
        return "http://localhost:" + port;
    }
}
