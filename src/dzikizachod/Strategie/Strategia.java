/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod.Strategie;

import dzikizachod.Akcja;
import dzikizachod.Gracze.Gracz;
import dzikizachod.ListaGraczy;
import java.util.List;

/**
 *
 * @author joald_000
 */
public abstract class Strategia {
    public final boolean zdecydujRuch(Akcja ruch, ListaGraczy listaGraczy, int numerGracza) {
        switch(ruch) {
            case DYNAMIT:
                return rzućDynamit(listaGraczy, numerGracza);
            case STRZEL:
                return zdecydujStrzał(listaGraczy, numerGracza);
            case ULECZ:
                return zdecydujLeczenie(listaGraczy, numerGracza);
            case ZASIEG_PLUS_JEDEN:
                return zdecydujZasięgPlusJeden(listaGraczy.get(numerGracza));
            case ZASIEG_PLUS_DWA:
                return zdecydujZasięgPlusDwa(listaGraczy.get(numerGracza));
            default:
                throw new AssertionError(ruch.name());
                
        }
    }
    
    private boolean zdecydujZasięgPlusJeden(Gracz sprawca) {
        sprawca.zwiększZasięg(1);
        System.out.println("      ZASIEG_PLUS_JEDEN");
        return true;
    }
    private boolean zdecydujZasięgPlusDwa(Gracz sprawca) {
        sprawca.zwiększZasięg(2);
        System.out.println("      ZASIEG_PLUS_DWA");
        return true;
    }
    protected abstract boolean zdecydujStrzał(ListaGraczy listaGraczy, int numerGracza);
    protected boolean zdecydujLeczenie(ListaGraczy listaGraczy, int numerGracza) {
        boolean leczenie = listaGraczy.get(numerGracza).ulecz();
        if(leczenie) {
            System.out.println("      ULECZ");
        }
        return leczenie;
    }
    protected abstract boolean rzućDynamit(ListaGraczy listaGraczy, int numerGracza);
}
