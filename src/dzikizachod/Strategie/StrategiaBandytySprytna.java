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
public class StrategiaBandytySprytna extends StrategiaBandyty {

    @Override
    protected boolean zdecydujStrzał(ListaGraczy listaGraczy, int numerGracza) {
        Gracz gracz = listaGraczy.get(numerGracza);
        if(gracz.strzelWZasięguTylko("Szeryf", listaGraczy)) {
            return true;
        }
        int strzały = gracz.ilośćStrzałów();
        ArrayList<Gracz> lewo = listaGraczy.graczeWLewoPrzedSzeryfem(gracz);
        ArrayList<Gracz> prawo = listaGraczy.graczeWPrawoPrzedSzeryfem(gracz);
        int najsłabszyLewyPomocnik = Integer.MAX_VALUE;
        int najsłabszyLewyBandyta = Integer.MAX_VALUE;
        int najsłabszyPrawyPomocnik = Integer.MAX_VALUE;
        int najsłabszyPrawyBandyta = Integer.MAX_VALUE;
        for(Gracz i : lewo) {
            if(i.toString().equals("Pomocnik Szeryfa")) {
                najsłabszyLewyPomocnik = Integer.max(najsłabszyLewyPomocnik, i.ilośćPunktówŻycia());
            }
            if(i.toString().equals("Bandyta")) {
                najsłabszyLewyBandyta = Integer.max(najsłabszyLewyBandyta, i.ilośćPunktówŻycia());
            }
        }
        
        for(Gracz i : prawo) {
            if(i.toString().equals("Pomocnik Szeryfa")) {
                najsłabszyPrawyPomocnik = Integer.max(najsłabszyPrawyPomocnik, i.ilośćPunktówŻycia());
            }
            if(i.toString().equals("Bandyta")) {
                najsłabszyPrawyBandyta = Integer.max(najsłabszyPrawyBandyta, i.ilośćPunktówŻycia());
            }
        }
        int lewi = najsłabszyLewyBandyta + najsłabszyLewyPomocnik;
        int prawi = najsłabszyPrawyBandyta + najsłabszyPrawyPomocnik;
        if(lewi < prawi && lewi <= strzały) {
            gracz.zabijNajsłabszegoLewegoPomocnika(listaGraczy);
            gracz.zabijNajsłabszegoLewegoBandytę(listaGraczy);
            return true;
        }
        if(prawi < lewi && prawi <= strzały) {
            gracz.zabijNajsłabszegoPrawegoBandytę(listaGraczy);
            gracz.zabijNajsłabszegoPrawegoBandytę(listaGraczy);
            return true;
        }
        return false;
    }
    
}
