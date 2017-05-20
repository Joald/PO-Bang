/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod.Strategie;

import dzikizachod.Gracze.Gracz;
import dzikizachod.ListaGraczy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joald_000
 */
public class StrategiaBandytyCierpliwa extends StrategiaBandyty {

    @Override
    protected boolean zdecydujStrzał(ListaGraczy listaGraczy, int numerGracza) {
        Gracz gracz = listaGraczy.get(numerGracza);
        return gracz.strzelWZasięguTylko("Szeryf", listaGraczy);
    }
}
