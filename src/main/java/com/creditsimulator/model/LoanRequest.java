package com.creditsimulator.model;

public class LoanRequest {

    private VehicleType vehicleType;
    private VehicleCondition vehicleCondition;
    private int vehicleYear;
    private double loanAmount;
    private int tenorYears;
    private double downPayment;

    public LoanRequest(VehicleType vehicleType, VehicleCondition vehicleCondition, int vehicleYear, double loanAmount, int tenorYears, double downPayment) {
        this.vehicleType = vehicleType;
        this.vehicleCondition = vehicleCondition;
        this.vehicleYear = vehicleYear;
        this.loanAmount = loanAmount;
        this.tenorYears = tenorYears;
        this.downPayment = downPayment;
    }


    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleCondition getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(VehicleCondition vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public int getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getTenorYears() {
        return tenorYears;
    }

    public void setTenorYears(int tenorYears) {
        this.tenorYears = tenorYears;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }


}
