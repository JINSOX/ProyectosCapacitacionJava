package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.entity.Customer;
import org.acme.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Path("/customer")
public class CustomerResource {

    @Inject
    private CustomerService customerService;

    @GET
    @RolesAllowed("manager")
    public List<Customer> retrieveAll() {
        List<Customer> customers = new ArrayList<>();

        try {
            customers = customerService.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all customers", e);
        }
        return customers;
    }

    @POST
    @RolesAllowed("manager")
    @Transactional
    public void addCustomer(Customer customer) {
        customerService.addCustomer(customer);
    }
}
