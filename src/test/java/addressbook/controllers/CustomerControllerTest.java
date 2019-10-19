package addressbook.controllers;

import addressbook.models.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class CustomerControllerTest {
    public Integer ONE = 1;
    public TestRestTemplate restTemplate;
    public  String getRootUrl;

    @Before
    public void setUp(){
        restTemplate = new TestRestTemplate();
        getRootUrl = "http://localhost:8080/";
    }

    @Test
    public void whenAddACustomerThenReturnNewlyAddedCustomer() {
        Customer customer = new Customer();
        customer.setId(ONE);
        customer.setFirstName("Jenny");
        customer.setSurname("Keo");
        customer.setPostcode("BA87 2DP");
        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(getRootUrl + "/customers", customer, Customer.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Test
    public void whenSelectACustomerThenGetTheCustomerDetail() {

        Customer customer = restTemplate.getForObject(getRootUrl + "/customers/1", Customer.class);

        Assert.assertEquals(ONE, customer.getId());
        Assert.assertEquals("Chea", customer.getSurname());
        Assert.assertEquals("Makera", customer.getFirstName());
        Assert.assertEquals("JK21 4PG", customer.getPostcode());
        Assert.assertNotNull(customer);
    }
}
