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
public class StrategiaSzeryfaDomyslna extends StrategiaSzeryfa {

    @Override
    protected boolean zdecydujStrzał(ListaGraczy listaGraczy, int numerGracza) {
        Gracz gracz = listaGraczy.get(numerGracza);
        ArrayList<Gracz> graczeWZasięgu = listaGraczy.graczeWZasięgu(gracz);
        List<Gracz> możliweCele;
        możliweCele = graczeWZasięgu.stream()
                .filter(g -> g.czyStrzeliłDoSzeryfa())
                .collect(Collectors.toList());
        if(możliweCele.isEmpty()) {
            return gracz.strzelWZasięguOprócz("Szeryf", listaGraczy);
        }
        Random random = new Random();
        Gracz cel = możliweCele.get(random.nextInt(możliweCele.size()));
        gracz.strzel(cel);
        return true;
        
    }
    
}
