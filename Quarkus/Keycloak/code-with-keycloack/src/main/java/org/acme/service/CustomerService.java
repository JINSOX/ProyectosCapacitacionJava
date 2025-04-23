package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.entity.Customer;
import org.acme.repository.CustomerRepository;

import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll().list();
    }

    public Customer addCustomer(Customer customer) {
        customerRepository.persist(customer);
        return customer;
    }
}
