package org.emp.gl.clients;

import java.beans.PropertyChangeEvent;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

public class CompteARebours implements TimerChangeListener {

    String name;
    TimerService timerService;
    int compteur;

    public CompteARebours(String name, int valeurInitiale, TimerService timerService) {
        this.name = name;
        this.compteur = valeurInitiale;
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);
        System.out.println("CompteARebours " + name + " initialisé à " + compteur);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (SECONDE_PROP.equals(evt.getPropertyName())) {
            if (compteur > 0) {
                compteur--;
                System.out.println(name + " : " + compteur);

                if (compteur == 0) {
                    System.out.println(name + " : termine désinscription");
                    // PropertyChangeSupport gère la désinscription en toute sécurité
                    timerService.removeTimeChangeListener(this);
                }
            }
        }
    }
}