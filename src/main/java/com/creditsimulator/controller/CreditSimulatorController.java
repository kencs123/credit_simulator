package com.creditsimulator.controller;

import com.creditsimulator.model.*;
import com.creditsimulator.service.LoanApiService;
import com.creditsimulator.service.LoanCalculatorService;
import com.creditsimulator.service.LoanValidationService;
import com.creditsimulator.view.CreditSimulatorView;

import java.util.List;

public class CreditSimulatorController {
    private final CreditSimulatorView view;
    private final LoanValidationService validationService;
    private final LoanCalculatorService calculatorService;
    private final LoanApiService apiService;

    public CreditSimulatorController(CreditSimulatorView view) {
        this.view = view;
        this.validationService = new LoanValidationService();
        this.calculatorService = new LoanCalculatorService();
        this.apiService = new LoanApiService();
    }


    public void loadFromApi() {
        try {
            LoanData loanData = apiService.fetchLoanData();

            view.showLoanData(loanData);

            LoanRequest request = loanData.toLoanRequest();
            List<String> errors = validationService.validate(request);

            if (!errors.isEmpty()) {
                view.showErrors(errors);
                return;
            }

            List<InstallmentDetail> installments = calculatorService.calculate(request);
            view.showInstallments(installments);

        } catch (Exception e) {
            view.showError("Failed to get data from api: " + e.getMessage());
        }
    }
}
