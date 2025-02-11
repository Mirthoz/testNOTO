package com.example.transactionconsumer.util;

public class GeoUtils {
  private static final double EARTH_RADIUS_KILOMETERS = 6371.0;

  //Haversine formula
  public static double calculateGeoDistance(
    double latitudeFirstPoint, double longitudeFirstPoint,
    double latitudeSecondPoint, double longitudeSecondPoint) {

      double latitudeDifferenceRadians = Math.toRadians(latitudeSecondPoint - latitudeFirstPoint);
      double longitudeDifferenceRadians = Math.toRadians(longitudeSecondPoint - longitudeFirstPoint);
      double latitudeFirstPointRadians = Math.toRadians(latitudeFirstPoint);
      double latitudeSecondPointRadians = Math.toRadians(latitudeSecondPoint);

      double haversineFormulaComponent = Math.pow(Math.sin(latitudeDifferenceRadians / 2), 2) +
          Math.cos(latitudeFirstPointRadians) * Math.cos(latitudeSecondPointRadians) *
              Math.pow(Math.sin(longitudeDifferenceRadians / 2), 2);

      double centralAngleRadians = 2 * Math.atan2(
          Math.sqrt(haversineFormulaComponent),
          Math.sqrt(1 - haversineFormulaComponent)
      );

      return EARTH_RADIUS_KILOMETERS * centralAngleRadians;
    }

}
