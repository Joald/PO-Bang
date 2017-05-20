/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod.Strategie;

import dzikizachod.Gracze.Gracz;
import dzikizachod.ListaGraczy;

/**
 *
 * @author joald_000
 */
public class StrategiaBandytyDomyslna extends StrategiaBandyty {

    @Override
    protected boolean zdecydujStrzał(ListaGraczy listaGraczy, int numerGracza) {
        Gracz strzelający = listaGraczy.get(numerGracza);
        return strzelający.strzelWZasięguTylko("Szeryf", listaGraczy) || 
               strzelający.strzelWZasięguOprócz("Bandyta", listaGraczy);
    }
    
}
