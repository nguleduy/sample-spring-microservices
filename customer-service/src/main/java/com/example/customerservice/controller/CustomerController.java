package com.example.customerservice.controller;

import com.example.customerservice.communication.AccountClient;
import com.example.customerservice.exception.CustomerNotFoundException;
import com.example.customerservice.model.Account;
import com.example.customerservice.model.Customer;
import com.example.customerservice.model.CustomerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomerController {

  @Autowired
  private AccountClient accountClient;

  protected Logger logger = Logger.getLogger(CustomerController.class.getName());

  private List<Customer> customers;

  public CustomerController() {
    customers = new ArrayList<>();
    customers.add(new Customer(1, "12345", "person 1", CustomerType.INDIVIDUAL));
    customers.add(new Customer(2, "12346", "person 2", CustomerType.INDIVIDUAL));
    customers.add(new Customer(3, "12347", "person 3", CustomerType.INDIVIDUAL));
    customers.add(new Customer(4, "12348", "person 4", CustomerType.INDIVIDUAL));
  }

  @RequestMapping(method = RequestMethod.GET, value = "/identificationCard/{identificationCard}")
  public Customer findByIdentificationCard(@PathVariable("identificationCard") String identificationCard) throws CustomerNotFoundException {
    logger.info(String.format("Customer.findByIdentificationCard(%s)", identificationCard));
    return customers.stream()
            .filter(it -> it.getIdentificationCard().equals(identificationCard))
            .findFirst()
            .orElseThrow(() -> new CustomerNotFoundException("identificationCard : " + identificationCard));
  }

  @RequestMapping(method = RequestMethod.GET, value = "")
  public List<Customer> findAll() {
    logger.info("Customer.findAll()");
    return customers;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public Customer findById(@PathVariable("id") Integer id) throws CustomerNotFoundException {
    logger.info(String.format("Customer.findById(%s)", id));
    Customer customer = customers.stream()
            .filter(it -> it.getId().intValue() == id.intValue())
            .findFirst().orElseThrow(() -> new CustomerNotFoundException("id : " + id));

    List<Account> accounts = accountClient.getAccounts(id);
    customer.setAccounts(accounts);
    return customer;
  }

  @RequestMapping(method = RequestMethod.POST, value = "")
  public Customer createNewCustomer(@RequestBody Customer customer) {
    logger.info("Customer.createNewCustomer()");
    if (customer.getId() != null) {
      return null;
    }
    int size = customers.size();
    customer.setId(size + 1);
    customers.add(customer);
    return customer;
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public boolean deleteCustomer(@PathVariable Integer id) {
    logger.info("Customer.deleteCustomer()");
    try {
      Customer byId = findById(id);
      customers.remove(byId);
    } catch (CustomerNotFoundException e) {
      return false;
    }
    return true;
  }

}
