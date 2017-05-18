/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod.Gracze;

import dzikizachod.Strategie.StrategiaBandytyDomyslna;
import dzikizachod.Strategie.Strategia;

/**
 *
 * @author joald_000
 */
public class Bandyta extends Gracz {

    public Bandyta() {
        this(new StrategiaBandytyDomyslna());
    }
    
    public Bandyta(Strategia strategia) {
        super(strategia);
    }
    
    @Override
    public String toString() {
        return "Bandyta";
    }
    
}
