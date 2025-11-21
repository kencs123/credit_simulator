package com.creditsimulator.service;

import com.creditsimulator.model.LoanData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoanApiService {
    private static final String API_URL = "https://692026eb31e684d7bfcbe6f1.mockapi.io/api/v1/loanData/1";
    private final Gson gson;

    public LoanApiService() {
        this.gson = new Gson();

    }

    public LoanData fetchLoanData() throws Exception {

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

        String jsonResponse = response.toString();
        if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
            throw new Exception("Empty response from API");
        }

        LoanData loanData = gson.fromJson(jsonResponse, LoanData.class);
        if (loanData == null) {
            throw new Exception("Failed to parse API response");
        }
        return loanData;

    }
}
