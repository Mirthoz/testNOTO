package com.example.transactionconsumer.controller;

import com.example.common.dto.TransactionDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class ConsumerController {

  @PostMapping("transaction")
  public void handleTransaction(TransactionDto transactionDto){

  }

}
