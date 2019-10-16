package addressbook.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CustomerTest {

    @Test
    public void shouldReturnCorrectInfoWhenDeserializeUsingJsonCreator()
            throws IOException {

        String json = "{\"id\":\"1\",\"surname\":\"Chea\",\"firstName\":\"Makera\",\"postcode\":\"JK21 4PG\"}";

        Customer customer = new ObjectMapper()
                .readerFor(Customer.class)
                .readValue(json);
        Assert.assertEquals("1", customer.getId());
        Assert.assertEquals("Chea", customer.getSurname());
        Assert.assertEquals("Makera", customer.getFirstName());
        Assert.assertEquals("JK21 4PG", customer.getPostcode());
    }
}
