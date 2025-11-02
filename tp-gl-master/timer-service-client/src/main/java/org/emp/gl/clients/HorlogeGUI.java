package org.emp.gl.clients;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

public class HorlogeGUI extends JFrame implements TimerChangeListener {

    private TimerService timerService;
    private JLabel heureLabel;
    private JLabel dateLabel;

    public HorlogeGUI(TimerService timerService) {
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);

        initUI();
    }

    private void initUI() {
        setTitle("Horloge");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 220);
        setLocationRelativeTo(null);

        // --- Panel principal avec dégradé ---
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color c1 = new Color(230, 240, 255);
                Color c2 = new Color(180, 200, 255);
                GradientPaint gp = new GradientPaint(0, 0, c1, 0, getHeight(), c2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());

        // --- Label pour l'heure ---
        heureLabel = new JLabel("", SwingConstants.CENTER);
        heureLabel.setFont(new Font("Segoe UI", Font.BOLD, 64));
        heureLabel.setForeground(new Color(20, 40, 120));

        // --- Label pour la date ---
        dateLabel = new JLabel("", SwingConstants.CENTER);
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        dateLabel.setForeground(new Color(60, 60, 90));

        // --- Bordures et marges ---
        heureLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        // --- Ajout des composants ---
        panel.add(heureLabel, BorderLayout.CENTER);
        panel.add(dateLabel, BorderLayout.SOUTH);

        add(panel);

        // --- Afficher l'heure initiale ---
        updateDisplay();
    }

    private void updateDisplay() {
        SwingUtilities.invokeLater(() -> {
            String heure = String.format("%02d:%02d:%02d",
                    timerService.getHeures(),
                    timerService.getMinutes(),
                    timerService.getSecondes());
            heureLabel.setText(heure);

            java.time.LocalDate date = java.time.LocalDate.now();
            dateLabel.setText(date.toString());
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (SECONDE_PROP.equals(evt.getPropertyName())) {
            updateDisplay();
        }
    }
}
