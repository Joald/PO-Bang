/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author joald_000
 */
public class PulaAkcji {
    private final ArrayList<Akcja> pula;
    private final ArrayList<Akcja> deleted;

    
    public PulaAkcji() {
        pula = new ArrayList<>();
        deleted = new ArrayList<>();
    }
    
    public PulaAkcji kopiujPulę() {
        PulaAkcji kopia = new PulaAkcji();
        for(Akcja a : pula) {
            kopia.pula.add(a);
        }
        return kopia;
    }
    
    public void tasuj() {
        pula.addAll(deleted);
        deleted.clear();
        Collections.shuffle(pula);
    }
    
    public void dodaj(Akcja typ, int liczba) {
        for (int i = 0; i < liczba; ++i){
            pula.add(typ);
        }
        tasuj();
    }
    
    public Akcja losuj() {
        if(pula.isEmpty()) {
            tasuj();
        }
        return pula.remove(pula.size() - 1);
    }
    
    public void zwróć(Akcja zguba) {
        deleted.add(zguba);
    }
    public void zwróćListę(ArrayList<Akcja> zguby) {
        deleted.addAll(zguby);
    }
}
