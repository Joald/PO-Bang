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
        gracze = new ListaGraczy();
    }
    private final ListaGraczy gracze;
    private Drukarka drukarka;
    private PulaAkcji pulaAkcji;
    
    
    private boolean przeprowadźTurę(int numerTury) {
        drukarka.drukujTurę(numerTury);
        String wcięcie = "  ";
        
        for (Gracz g : gracze) {
            ArrayList<Akcja> dobór = new ArrayList<>();
            for(int i = 0; i < g.doDobrania(); ++i) {
                dobór.add(pulaAkcji.losuj());
            }
            g.dodajZestaw(dobór);
        }
        
        int i = gracze.getNumerSzeryfa();
        do {
            if (gracze.koniecGry()) {
                return false;
            }
            System.out.println(wcięcie + "GRACZ " + (i + 1) + " (" + gracze.get(i) + "): ");
            Gracz g = gracze.get(i);
            if(!g.czyŻyje()) {
                System.out.println(wcięcie + "  MARTWY");
                i = (i + 1) % gracze.size();
                continue;
            }
            g.drukujAkcje(wcięcie);
            g.wykonajTurę(gracze, wcięcie, i, pulaAkcji);
            if(g.czyŻyje()) {
                drukarka.drukujListęGraczy(wcięcie);
            }
            i = (i + 1) % gracze.size();
        }
        while (i != gracze.getNumerSzeryfa());
        
        return !gracze.koniecGry();
    }
    
    public void rozgrywka(List<Gracz> listaGraczy, PulaAkcji pulaAkcji) {
        
        for (Gracz i : listaGraczy) {
            gracze.add(i.klonujGracza());
        }
        
        Collections.shuffle(gracze);
        
        this.pulaAkcji = pulaAkcji.kopiujPulę();
        drukarka = new Drukarka(gracze, this.pulaAkcji);
        
        gracze.ustalNumerSzeryfa();
        
        drukarka.drukujPoczątek();
        
        for (int i = 1; i <= 42; ++i) {
            if(!przeprowadźTurę(i)) {
                break;
            }
        }

        drukarka.drukujKoniec();
    }
    
    public void rozgrywka(Gracz[] gracze, PulaAkcji pulaAkcji) {
        List<Gracz> lista = Arrays.asList(gracze);
        rozgrywka(lista, pulaAkcji);
    }
    
    
}
