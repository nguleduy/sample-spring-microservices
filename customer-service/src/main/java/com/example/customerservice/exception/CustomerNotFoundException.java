package com.example.customerservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends Exception {

  public CustomerNotFoundException(String identificationCard) {
    super("No such customer: " + identificationCard);
  }
}
