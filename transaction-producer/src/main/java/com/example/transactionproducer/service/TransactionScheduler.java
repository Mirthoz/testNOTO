package com.example.transactionproducer.service;
import com.example.transactionproducer.service.contract.ProducerService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class TransactionScheduler {

  private final ProducerService producerService;

  public TransactionScheduler(ProducerService producerService) {
    this.producerService = producerService;
  }

  @Scheduled(fixedRate = 1000)
  public void scheduleTransactionGeneration() {
    producerService.generateTransaction();
  }
}
