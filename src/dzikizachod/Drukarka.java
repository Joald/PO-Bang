/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod;

import dzikizachod.Gracze.Gracz;
import java.util.ArrayList;

/**
 *
 * @author joald_000
 */
public class Drukarka {
    ArrayList<Gracz> gracze;
    PulaAkcji pulaAkcji;
    Drukarka(ArrayList<Gracz> gracze, PulaAkcji pulaAkcji) {
        this.pulaAkcji = pulaAkcji;
        this.gracze = gracze;
    }
    public void drukujGracza(Gracz druk, int liczbaPorządkowa, String wcięcie) {
        if (druk.czyŻyje()) {
            System.out.println(wcięcie + liczbaPorządkowa + ": " + druk + " (liczba żyć: " + druk.ilośćPunktówŻycia() + ")");
        }
        else {
            System.out.println(wcięcie + liczbaPorządkowa + ": X (" + druk + ")");
        }
    }
    
    public void drukujListęGraczy(String wcięcie) {
        System.out.println(wcięcie + "Gracze:");
        wcięcie += "  ";
        int i = 1;
        for (Gracz g : gracze) {    
            drukujGracza(g, i, wcięcie);
            ++i;
        }    
    }
    
    public void drukujPoczątek() {
        System.out.println("** START");
        String wcięcie = "  ";
        drukujListęGraczy(wcięcie);
    }
    
    public void drukujTurę(int numerTury) {
        System.out.println("** TURA " + numerTury);
    }
    
}
