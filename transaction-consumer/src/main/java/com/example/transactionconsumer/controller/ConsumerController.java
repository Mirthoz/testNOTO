package com.example.transactionconsumer.controller;

import com.example.common.dto.TransactionDto;
import com.example.transactionconsumer.service.contract.ProcessTransactionFactoryService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class ConsumerController {

  private final ProcessTransactionFactoryService processTransactionFactoryService;

  public ConsumerController(ProcessTransactionFactoryService processTransactionFactoryService) {
    this.processTransactionFactoryService = processTransactionFactoryService;
  }

  @PostMapping("/transaction")
  public void handleTransaction(@Valid @RequestBody TransactionDto transactionDto){
    processTransactionFactoryService.processTransactionFactory(transactionDto);
    System.out.println(transactionDto.toString());
  }

}
