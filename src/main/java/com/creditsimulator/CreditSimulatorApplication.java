package com.creditsimulator;

import com.creditsimulator.controller.CreditSimulatorController;
import com.creditsimulator.view.CreditSimulatorView;

public class CreditSimulatorApplication {
    public static void main(String[] args) {
        CreditSimulatorView view = new CreditSimulatorView();
        CreditSimulatorController controller = new CreditSimulatorController(view);

        if (args.length > 0) {
            controller.processFileInput(args[0]);
        } else {
            controller.showMainMenu();
        }
    }
}
