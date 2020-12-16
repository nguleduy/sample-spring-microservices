package com.example.customerservice.controller;

import com.example.customerservice.communication.AccountClient;
import com.example.customerservice.model.Account;
import com.example.customerservice.model.Customer;
import com.example.customerservice.model.CustomerType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomerController {

  @Autowired
  RabbitTemplate template;

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

  @RequestMapping("/customers/identificationCard/{identificationCard}")
  public Customer findByIdentificationCard(@PathVariable("identificationCard") String identificationCard) {
    logger.info(String.format("Customer.findByIdentificationCard(%s)", identificationCard));
    return customers.stream().filter(it -> it.getIdentificationCard().equals(identificationCard)).findFirst().get();
  }

  @RequestMapping("/customers")
  public List<Customer> findAll() {
    logger.info("Customer.findAll()");
    return customers;
  }

  @RequestMapping("/customers/{id}")
  public Customer findById(@PathVariable("id") Integer id) {
    logger.info(String.format("Customer.findById(%s)", id));
    Customer customer = customers.stream().filter(it -> it.getId().intValue() == id.intValue()).findFirst().get();
    List<Account> accounts = accountClient.getAccounts(id);
    customer.setAccounts(accounts);
    return customer;
  }

  @RequestMapping("/customers/add/{id}/{identificationCard}")
  public Customer addCustomer(@PathVariable("id") Integer id, @PathVariable("identificationCard") String identificationCard) {
    logger.info(String.format("Customer.add(%d, %s)", id, identificationCard));
    template.convertAndSend(id + "-" + identificationCard);
    return new Customer(id, identificationCard, "Test Test", CustomerType.INDIVIDUAL);
  }
}
