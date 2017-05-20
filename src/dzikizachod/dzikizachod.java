/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod;

import dzikizachod.Gracze.*;
import dzikizachod.Strategie.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joald_000
 */
public class dzikizachod {

    
    public static void main(String[] args) {
        Strategia sz = new StrategiaSzeryfaZliczajaca();
        Strategia pz = new StrategiaPomocnikaSzeryfaZliczajaca();
        Strategia bs = new StrategiaBandytySprytna();
        Strategia bc = new StrategiaBandytyCierpliwa();
        
        
        
        List<Gracz> gracze = new ArrayList<>();
        gracze.add(new Szeryf(sz));
        for(int i=0;i<10;i++) gracze.add(new PomocnikSzeryfa(pz));
        for(int i=0;i<10;i++) gracze.add(new Bandyta(bs));

        // Kod wspólny dla obu wersji:
        PulaAkcji pulaAkcji = new PulaAkcji();
        pulaAkcji.dodaj(Akcja.ULECZ, 50);
        pulaAkcji.dodaj(Akcja.STRZEL, 600);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_JEDEN, 30);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_DWA, 10);
        pulaAkcji.dodaj(Akcja.DYNAMIT, 1);

        Gra gra = new Gra();
        gra.rozgrywka(gracze, pulaAkcji);
    }
    
}
