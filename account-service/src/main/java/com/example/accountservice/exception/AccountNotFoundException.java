package com.example.accountservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends Exception {

  public AccountNotFoundException(String accountNumber) {
    super("No such account: " + accountNumber);
  }
}
