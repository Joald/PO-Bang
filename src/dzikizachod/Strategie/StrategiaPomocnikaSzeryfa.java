/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod.Strategie;

import dzikizachod.ListaGraczy;

/**
 *
 * @author joald_000
 */
public abstract class StrategiaPomocnikaSzeryfa extends Strategia {
    @Override
    public boolean zdecydujLeczenie(ListaGraczy listaGraczy, int numerGracza) {
        if(listaGraczy.lewySąsiad(numerGracza).toString().equals("Szeryf") && listaGraczy.lewySąsiad(numerGracza).ulecz()) {
            return true;
        }
        if(listaGraczy.prawySąsiad(numerGracza).toString().equals("Szeryf") && listaGraczy.prawySąsiad(numerGracza).ulecz()) {
            return true;
        }
        return listaGraczy.get(numerGracza).ulecz();
    }
}
