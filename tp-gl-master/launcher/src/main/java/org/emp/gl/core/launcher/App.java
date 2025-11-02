package org.emp.gl.core.launcher;

import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.HorlogeGUI;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

import javax.swing.*;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        testDuTimeService();
    }


    private static void testDuTimeService() {
        TimerService timerService = new DummyTimeServiceImpl();

        // Lancer l'interface graphique
        SwingUtilities.invokeLater(() -> {
            HorlogeGUI gui = new HorlogeGUI(timerService);
            gui.setVisible(true);
        });
    }


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}