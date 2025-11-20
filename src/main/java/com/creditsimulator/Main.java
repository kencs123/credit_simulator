package com.creditsimulator;

import com.creditsimulator.controller.CreditSimulatorController;
import com.creditsimulator.view.CreditSimulatorView;

public class Main {
    public static void main(String[] args) {
        CreditSimulatorView view = new CreditSimulatorView();
        CreditSimulatorController controller = new CreditSimulatorController(view);

        controller.loadFromApi();
    }


}
