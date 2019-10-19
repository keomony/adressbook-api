package addressbook.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Customer {

    private @Id @GeneratedValue Integer id;
    private String surname;
    private String firstName;
    private String postcode;

    public Customer(){}

    @JsonCreator
    public Customer(@JsonProperty("surname") String surname, @JsonProperty("firstName") String firstName, @JsonProperty("postcode") String postcode) {
        this.surname = surname;
        this.firstName = firstName;
        this.postcode = postcode;
    }

    public Integer getId(){
        return id;
    }

    public String getSurname(){
        return surname;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getPostcode(){
        return postcode;
    }

}
