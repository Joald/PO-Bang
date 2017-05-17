/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod;

import dzikizachod.*;
import dzikizachod.Gracze.Bandyta;
import dzikizachod.Gracze.Gracz;
import dzikizachod.Gracze.PomocnikSzeryfa;
import dzikizachod.Gracze.Szeryf;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joald_000
 */
public class dzikizachod {

    
    public static void main(String[] args) {
        List<Gracz> gracze = new ArrayList<>();
        gracze.add(new Szeryf());
        for(int i=0;i<2;i++) gracze.add(new PomocnikSzeryfa());
        for(int i=0;i<3;i++) gracze.add(new Bandyta());

        // Kod wspólny dla obu wersji:
        PulaAkcji pulaAkcji = new PulaAkcji();
        pulaAkcji.dodaj(Akcja.ULECZ, 20);
        pulaAkcji.dodaj(Akcja.STRZEL, 60);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_JEDEN, 3);
        pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_DWA, 1);
        pulaAkcji.dodaj(Akcja.DYNAMIT, 1);

        Gra gra = new Gra();
        gra.rozgrywka(gracze, pulaAkcji);
    }
    
}
