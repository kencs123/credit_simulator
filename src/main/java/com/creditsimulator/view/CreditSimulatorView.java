package com.creditsimulator.view;

import com.creditsimulator.model.InstallmentDetail;
import com.creditsimulator.model.LoanData;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CreditSimulatorView {
    private final NumberFormat currencyFormat;
    private final Scanner scanner;

    public CreditSimulatorView() {
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        this.scanner = new Scanner(System.in);
    }

    public void displayWelcome() {
        System.out.println("\n+====================================+");
        System.out.println("|   Simulasi Kredit Kendaraan        |");
        System.out.println("+====================================+");
    }

    public int getMainMenuChoice() {
        System.out.println("\nSilakan pilih metode input:");
        System.out.println("1. Load dari API");
        System.out.println("2. Input Manual");
        System.out.print("\nPilihan Anda (1-2): ");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
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
        System.out.println("+------+---------------------+------------+");
        System.out.println("| Tahun| Cicilan/Bulan       | Bunga (%)  |");
        System.out.println("+------+---------------------+------------+");

        for (InstallmentDetail detail : installments) {
            System.out.printf("| %-5d| %-19s | %-10.2f |%n",
                    detail.getYear(),
                    currencyFormat.format(detail.getMonthlyInstallment()),
                    detail.getInterestRate());
        }

        System.out.println("+------+---------------------+------------+\n");
    }

}
