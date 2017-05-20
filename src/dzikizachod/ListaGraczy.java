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
        for(int i = 0; i < this.size(); ++i) {
            if (this.get(i).toString().equals("Szeryf")) {
                numerSzeryfa = i;
                break;
            }
        }
    }
    
    public boolean szeryfNieŻyje() {
        return !get(numerSzeryfa).czyŻyje();
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
        int i = numerGracza;
        
        do {
            --i;
            if(i < 0) {
                i = size() - 1;
            }
        }
        while(!this.get(i).czyŻyje());
        return this.get(i);
    }
    
    public Gracz prawySąsiad(int numerGracza) {
        int i = numerGracza;
        
        do {
            i = (i + 1) % size();
        }
        while(!this.get(i).czyŻyje());
        return this.get(i);
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
        
        for (int i = 0; i < zasięg; ++i) {
            doZwrócenia.add(lewy);
            doZwrócenia.add(prawy);
            lewy = lewySąsiad(this.indexOf(lewy));
            prawy = prawySąsiad(this.indexOf(prawy));
        }
        
        return doZwrócenia;
    }
    
    public ArrayList<Gracz> graczePrzedSzeryfem(Gracz źródło, boolean lewo) {
        ArrayList<Gracz> doZwrócenia = new ArrayList<>();
        Gracz iter = sąsiad(źródło, lewo);
        int zasięg = źródło.getZasięg();
        int i = 1;
        while (iter != get(getNumerSzeryfa()) && i <= zasięg) {
            doZwrócenia.add(iter);
            iter = sąsiad(iter, lewo);
            ++i;
        }
        return doZwrócenia;
    }
    
    public ArrayList<Gracz> graczeWLewoPrzedSzeryfem(Gracz źródło) {
        return graczePrzedSzeryfem(źródło, true);
    }
    
    public ArrayList<Gracz> graczeWPrawoPrzedSzeryfem(Gracz źródło) {
        return graczePrzedSzeryfem(źródło, false);
    }

    public Gracz sąsiad(Gracz gracz, boolean lewo) {
        if(lewo) {
            return lewySąsiad(gracz);
        }
        return prawySąsiad(gracz);
    }
}
