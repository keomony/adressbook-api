package addressbook.controllers;

import addressbook.models.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class CustomerControllerTest {

    @Test
    public void whenSelectACustomerGetTheCustomerDetail() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        String getRootUrl = "http://localhost:8080/";
        Integer ONE = 1;

        Customer customer = restTemplate.getForObject(getRootUrl + "/customers/1", Customer.class);

        Assert.assertEquals(ONE, customer.getId());
        Assert.assertEquals("Chea", customer.getSurname());
        Assert.assertEquals("Makera", customer.getFirstName());
        Assert.assertEquals("JK21 4PG", customer.getPostcode());
        Assert.assertNotNull(customer);
    }
}
