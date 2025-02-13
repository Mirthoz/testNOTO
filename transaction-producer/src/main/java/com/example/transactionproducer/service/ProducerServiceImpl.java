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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProducerServiceImpl implements ProducerService {

  private final RestTemplate restTemplate;

  @Value("${transaction.consumer.endpoint}")
  private String consumerUrl;

  public ProducerServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public void generateTransaction() {
    String transactionId = UUID.randomUUID().toString();
    String userId = UUID.randomUUID().toString();
    BigDecimal amount = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(10.0, 10000.0))
        .setScale(2, RoundingMode.HALF_DOWN);
    Instant timestamp = Instant.ofEpochSecond(System.currentTimeMillis());
    Country[] countries = Country.values();
    Country country = countries[ThreadLocalRandom.current().nextInt(countries.length)];
    double latitudeCoordinate = ThreadLocalRandom.current().nextDouble(-90.0, 90.0);
    double longitudeCoordinate = ThreadLocalRandom.current().nextDouble(-180.0, 180.0);

    TransactionDto transactionDto = new TransactionDto(
        transactionId,
        userId,
        amount,
        timestamp,
        country,
        latitudeCoordinate,
        longitudeCoordinate
    );

    restTemplate.postForObject(consumerUrl, transactionDto, String.class);
  }
}
