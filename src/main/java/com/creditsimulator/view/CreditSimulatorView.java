package com.creditsimulator.view;

import com.creditsimulator.model.InstallmentDetail;
import com.creditsimulator.model.LoanData;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CreditSimulatorView {
    private final NumberFormat currencyFormat;

    public CreditSimulatorView() {
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.err.println("ERROR: " + error);
    }

    public void showErrors(List<String> errors) {
        System.err.println("VALIDATION ERRORS:");
        errors.forEach(error -> System.err.println("- " + error));
    }

    public void showLoanData(LoanData data) {
        System.out.println("\n=== DATA PINJAMAN ===");
        System.out.println("Tipe Kendaraan: " + data.getVehicleType());
        System.out.println("Kondisi: " + data.getVehicleCondition());
        System.out.println("Tahun: " + data.getVehicleYear());
        System.out.println("Total Pinjaman: " + currencyFormat.format(data.getTotalLoanAmount()));
        System.out.println("DP: " + currencyFormat.format(data.getDownPayment()));
        System.out.println("Tenor: " + data.getLoanTenure() + " tahun");
        System.out.println("=====================\n");
    }

    public void showInstallments(List<InstallmentDetail> installments) {
        System.out.println("\n=== RINCIAN CICILAN ===");
        System.out.println("┌──────┬─────────────────────┬────────────┐");
        System.out.println("│ Tahun│ Cicilan/Bulan       │ Bunga (%)  │");
        System.out.println("├──────┼─────────────────────┼────────────┤");

        for (InstallmentDetail detail : installments) {
            System.out.printf("│ %-5d│ %-19s │ %-10.2f │%n",
                    detail.getYear(),
                    currencyFormat.format(detail.getMonthlyInstallment()),
                    detail.getInterestRate());
        }

        System.out.println("└──────┴─────────────────────┴────────────┘\n");
    }
}