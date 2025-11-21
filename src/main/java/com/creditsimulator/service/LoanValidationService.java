package com.creditsimulator.service;

import com.creditsimulator.model.LoanRequest;
import com.creditsimulator.model.VehicleCondition;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class LoanValidationService {

    public List<String> validate(LoanRequest request) {
        List<String> errors = new ArrayList<>();

        validateVehicleYear(request, errors);
        validateTenor(request, errors);
        validateDownPayment(request, errors);
        validateLoanAmount(request, errors);
        validateOtherAmount(request, errors);

        return errors;
    }

    private void validateVehicleYear(LoanRequest request, List<String> errors) {
        int currentYear = Year.now().getValue();
        if (request.getVehicleCondition() == VehicleCondition.BARU) {
            if (request.getVehicleYear() < currentYear - 1) {
                errors.add("Kendaraan baru tidak boleh tahun kurang dari " + (currentYear - 1));
            }
        }
    }

    private void validateTenor(LoanRequest request, List<String> errors) {
        if (request.getTenorYears() < 1 || request.getTenorYears() > 6) {
            errors.add("Tenor harus antara 1-6 tahun");
        }
    }

    private void validateDownPayment(LoanRequest request, List<String> errors) {
        double minDpPercentage = request.getVehicleCondition().getMinimumDownPaymentPercentage();
        double minDp = request.getLoanAmount() * minDpPercentage / 100;

        if (request.getDownPayment() < minDp) {
            errors.add(String.format("DP minimal %.0f%% dari jumlah pinjaman (Rp %.2f)",
                    minDpPercentage, minDp));
        }
    }

    private void validateLoanAmount(LoanRequest request, List<String> errors) {
        if (request.getLoanAmount() > 1_000_000_000) {
            errors.add("Jumlah pinjaman tidak boleh lebih dari 1 miliar");
        }
    }

    private void validateOtherAmount(LoanRequest request, List<String> errors) {
        if (request.getLoanAmount() <= 0) {
            errors.add("Jumlah pinjaman harus lebih dari 0");
        }
        if (request.getDownPayment() < 0) {
            errors.add("DP tidak boleh kurang dari 0");
        }
        if (request.getDownPayment() > request.getLoanAmount()) {
            errors.add("DP tidak boleh lebih dari jumlah pinjaman");
        }
    }
}
