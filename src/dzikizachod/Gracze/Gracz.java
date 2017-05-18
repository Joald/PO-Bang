/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod.Gracze;

import dzikizachod.Akcja;
import dzikizachod.Strategie.Strategia;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Random;



/**
 *
 * @author joald_000
 */
public abstract class Gracz {
    protected Strategia strategia;
    protected int punktyŻycia;
    protected boolean żyje;
    protected ArrayList<Akcja> zestaw;
    protected int ilośćZabójstw;
    protected int zasięg;
    
    protected static final int MAX_AKCJI_W_RĘKU = 5;
    
    Gracz(Strategia strategia) {
        this.strategia = strategia;
        ilośćZabójstw = 0;
        Random los = new Random();
        punktyŻycia = 3 + los.nextInt(2);
        zestaw = new ArrayList<>();
        żyje = true;
        zasięg = 1;
    }
    
    public Gracz klonujGracza() {
        Gracz nowyGracz;
        switch (toString()) {
            case "Szeryf":
                nowyGracz = new Szeryf(strategia);
                break;
            case "Bandyta":
                nowyGracz = new Bandyta(strategia);
                break;
            case "Pomocnik Szeryfa":
                nowyGracz = new PomocnikSzeryfa(strategia);
                break;
            default:
                nowyGracz = new Bandyta(strategia);
                assert(false);
                break;
        }
        
        return nowyGracz;
    }
    
    public boolean czyŻyje() {
        return żyje;
    }
    
    public int ilośćPunktówŻycia() {
        return punktyŻycia;
    }
    
    public void dodajZestaw(ArrayList<Akcja> nowyZestaw) {
        zestaw.addAll(nowyZestaw);
    }
    
    public int doDobrania() {
        return MAX_AKCJI_W_RĘKU - zestaw.size();
    }
    
    public void dodajZabójstwo() {
        ilośćZabójstw++;
    }
    public void drukujAkcje(String wcięcie) {
        wcięcie += "  ";
        System.out.println(wcięcie + "Akcje: [");
        String doDruku = "";
        for (Akcja x : zestaw) {
            doDruku += x.toString() + ", ";
        }
        System.out.println(doDruku.substring(0, doDruku.length() - 1));
    }
    
    public void wykonajTurę(ArrayList<Gracz> listaGraczy, String wcięcie, int numerGracza) {
        zestaw.sort(Enum::compareTo);
        for (Akcja a : zestaw) {
            strategia.zdecydujRuch(a, listaGraczy, numerGracza);
        }
    }
    
    public void zwiększZasięg(int zwiększenie) {
        zasięg += zwiększenie;
    }
}
