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

    private @Id
    @GeneratedValue
    Integer id;
    private String surname;
    private String firstName;
    private String postcode;

    public Customer() {
    }

    @JsonCreator
    public Customer(@JsonProperty("surname") String surname, @JsonProperty("firstName") String firstName, @JsonProperty("postcode") String postcode) {
        this.surname = surname;
        this.firstName = firstName;
        this.postcode = postcode;
    }

    public Integer setId(Integer id) {
        return this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String setSurname(String surname) {
        return this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public String setFirstName(String firstName) {
        return this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String setPostcode(String postcode) {
        return this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

}
