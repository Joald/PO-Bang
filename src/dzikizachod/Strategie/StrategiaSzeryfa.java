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
public abstract class StrategiaSzeryfa extends Strategia {

    @Override
    protected boolean rzućDynamit(ListaGraczy listaGraczy, int numerGracza) {
        return false;
    }
    
}
