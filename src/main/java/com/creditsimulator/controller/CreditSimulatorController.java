package com.creditsimulator.controller;

import com.creditsimulator.model.InstallmentDetail;
import com.creditsimulator.model.LoanData;
import com.creditsimulator.model.LoanRequest;
import com.creditsimulator.service.FileInputService;
import com.creditsimulator.service.LoanApiService;
import com.creditsimulator.service.LoanCalculatorService;
import com.creditsimulator.service.LoanValidationService;
import com.creditsimulator.view.CreditSimulatorView;

import java.util.List;
import java.util.Scanner;

public class CreditSimulatorController {
    private final CreditSimulatorView view;
    private final LoanValidationService validationService;
    private final LoanCalculatorService calculatorService;
    private final LoanApiService apiService;
    private final FileInputService fileInputService;
    private final Scanner scanner;

    public CreditSimulatorController(CreditSimulatorView view) {
        this.view = view;
        this.validationService = new LoanValidationService();
        this.calculatorService = new LoanCalculatorService();
        this.apiService = new LoanApiService();
        this.fileInputService = new FileInputService();
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        view.displayWelcome();
        int choice = view.getMainMenuChoice();

        switch (choice) {
            case 1:
                loadFromApi();
                break;
            case 2:
                handleManualInput();
                break;
            default:
                System.err.println("Option not valid ");
        }
    }

    public void processFileInput(String filePath) {
        try {
            List<LoanData> loanDataList = fileInputService.readLoanDataFromFile(filePath);

            int entryNumber = 1;
            for (LoanData loanData : loanDataList) {
                System.out.println("\n--- Processing Entry " + entryNumber + " ---");
                processLoanData(loanData);
                entryNumber++;
            }

        } catch (Exception e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
    }

    public void loadFromApi() {
        try {
            System.out.println("fetching data from api");
            LoanData loanData = apiService.fetchLoanData();
            processLoanData(loanData);
        } catch (Exception e) {
            System.err.println("Failed to get data from api " + e.getMessage());
        }
    }

    private void handleManualInput() {
        System.out.println("\n=== Input Data Pinjaman ===");

        // Vehicle Type Input
        String vehicleType = null;
        while (vehicleType == null) {
            try {
                System.out.print("\nTipe Kendaraan (Mobil/Motor): ");
                String typeInput = scanner.nextLine();
                if (typeInput == null || typeInput.trim().isEmpty()) {
                    throw new IllegalArgumentException("Tipe kendaraan tidak boleh kosong");
                }
                String normalizedType = typeInput.trim().toLowerCase();
                if (!normalizedType.equals("mobil") && !normalizedType.equals("motor")) {
                    throw new IllegalArgumentException("Tipe kendaraan harus 'Mobil' atau 'Motor'");
                }
                vehicleType = normalizedType.substring(0, 1).toUpperCase() + normalizedType.substring(1);
            } catch (Exception e) {
                System.err.println("Input tidak valid: " + e.getMessage());
            }
        }

        // Vehicle Condition Input
        String vehicleCondition = null;
        while (vehicleCondition == null) {
            try {
                System.out.print("Kondisi Kendaraan (Baru/Bekas): ");
                String conditionInput = scanner.nextLine();
                if (conditionInput == null || conditionInput.trim().isEmpty()) {
                    throw new IllegalArgumentException("Kondisi kendaraan tidak boleh kosong");
                }
                String normalizedCondition = conditionInput.trim().toLowerCase();
                if (!normalizedCondition.equals("baru") && !normalizedCondition.equals("bekas")) {
                    throw new IllegalArgumentException("Kondisi harus 'Baru' atau 'Bekas'");
                }
                vehicleCondition = normalizedCondition.substring(0, 1).toUpperCase() + normalizedCondition.substring(1);
            } catch (Exception e) {
                System.err.println("Input tidak valid: " + e.getMessage());
            }
        }

        // Vehicle Year Input
        Integer vehicleYear = null;
        while (vehicleYear == null) {
            try {
                System.out.print("\nTahun Kendaraan: ");
                String yearInput = scanner.nextLine();
                if (yearInput == null || yearInput.trim().isEmpty()) {
                    throw new IllegalArgumentException("Tahun tidak boleh kosong");
                }
                String trimmedYear = yearInput.trim();
                if (trimmedYear.length() != 4) {
                    throw new IllegalArgumentException("Tahun harus 4 digit");
                }
                vehicleYear = Integer.parseInt(trimmedYear);
                if (vehicleYear < 1000 || vehicleYear > 9999) {
                    throw new IllegalArgumentException("Tahun harus 4 digit");
                }
            } catch (NumberFormatException e) {
                System.err.println("Input tidak valid: Masukkan tahun dalam angka");
            } catch (Exception e) {
                System.err.println("Input tidak valid: " + e.getMessage());
            }
        }

        // Loan Amount Input
        Double loanAmount = null;
        while (loanAmount == null) {
            try {
                System.out.print("Total Pinjaman (Rp): ");
                String loanInput = scanner.nextLine();
                if (loanInput == null || loanInput.trim().isEmpty()) {
                    throw new IllegalArgumentException("Total pinjaman tidak boleh kosong");
                }
                loanAmount = Double.parseDouble(loanInput.trim());

                if (loanAmount > 1_000_000_000 || loanAmount <= 0) {
                    loanAmount = null;
                    throw new IllegalArgumentException("Jumlah pinjaman tidak boleh lebih dari 1 miliar");
                }
            } catch (NumberFormatException e) {
                System.err.println("Input tidak valid: Masukkan jumlah dalam angka");
            } catch (Exception e) {
                System.err.println("Input tidak valid: " + e.getMessage());
            }
        }

        // Down Payment Input
        Double downPayment = null;
        while (downPayment == null) {
            try {
                System.out.print("Uang Muka/DP (Rp): ");
                String dpInput = scanner.nextLine();
                if (dpInput == null || dpInput.trim().isEmpty()) {
                    throw new IllegalArgumentException("Uang muka tidak boleh kosong");
                }
                downPayment = Double.parseDouble(dpInput.trim());
            } catch (NumberFormatException e) {
                System.err.println("Input tidak valid: Masukkan jumlah dalam angka");
            } catch (Exception e) {
                System.err.println("Input tidak valid: " + e.getMessage());
            }
        }

        // Tenor Input
        Integer tenor = null;
        while (tenor == null) {
            try {
                System.out.print("Tenor (tahun): ");
                String tenorInput = scanner.nextLine();
                if (tenorInput == null || tenorInput.trim().isEmpty()) {
                    throw new IllegalArgumentException("Tenor tidak boleh kosong");
                }
                tenor = Integer.parseInt(tenorInput.trim());
                if (tenor < 1 || tenor > 6) {
                    throw new IllegalArgumentException("Tenor harus antara 1-6 tahun");
                }
            } catch (NumberFormatException e) {
                System.err.println("Input tidak valid: Masukkan tenor dalam angka");
            } catch (Exception e) {
                System.err.println("Input tidak valid: " + e.getMessage());
            }
        }

        LoanData loanData = new LoanData(vehicleType, vehicleCondition,
                vehicleYear, loanAmount, tenor, downPayment);

        processLoanData(loanData);
    }


    private void processLoanData(LoanData loanData) {
        view.showLoanData(loanData);

        LoanRequest request = loanData.toLoanRequest();
        List<String> errors = validationService.validate(request);

        if (!errors.isEmpty()) {
            System.err.println("\nVALIDASI GAGAL:");
            errors.forEach(error -> System.err.println("- " + error));
            return;
        }

        List<InstallmentDetail> installments = calculatorService.calculate(request);
        view.showInstallments(installments);
    }
}
