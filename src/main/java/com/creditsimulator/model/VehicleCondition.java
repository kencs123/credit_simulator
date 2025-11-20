package com.creditsimulator.model;

public enum VehicleCondition {
    BARU(35.0),
    BEKAS(25.0);

    private final double minimumDownPaymentPercentage;

    VehicleCondition(double minimumDownPaymentPercentage) {
        this.minimumDownPaymentPercentage = minimumDownPaymentPercentage;
    }

    public double getMinimumDownPaymentPercentage() {
        return minimumDownPaymentPercentage;
    }

    public static VehicleCondition fromString(String condition) {
        return VehicleCondition.valueOf(condition.toUpperCase());
    }
}
