package com.creditsimulator.model;

public class LoanData {
    private String vehicleType;
    private String vehicleCondition;
    private int vehicleYear;
    private double totalLoanAmount;
    private int loanTenure;
    private double downPayment;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public int getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public double getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public void setTotalLoanAmount(double totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public int getLoanTenure() {
        return loanTenure;
    }

    public void setLoanTenure(int loanTenure) {
        this.loanTenure = loanTenure;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public LoanData(String vehicleType, String vehicleCondition, int vehicleYear, double totalLoanAmount, int loanTenure, double downPayment) {
        this.vehicleType = vehicleType;
        this.vehicleCondition = vehicleCondition;
        this.vehicleYear = vehicleYear;
        this.totalLoanAmount = totalLoanAmount;
        this.loanTenure = loanTenure;
        this.downPayment = downPayment;
    }

    public LoanRequest toLoanRequest() {
        return new LoanRequest(
                VehicleType.fromString(vehicleType),
                VehicleCondition.fromString(vehicleCondition),
                vehicleYear,
                totalLoanAmount,
                loanTenure,
                downPayment
        );
    }
}
