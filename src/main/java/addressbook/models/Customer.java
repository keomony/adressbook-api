package addressbook.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

    private static String id;
    private final String surname;
    private final String firstName;
    private final String postcode;

    @JsonCreator
    public Customer(@JsonProperty("id") String id, @JsonProperty("surname") String surname, @JsonProperty("firstName") String firstName, @JsonProperty("postcode") String postcode) {
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.postcode = postcode;
    }

    public void setId(String id){
        this.id = id ;
    }

    public String getId(){
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
