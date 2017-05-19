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
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author joald_000
 */
public class StrategiaBandytyDomyslna extends StrategiaBandyty {

    @Override
    protected boolean zdecydujStrzał(ListaGraczy listaGraczy, int numerGracza) {
        Gracz strzelający = listaGraczy.get(numerGracza);
        return strzelający.strzelWZasięguOprócz("Bandyta", listaGraczy);
    }
    
}
