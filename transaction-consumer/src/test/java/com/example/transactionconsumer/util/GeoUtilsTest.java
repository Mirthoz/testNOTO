package com.example.transactionconsumer.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoUtilsTest {

  @Test
  public void testCalculateGeoDistance_SameLocation_ShouldReturnZero() {
    // Given
    double latitudeFirstPoint = 52.5200; // Berlin
    double longitudeFirstPoint = 13.4050;
    double latitudeSecondPoint = 52.5200; // Berlin
    double longitudeSecondPoint = 13.4050;

    // When
    double result = GeoUtils.calculateGeoDistance(latitudeFirstPoint, longitudeFirstPoint, latitudeSecondPoint, longitudeSecondPoint);

    // Then
    assertEquals(0.0, result, 0.001);
  }

  @Test
  public void testCalculateGeoDistance_SmallDistance_ShouldReturnNonZero() {
    // Given
    double latitudeFirstPoint = 52.5200; // Berlin
    double longitudeFirstPoint = 13.4050;
    double latitudeSecondPoint = 52.5300;
    double longitudeSecondPoint = 13.4050;

    // When
    double result = GeoUtils.calculateGeoDistance(latitudeFirstPoint, longitudeFirstPoint, latitudeSecondPoint, longitudeSecondPoint);

    // Then
    assertEquals(1.1119492664453663, result, 0.001);
  }

  @Test
  public void testCalculateGeoDistance_LargeDistance_ShouldReturnCorrectDistance() {
    // Given
    double latitudeFirstPoint = 48.8566; // Paris
    double longitudeFirstPoint = 2.3522;
    double latitudeSecondPoint = 52.5200; // Berlin
    double longitudeSecondPoint = 13.4050;

    // When
    double result = GeoUtils.calculateGeoDistance(latitudeFirstPoint, longitudeFirstPoint, latitudeSecondPoint, longitudeSecondPoint);

    // Then
    assertEquals(877.4633259175431, result, 0.5);
  }
}
