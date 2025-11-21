package com.creditsimulator.test;

import com.creditsimulator.model.*;
import com.creditsimulator.service.LoanCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Credit Simulator Tests")
class CreditSimulatorTest {

    private LoanCalculatorService calculator;

    @BeforeEach
    void setUp() {
        calculator = new LoanCalculatorService();
    }

    @Test
    @DisplayName("New car loan request should generate correct number of installments")
    void testNewMobilLoan() {
        LoanRequest newMobilLoan = new LoanRequest(
                VehicleType.MOBIL,
                VehicleCondition.BARU,
                2024,
                300000,
                5,
                50000
        );
        List<InstallmentDetail> installments = calculator.calculate(newMobilLoan);

        assertEquals(5, installments.size(), "Should generate 5 installments");
        assertTrue(installments.getFirst().getMonthlyInstallment() > 0,
                "Year 1 monthly installment should be positive");
    }

    @Test
    @DisplayName("used car loan should generate correct number of installments")
    void testUsedMobilLoan() {
        LoanRequest usedMobilLoan = new LoanRequest(
                VehicleType.MOBIL,
                VehicleCondition.BEKAS,
                2020,
                200000,
                3,
                30000
        );
        List<InstallmentDetail> installments = calculator.calculate(usedMobilLoan);

        assertEquals(3, installments.size(), "Should generate 3 installments");
    }

    @Test
    @DisplayName("New motor loan should generate correct number of installments")
    void testNewMotorcycleLoan() {
        LoanRequest newMotorLoan = new LoanRequest(
                VehicleType.MOTOR,
                VehicleCondition.BARU,
                2024,
                50000,
                2,
                10000
        );
        List<InstallmentDetail> installments = calculator.calculate(newMotorLoan);

        assertEquals(2, installments.size(), "Should generate 2 installments");
    }

    @Test
    @DisplayName("Interest rate should increase each year")
    void testInterestRateProgression() {
        LoanRequest loan = new LoanRequest(
                VehicleType.MOBIL,
                VehicleCondition.BARU,
                2024,
                100000,
                3,
                20000
        );
        List<InstallmentDetail> installments = calculator.calculate(loan);

        double year1Rate = installments.get(0).getInterestRate();
        double year2Rate = installments.get(1).getInterestRate();

        assertTrue(year2Rate > year1Rate,
                "Year 2 interest rate should be higher than Year 1");
    }

    @Test
    @DisplayName("LoanData should convert to LoanRequest correctly")
    void testLoanDataConversion() {
        LoanData loanData = new LoanData("MOBIL", "BARU", 2024, 150000, 4, 25000);
        LoanRequest convertedRequest = loanData.toLoanRequest();

        assertEquals(VehicleType.MOBIL, convertedRequest.getVehicleType(),
                "Vehicle type should be MOBIL");
        assertEquals(VehicleCondition.BARU, convertedRequest.getVehicleCondition(),
                "Vehicle condition should be BARU");
        assertEquals(150000.0, convertedRequest.getLoanAmount(), 0.01,
                "Loan amount should match");
    }

    @Test
    @DisplayName("DP should reduce monthly installment")
    void testDownPaymentImpact() {
        LoanRequest withDownPayment = new LoanRequest(
                VehicleType.MOBIL,
                VehicleCondition.BARU,
                2024,
                100000,
                2,
                20000
        );
        LoanRequest withoutDownPayment = new LoanRequest(
                VehicleType.MOBIL,
                VehicleCondition.BARU,
                2024,
                100000,
                2,
                0
        );

        List<InstallmentDetail> withDP = calculator.calculate(withDownPayment);
        List<InstallmentDetail> withoutDP = calculator.calculate(withoutDownPayment);

        assertTrue(withDP.getFirst().getMonthlyInstallment() < withoutDP.getFirst().getMonthlyInstallment(),
                "Monthly installment with dp should be less");
    }

    @Test
    @DisplayName("Zero DP should calculate successfully")
    void testZeroDownPayment() {
        LoanRequest zeroDP = new LoanRequest(
                VehicleType.MOTOR,
                VehicleCondition.BEKAS,
                2020,
                30000,
                2,
                0
        );
        List<InstallmentDetail> installments = calculator.calculate(zeroDP);

        assertEquals(2, installments.size(),
                "Loan with zero DP should calculate successfully");
    }
}
