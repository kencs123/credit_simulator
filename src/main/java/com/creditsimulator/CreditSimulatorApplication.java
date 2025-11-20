package com.creditsimulator;

import com.creditsimulator.controller.CreditSimulatorController;
import com.creditsimulator.view.CreditSimulatorView;

public class CreditSimulatorApplication {
    public static void main(String[] args) {
        System.out.println("Credit Simulator Application Started");
        CreditSimulatorView view = new CreditSimulatorView();
        CreditSimulatorController controller = new CreditSimulatorController(view);

        controller.loadFromApi();
    }
}
