/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod.Strategie;

import dzikizachod.Gracze.Gracz;
import dzikizachod.ListaGraczy;
import java.util.ArrayList;

/**
 *
 * @author joald_000
 */
public abstract class StrategiaPomocnikaSzeryfa extends Strategia {
    @Override
    public boolean zdecydujLeczenie(ListaGraczy listaGraczy, int numerGracza) {
        if(listaGraczy.lewySąsiad(numerGracza).toString().equals("Szeryf") && listaGraczy.lewySąsiad(numerGracza).ulecz()) {
            System.out.println("      ULECZ " + (listaGraczy.getNumerSzeryfa() + 1));
            return true;
        }
        if(listaGraczy.prawySąsiad(numerGracza).toString().equals("Szeryf") && listaGraczy.prawySąsiad(numerGracza).ulecz()) {
            System.out.println("      ULECZ " + (listaGraczy.getNumerSzeryfa() + 1));
            return true;
        }
        boolean leczenie = listaGraczy.get(numerGracza).ulecz();
        if(leczenie) {
            System.out.println("      ULECZ");
        }
        return leczenie;
    }
    
    @Override
    protected boolean rzućDynamit(ListaGraczy listaGraczy, int numerGracza) {
        Gracz gracz = listaGraczy.get(numerGracza);
        ArrayList<Gracz> graczePrzedSzeryfem = listaGraczy.graczeWPrawoPrzedSzeryfem(gracz);
        if (graczePrzedSzeryfem.size() <= 3) {
            return false;
        }
        int podejrzani = 0;
        for (Gracz i : graczePrzedSzeryfem) {
            if(i.czyPodejrzany()) {
                podejrzani++;
            }
        }
        if(podejrzani >= graczePrzedSzeryfem.size() * 2 / 3) {
            listaGraczy.prawySąsiad(gracz).otrzymajDynamit();
            System.out.println("      DYNAMIT");
            return true;
        }
        return false;
    }
}
