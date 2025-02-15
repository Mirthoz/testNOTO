package com.example.transactionproducer.service;

import com.example.common.dto.TransactionDto;
import com.example.common.enumeration.Country;
import com.example.transactionproducer.service.contract.ProducerService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProducerServiceImpl implements ProducerService {

  private final RestTemplate restTemplate;
  private static final Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);

  private String consumerUrl = "http://localhost:8082/transactions/transaction";

  public ProducerServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public void generateTransaction() {
    String userId = UUID.randomUUID().toString();
    BigDecimal amount = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(10.0, 10000.0))
        .setScale(2, RoundingMode.HALF_DOWN);
    Instant timestamp = Instant.ofEpochSecond(System.currentTimeMillis());
    Country[] countries = Country.values();
    Country country = countries[ThreadLocalRandom.current().nextInt(countries.length)];
    double latitudeCoordinate = ThreadLocalRandom.current().nextDouble(-90.0, 90.0);
    double longitudeCoordinate = ThreadLocalRandom.current().nextDouble(-180.0, 180.0);

    TransactionDto transactionDto = new TransactionDto(
        userId,
        amount,
        timestamp,
        country,
        latitudeCoordinate,
        longitudeCoordinate
    );

    try {
      ResponseEntity<String> response = restTemplate.postForEntity(consumerUrl, transactionDto, String.class);
      logger.info("Transaction sent successfully. Response: Status={}, Body={}", response.getStatusCode(), response.getBody());
    } catch (Exception e) {
      logger.error("Failed to send transaction: {}", e.getMessage());
    }
  }
}
