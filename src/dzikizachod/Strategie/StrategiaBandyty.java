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
public abstract class StrategiaBandyty extends Strategia {
    @Override
    protected boolean rzućDynamit(ListaGraczy listaGraczy, int numerGracza) {
        Gracz gracz = listaGraczy.get(numerGracza);
        if(listaGraczy.graczeWPrawoPrzedSzeryfem(gracz).size() < 3) {
            listaGraczy.prawySąsiad(gracz).otrzymajDynamit();
            System.out.println("      DYNAMIT");
            return true;
        }
        return false;
    }
    
}
