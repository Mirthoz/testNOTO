package com.example.transactionconsumer.controller;

import com.example.common.dto.TransactionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class ConsumerController {

  @GetMapping("transaction")
  public void getTransaction(TransactionDto transactionDto){

  }

}
