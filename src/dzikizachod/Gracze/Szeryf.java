/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod.Gracze;

import dzikizachod.Strategie.Strategia;
import dzikizachod.Strategie.StrategiaSzeryfaDomyslna;

/**
 *
 * @author joald_000
 */
public class Szeryf extends Gracz {
    
    public Szeryf() {
        this(new StrategiaSzeryfaDomyslna());
    }
    public Szeryf(Strategia strategia) {
        super(strategia);
        punktyŻycia = 5;
        maksymalnePunktyŻycia = 5;
    }
    
    @Override
    public String toString() {
        return "Szeryf";
    }
    
}
