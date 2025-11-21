package com.creditsimulator.service;

import com.creditsimulator.model.LoanData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInputService {

    public List<LoanData> readLoanDataFromFile(String filePath) throws IOException {
        List<LoanData> loanDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                try {
                    LoanData loanData = parseLoanDataFromLine(line);
                    loanDataList.add(loanData);
                } catch (Exception e) {
                    throw new IOException("Error parsing line " + lineNumber + ": " + e.getMessage());
                }
            }
        }

        return loanDataList;
    }

    private LoanData parseLoanDataFromLine(String line) {
        String[] parts = line.split(",");
        String vehicleType = null;
        String vehicleCondition = null;
        Integer vehicleYear = null;
        Double loanAmount = null;
        Integer tenor = null;
        Double downPayment = null;

        for (String part : parts) {
            String[] keyValue = part.trim().split("=");
            if (keyValue.length != 2) continue;

            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            switch (key) {
                case "vehicleType":
                    vehicleType = value;
                    break;
                case "vehicleCondition":
                    vehicleCondition = value;
                    break;
                case "vehicleYear":
                    vehicleYear = Integer.parseInt(value);
                    break;
                case "totalLoanAmount":
                    loanAmount = Double.parseDouble(value);
                    break;
                case "loanTenure":
                    tenor = Integer.parseInt(value);
                    break;
                case "downPayment":
                    downPayment = Double.parseDouble(value);
                    break;
            }
        }
        // Validate required fields
        if (vehicleType == null || vehicleCondition == null || vehicleYear == null ||
                loanAmount == null || tenor == null || downPayment == null) {
            throw new IllegalArgumentException("Missing required fields in line: " + line);
        }

        return new LoanData(vehicleType, vehicleCondition, vehicleYear,
                loanAmount, tenor, downPayment);
    }
}
