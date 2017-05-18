/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod.Gracze;

import dzikizachod.Strategie.StrategiaPomocnikaSzeryfaDomyslna;
import dzikizachod.Strategie.Strategia;

/**
 *
 * @author joald_000
 */
public class PomocnikSzeryfa extends Gracz {

    public PomocnikSzeryfa() {
        this(new StrategiaPomocnikaSzeryfaDomyslna());
    }
    public PomocnikSzeryfa(Strategia strategia) {
        super(strategia);
    }
    
    @Override
    public String toString() {
        return "Pomocnik Szeryfa";
    }
    
}
