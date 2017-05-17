/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod.Strategie;

import dzikizachod.Akcja;
import dzikizachod.Gracze.Gracz;
import java.util.ArrayList;

/**
 *
 * @author joald_000
 */
public abstract class Strategia {
    public final void zdecydujRuch(Akcja ruch, ArrayList<Gracz> listaGraczy, int numerGracza) {
        switch(ruch) {
            case DYNAMIT:
                rzućDynamit(listaGraczy.get(numerGracza));
                break;
            case STRZEL:
                zdecydujStrzał(listaGraczy, numerGracza);
                break;
            case ULECZ:
                zdecydujLeczenie(listaGraczy, numerGracza);
                break;
            case ZASIEG_PLUS_JEDEN:
                zdecydujZasięgPlusJeden(listaGraczy.get(numerGracza));
                break;
            case ZASIEG_PLUS_DWA:
                zdecydujZasięgPlusDwa(listaGraczy.get(numerGracza));
                break;
            default:
                throw new AssertionError(ruch.name());
                
        }
    }
    
    private void zdecydujZasięgPlusJeden(Gracz sprawca) {
        sprawca.zwiększZasięg(1);
    }
    private void zdecydujZasięgPlusDwa(Gracz sprawca) {
        sprawca.zwiększZasięg(2);
    }
    protected abstract void zdecydujStrzał(ArrayList<Gracz> listaGraczy, int numerGracza);
    protected void zdecydujLeczenie(ArrayList<Gracz> listaGraczy, int numerGracza) {
        
    }
    private void rzućDynamit(Gracz sprawca) {
        
    }
}
