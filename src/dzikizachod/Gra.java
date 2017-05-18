/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod;

import dzikizachod.Gracze.Gracz;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author joald_000
 */
public class Gra {
    Gra() {
        gracze = new ArrayList<>();
    }
    private final ArrayList<Gracz> gracze;
    private PulaAkcji pulaAkcji;
    private int numerSzeryfa;
    
    private boolean szeryfNieŻyje() {
        for (Gracz g : gracze) {
            if(g.toString().equals("Szeryf") && !g.czyŻyje()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean bandyciNieŻyją() {
        boolean nieŻyjąWszyscyBandyci = true;
        for (Gracz g : gracze) {
            if(g.toString().equals("Bandyta") && g.czyŻyje()) {
                nieŻyjąWszyscyBandyci = false;
            }
        }
        return nieŻyjąWszyscyBandyci;
    }
    
    private boolean koniecGry() {
        return bandyciNieŻyją() || szeryfNieŻyje();
    }
    private void drukujGracza(Gracz druk, int liczbaPorządkowa, String wcięcie) {
        if (druk.czyŻyje()) {
            System.out.println(wcięcie + liczbaPorządkowa + ": " + druk.toString() + " (liczba żyć: " + druk.ilośćPunktówŻycia() + ")");
        }
        else {
            System.out.println(wcięcie + liczbaPorządkowa + ": X (" + druk.toString() + ")");
        }
    }
    
    private void drukujListęGraczy(String wcięcie) {
        System.out.println(wcięcie + "Gracze:");
        wcięcie += "  ";
        int i = 1;
        for (Gracz g : gracze) {    
            drukujGracza(g, i, wcięcie);
            ++i;
        }    
    }
    
    private void drukujPoczątek() {
        System.out.println("** START");
        String wcięcie = "  ";
        drukujListęGraczy(wcięcie);
    }
    
    private void drukujTurę(int numerTury) {
        System.out.println("** TURA " + numerTury);
    }
    
    private void drukujKoniec() {
        System.out.println("** KONIEC");
        String wcięcie = "  ";
        if(!koniecGry()) {
            System.out.println(wcięcie + "REMIS - OSIĄGNIĘTO LIMIT TUR");
            return;
        }
        System.out.println(wcięcie + "WYGRANA STRONA: ");
        if (bandyciNieŻyją()) {
            System.out.println("szeryf i pomocnicy");
        }
        else {
            System.out.println("bandyci");
        }
    }
    
    private int numerGraczaNaLiście(int numer) {
        return (numer + numerSzeryfa) % gracze.size();
    }
    
    private boolean przeprowadźTurę(int numerTury) {
        drukujTurę(numerTury);
        String wcięcie = "  ";
        
        for (Gracz g : gracze) {
            ArrayList<Akcja> dobór = new ArrayList<>();
            for(int i = 0; i < g.doDobrania(); ++i) {
                dobór.add(pulaAkcji.losuj());
            }
            g.dodajZestaw(dobór);
        }
        
        for (int i = 0; i < gracze.size(); ++i) {
            System.out.println(wcięcie + "GRACZ " + (i + 1 + numerSzeryfa) + " (" + gracze.get(numerGraczaNaLiście(i)) + "): ");
            Gracz g = gracze.get(numerGraczaNaLiście(i));
            g.drukujAkcje(wcięcie);
            g.wykonajTurę(gracze, wcięcie, numerGraczaNaLiście(i));
            
            
        }
        
        return !koniecGry();
    }
    
    private void ustalNumerSzeryfa() {
        for(int i = 1; i <= gracze.size(); ++i) {
            if (gracze.get(i - 1).toString().equals("Szeryf")) {
                numerSzeryfa = i;
                break;
            }
        }
    }
    
    public void rozgrywka(List<Gracz> listaGraczy, PulaAkcji pulaAkcji) {
        
        for (Gracz i : listaGraczy) {
            gracze.add(i.klonujGracza());
        }
        Collections.shuffle(gracze);
        
        this.pulaAkcji = pulaAkcji.kopiujPulę();
        
        ustalNumerSzeryfa();
        
        drukujPoczątek();
        
        for(int i = 1; i <= 42; ++i) {
            if(!przeprowadźTurę(i)) {
                break;
            }
        }

        drukujKoniec();
    }
    
    public void rozgrywka(Gracz[] gracze, PulaAkcji pulaAkcji) {
        List<Gracz> lista = Arrays.asList(gracze);
        rozgrywka(lista, pulaAkcji);
    }
    
    
}
