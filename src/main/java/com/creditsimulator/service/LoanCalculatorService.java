package com.creditsimulator.service;

import com.creditsimulator.model.InstallmentDetail;
import com.creditsimulator.model.LoanRequest;

import java.util.ArrayList;
import java.util.List;

public class LoanCalculatorService {


    public List<InstallmentDetail> calculate(LoanRequest request) {
        List<InstallmentDetail> installments = new ArrayList<>();
        double baseRate = request.getVehicleType().getBaseInterestRate();
        double remainingPrincipal = request.getLoanAmount() - request.getDownPayment();
        int totalTenorMonths = request.getTenorYears() * 12;

        for (int year = 1; year <= request.getTenorYears(); year++) {
            double interestRate = calculateInterestRate(baseRate, year);

            double yearlyInterest = remainingPrincipal * (interestRate / 100);
            double totalLoanThisYear = remainingPrincipal + yearlyInterest;

            int monthsPassed = (year - 1) * 12;
            int remainingMonths = totalTenorMonths - monthsPassed;

            double monthlyInstallment = totalLoanThisYear / remainingMonths;

            double yearlyPayment = monthlyInstallment * 12;

            installments.add(new InstallmentDetail(year, monthlyInstallment, interestRate));

            remainingPrincipal = totalLoanThisYear - yearlyPayment;
        }

        return installments;
    }

    private double calculateInterestRate(double baseRate, int year) {
        if (year == 1) {
            return baseRate;
        }

        double rate = baseRate;

        for (int y = 2; y <= year; y++) {
            if ((y - 1) % 2 == 1) {
                rate += 0.1;
            } else {
                rate += 0.5;
            }
        }

        return rate;
    }
    }

