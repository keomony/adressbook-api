package addressbook.repositories;

import addressbook.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Streamable<Customer> findBySurnameContaining(String surname);

}
