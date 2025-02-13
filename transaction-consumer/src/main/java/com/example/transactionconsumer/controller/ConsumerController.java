package com.example.transactionconsumer.controller;

import com.example.common.dto.TransactionDto;
import com.example.transactionconsumer.service.contract.ProcessTransactionFactoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class ConsumerController {

  private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

  private final ProcessTransactionFactoryService processTransactionFactoryService;

  public ConsumerController(ProcessTransactionFactoryService processTransactionFactoryService) {
    this.processTransactionFactoryService = processTransactionFactoryService;
  }

  @PostMapping("/transaction")
  public ResponseEntity<String> handleTransaction(@Valid @RequestBody TransactionDto transactionDto) {
    logger.info("Processing transaction for user: {}", transactionDto.userId());
    try {
      processTransactionFactoryService.processTransactionFactory(transactionDto);
      logger.info("Transaction successfully processed for user: {}", transactionDto.userId());
      return ResponseEntity.ok("Transaction processed successfully");
    } catch (RuntimeException e) {
      logger.error("Error processing transaction for user: {}", transactionDto.userId(), e);
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
          .body("Error processing transaction: " + e.getMessage());
    }
  }
}