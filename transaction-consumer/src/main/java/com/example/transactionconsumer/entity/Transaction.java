package com.example.transactionconsumer.entity;

import com.example.common.enumeration.Country;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
public class Transaction {

  public Transaction() {
  }

  public Transaction(String id, String userId, BigDecimal amount, Instant timestamp,
      Country country,
      double latitudeCoordinate, double longitudeCoordinate) {
    this.id = id;
    this.userId = userId;
    this.amount = amount;
    this.timestamp = timestamp;
    this.country = country;
    this.latitudeCoordinate = latitudeCoordinate;
    this.longitudeCoordinate = longitudeCoordinate;
  }

  @Id
  private String id;

  @Indexed
  @NotBlank
  private String userId;

  @NotBlank
  private BigDecimal amount;

  @NotBlank
  private Instant timestamp;

  @NotBlank
  private Country country;

  @NotBlank
  private double latitudeCoordinate;

  @NotBlank
  private double longitudeCoordinate;

  public String getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public Country getCountry() {
    return country;
  }

  public double getLatitudeCoordinate() {
    return latitudeCoordinate;
  }

  public double getLongitudeCoordinate() {
    return longitudeCoordinate;
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "id='" + id + '\'' +
        ", userId='" + userId + '\'' +
        ", amount=" + amount +
        ", timestamp=" + timestamp +
        ", country=" + country +
        ", latitudeCoordinate=" + latitudeCoordinate +
        ", longitudeCoordinate=" + longitudeCoordinate +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction that = (Transaction) o;
    return Double.compare(latitudeCoordinate, that.latitudeCoordinate) == 0
        && Double.compare(longitudeCoordinate, that.longitudeCoordinate) == 0
        && Objects.equals(id, that.id) && Objects.equals(userId, that.userId)
        && Objects.equals(amount, that.amount) && Objects.equals(timestamp,
        that.timestamp) && country == that.country;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, amount, timestamp, country, latitudeCoordinate,
        longitudeCoordinate);
  }
}
