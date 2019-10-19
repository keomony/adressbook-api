package addressbook.controllers;

import addressbook.models.Customer;
import addressbook.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/customers")
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Integer id)
            throws Exception {
        Customer customer =
                customerRepository
                        .findById(id)
                        .orElseThrow(() -> new Exception("Customer not found on :: " + id));
        return ResponseEntity.ok().body(customer);
    }

}
