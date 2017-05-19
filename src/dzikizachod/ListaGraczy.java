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
public class ListaGraczy extends ArrayList<Gracz> {
    private int numerSzeryfa;
    
    ListaGraczy() {
        super();
    }
    
    public int getNumerSzeryfa() {
        return numerSzeryfa;
    }
    
    public void ustalNumerSzeryfa() {
        for(int i = 1; i <= this.size(); ++i) {
            if (this.get(i - 1).toString().equals("Szeryf")) {
                numerSzeryfa = i;
                break;
            }
        }
    }
    
    public boolean szeryfNieŻyje() {
        for (Gracz g : this) {
            if(g.toString().equals("Szeryf") && !g.czyŻyje()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean bandyciNieŻyją() {
        boolean nieŻyjąWszyscyBandyci = true;
        for (Gracz g : this) {
            if(g.toString().equals("Bandyta") && g.czyŻyje()) {
                nieŻyjąWszyscyBandyci = false;
            }
        }
        return nieŻyjąWszyscyBandyci;
    }
    
    public boolean koniecGry() {
        return bandyciNieŻyją() || szeryfNieŻyje();
    }
    
    public Gracz lewySąsiad(int numerGracza) {
        Gracz doZwrócenia;
        int i;
        
        if(numerGracza == 0) {
            i = this.size() - 1;
            
        }
        else {
            i = numerGracza - 1;
        }
        do {
            doZwrócenia = this.get(i);
            --i;
        }
        while(!this.get(i).czyŻyje());
        return doZwrócenia;
    }
    
    public Gracz prawySąsiad(int numerGracza) {
        Gracz doZwrócenia;
        int i;
        
        if(numerGracza == this.size() - 1) {
            i = 0;
        }
        else {
            i = numerGracza + 1;
        }
        do {
            doZwrócenia = this.get(i);
            ++i;
        }
        while(!this.get(i).czyŻyje());
        return doZwrócenia;
    }
    
    public Gracz lewySąsiad(Gracz gracz) {
        return lewySąsiad(this.indexOf(gracz));
    }
    
    public Gracz prawySąsiad(Gracz gracz) {
        return prawySąsiad(this.indexOf(gracz));
    }
    
    public ArrayList<Gracz> graczeWZasięgu(int numerGracza) {
        Gracz źródło = this.get(numerGracza);
        return graczeWZasięgu(źródło);
    }
    public ArrayList<Gracz> graczeWZasięgu(Gracz źródło) {
        ArrayList<Gracz> doZwrócenia = new ArrayList<>();
        
        int zasięg = źródło.getZasięg();
        Gracz lewy = lewySąsiad(źródło);
        Gracz prawy = prawySąsiad(źródło);
        
        while (zasięg > 0) {
            doZwrócenia.add(lewy);
            doZwrócenia.add(prawy);
            lewy = lewySąsiad(this.indexOf(lewy));
            prawy = prawySąsiad(this.indexOf(prawy));
            --zasięg;
        }
        return doZwrócenia;
    }
    
    public ArrayList<Gracz> graczeWLewoPrzedSzeryfem(Gracz źródło) {
        ArrayList<Gracz> doZwrócenia = new ArrayList<>();
        Gracz iter = lewySąsiad(źródło);
        while (iter != get(getNumerSzeryfa())) {
            doZwrócenia.add(iter);
            iter = lewySąsiad(iter);
        }
        return doZwrócenia;
    }
    
    public ArrayList<Gracz> graczeWPrawoPrzedSzeryfem(Gracz źródło) {
        ArrayList<Gracz> doZwrócenia = new ArrayList<>();
        Gracz iter = prawySąsiad(źródło);
        while (iter != get(getNumerSzeryfa())) {
            doZwrócenia.add(iter);
            iter = prawySąsiad(iter);
        }
        return doZwrócenia;
    }
}
