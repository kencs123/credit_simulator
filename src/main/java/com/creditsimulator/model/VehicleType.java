package com.creditsimulator.model;

public enum VehicleType {
    MOBIL(8.0),
    MOTOR(9.0);

    private final double baseInterestRate;

    VehicleType(double baseInterestRate) {
        this.baseInterestRate = baseInterestRate;
    }

    public double getBaseInterestRate() {
        return baseInterestRate;
    }

    public static VehicleType fromString(String type) {
        return VehicleType.valueOf(type.toUpperCase());
    }
}
