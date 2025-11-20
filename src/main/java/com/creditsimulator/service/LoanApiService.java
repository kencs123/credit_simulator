package com.creditsimulator.service;

import com.creditsimulator.model.LoanData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoanApiService {
    private static final String API_URL = "https://run.mocky.io/v3/9108b1da-beec-409e-ae14-e212003666c";
    private final Gson gson;

    public LoanApiService() {
        this.gson = new Gson();

    }

    public LoanData fetchLoanData() throws Exception {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("HTTP Error: " + responseCode);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return gson.fromJson(response.toString(), LoanData.class);
        } catch (Exception e) {

            System.err.println("get from api fail " + e.getMessage());


            String hardcodedJson = """
                {
                    "vehicleType": "Mobil",
                    "vehicleCondition": "Baru",
                    "vehicleYear": 2025,
                    "totalLoanAmount": 100000000,
                    "loanTenure": 5,
                    "downPayment": 50000000
                }
                """;

            return gson.fromJson(hardcodedJson, LoanData.class);
        }
    }
}
