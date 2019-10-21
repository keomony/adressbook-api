package addressbook.controllers;

import addressbook.models.Customer;
import addressbook.repositories.CustomerRepository;
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

    @Autowired
    public CustomerRepository customerRepository;

    public Integer TestID = 1;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        restTemplate = new TestRestTemplate();
        customerRepository.deleteAll();
    }

    @Test
    public void whenAddACustomerThenReturnNewlyAddedCustomer() {
        Customer customer = makeDefaultTestCustomer();

        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(getRootUrl() + "/customers", customer, Customer.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());

        final Customer savedCustomer = customerRepository.findAll().get(0);
        Assert.assertEquals(savedCustomer.getSurname(), customer.getSurname());
        Assert.assertEquals(savedCustomer.getFirstName(), customer.getFirstName());
        Assert.assertEquals(savedCustomer.getPostcode(), customer.getPostcode());
    }

    @Test
    public void whenSelectACustomerThenGetTheCustomerDetail() {
        Customer expectedCustomer = makeDefaultTestCustomer();
        expectedCustomer = customerRepository.save(expectedCustomer);

        Customer actualCustomer = restTemplate.getForObject(getRootUrl() + "/customers/" + expectedCustomer.getId().toString(), Customer.class);

        Assert.assertEquals(expectedCustomer.getSurname(), actualCustomer.getSurname());
        Assert.assertEquals(expectedCustomer.getFirstName(), actualCustomer.getFirstName());
        Assert.assertEquals(expectedCustomer.getPostcode(), actualCustomer.getPostcode());
        Assert.assertNotNull(actualCustomer);
    }

    @Test
    public void whenRequestAllCustomersThenReturnCustomerList() {
        customerRepository.save(makeTestCustomer("a", "b", "c"));
        customerRepository.save(makeTestCustomer("d", "e", "f"));
        customerRepository.save(makeTestCustomer("g", "h", "i"));

        final Customer[] forObject = restTemplate.getForObject(getRootUrl() + "/customers", Customer[].class);

        Assert.assertEquals(forObject.length, 3);
    }

    @Test
    public void whenRequestForSpecificSurnameThenFilter() {
        customerRepository.save(makeTestCustomer("a1", "b1", "c1"));
        customerRepository.save(makeTestCustomer("a2", "b2", "c2"));
        customerRepository.save(makeTestCustomer("d", "e", "f"));
        customerRepository.save(makeTestCustomer("g", "h", "i"));

        final Customer[] forObject = restTemplate.getForObject(getRootUrl() + "/customers?surname=a", Customer[].class);

        Assert.assertEquals(forObject.length, 2);
    }

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    private Customer makeDefaultTestCustomer() {
        return makeTestCustomer("Keo", "Mina", "JK10 4PG");
    }

    private Customer makeTestCustomer(String surname, String firstName, String postcode) {
        Customer customer = new Customer();
        customer.setId(TestID);
        customer.setSurname(surname);
        customer.setFirstName(firstName);
        customer.setPostcode(postcode);
        return customer;
    }
}
