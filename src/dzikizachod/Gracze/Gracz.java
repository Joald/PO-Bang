/*5
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dzikizachod.Gracze;

import dzikizachod.Akcja;
import dzikizachod.Kostka;
import dzikizachod.ListaGraczy;
import dzikizachod.PulaAkcji;
import dzikizachod.Strategie.Strategia;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;



/**
 *
 * @author joald_000
 */
public abstract class Gracz {
    protected Strategia strategia;
    protected int punktyŻycia;
    protected int maksymalnePunktyŻycia;
    private boolean dynamit;
    protected ArrayList<Akcja> zestaw;
    protected int ilośćZabójstw;
    protected int zasięg;
    private int wystrzeloneWTurze;
    private Set<Gracz> listaCelów;
    private Set<Gracz> listaZabójstw;
    protected static final int MAX_AKCJI_W_RĘKU = 5;
    
    Gracz(Strategia strategia) {
        this.strategia = strategia;
        ilośćZabójstw = 0;
        wystrzeloneWTurze = 0;
        Random los = new Random();
        punktyŻycia = 3 + los.nextInt(2);
        maksymalnePunktyŻycia = punktyŻycia;
        zestaw = new ArrayList<>();
        listaCelów = new HashSet<>();
        listaZabójstw = new HashSet<>();
        dynamit = false;
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
        return punktyŻycia > 0;
    }
    
    public int getZasięg() {
        return zasięg;
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
        
        String doDruku = wcięcie + "Akcje: [";
        for (Akcja x : zestaw) {
            doDruku += x.toString() + ", ";
        }
        System.out.println(doDruku.substring(0, doDruku.length() - 2) + "]");
    }
    
    public void otrzymajObrażenia(int ilość) {
        punktyŻycia -= ilość;
    }
    
    public boolean dynamitWybuchł() {
        return Kostka.rzuć() == 1;
    }
    
    public void wykonajTurę(ListaGraczy listaGraczy, String wcięcie, int numerGracza, PulaAkcji pulaAkcji) {
        if(dynamit) {
            String dynamit = wcięcie + "Dynamit: ";
            
            if(dynamitWybuchł()) {
                otrzymajObrażenia(3);
                dynamit += "WYBUCHŁ";
                
            }
            else {
                dynamit += "PRZECHODZI DALEJ";
                strategia.zdecydujRuch(Akcja.DYNAMIT, listaGraczy, numerGracza);
            }
            System.out.println(dynamit);
            
            if (!czyŻyje()) {
                System.out.println(wcięcie + "Ruchy:");
                wcięcie += "  ";
                System.out.println(wcięcie + "MARTWY");
                return;
            }
        }
        System.out.println(wcięcie + "Ruchy :");
        zestaw.sort(Enum::compareTo);
        ArrayList<Boolean> wykorzystania = new ArrayList<>();
        for (Akcja a : zestaw) {
            if(a.equals(Akcja.STRZEL) && ilośćStrzałów() == 0) {
                wykorzystania.add(true);
                continue;
            }
            wykorzystania.add(strategia.zdecydujRuch(a, listaGraczy, numerGracza));
        }
        wystrzeloneWTurze = 0;
        for (int i = wykorzystania.size() - 1; i >= 0; --i) {
            if(wykorzystania.get(i)) {
                if (!zestaw.get(i).equals(Akcja.DYNAMIT)) {
                    pulaAkcji.zwróć(zestaw.get(i));
                }
                zestaw.remove(i);
            }
        }
    }
    
    public void zwiększZasięg(int zwiększenie) {
        zasięg += zwiększenie;
    }
    
    public void otrzymajDynamit() {
        dynamit = true;
    }
    
    public boolean ulecz() {
        if(punktyŻycia < maksymalnePunktyŻycia) {
            punktyŻycia++;
            return true;
        }
        return false;
    }
    
    public void strzel(Gracz cel) {
        cel.otrzymajObrażenia(1);
        listaCelów.add(cel);
        wystrzeloneWTurze++;
        if(!cel.czyŻyje()) {
            this.ilośćZabójstw++;
            this.listaZabójstw.add(cel);
        }
    }
    
    public int ilośćStrzałów() {
        int doZwrócenia = 0;
        for(Akcja i : zestaw) {
            if(i.equals(Akcja.STRZEL)) {
                doZwrócenia++;
            }
        }
        return doZwrócenia - wystrzeloneWTurze;
    }
    
    private boolean strzelWZasięgu(String wyjątek, ListaGraczy listaGraczy, boolean czyDobrze) {
        ArrayList<Gracz> graczeWZasięgu = listaGraczy.graczeWZasięgu(this);
        Gracz szeryf = listaGraczy.get(listaGraczy.getNumerSzeryfa());
        
        if (graczeWZasięgu.contains(szeryf) && !wyjątek.equals("Szeryf")) {
            this.strzel(szeryf);
            return true;
        }
        
        List<Gracz> możliweCele;
        możliweCele = graczeWZasięgu.stream()
                .filter(gracz -> gracz.toString().equals(wyjątek) == czyDobrze)
                .collect(Collectors.toList());
        if(możliweCele.isEmpty()) {
            return false;
        }
        Random random = new Random();
        Gracz cel = możliweCele.get(random.nextInt(możliweCele.size()));
        this.strzel(cel);
        return true;
    }
    
    public boolean strzelWZasięguTylko(String wzór, ListaGraczy listaGraczy) {
        return this.strzelWZasięgu(wzór, listaGraczy, true);
    }
    
    ///Jeśli szeryf nie jest wyjątkiem, jest priorytetem.
    public boolean strzelWZasięguOprócz(String wyjątek, ListaGraczy listaGraczy) {
        return this.strzelWZasięgu(wyjątek, listaGraczy, false);
    }
    
    public boolean strzelDoPodejrzanych(ListaGraczy listaGraczy) {
        ArrayList<Gracz> graczeWZasięgu = listaGraczy.graczeWZasięgu(this);
        List<Gracz> możliweCele;
        możliweCele = graczeWZasięgu.stream()
                .filter(gracz -> gracz.czyPodejrzany())
                .collect(Collectors.toList());
        if(możliweCele.isEmpty()) {
            return false;
        }
        Random random = new Random();
        Gracz cel = możliweCele.get(random.nextInt(możliweCele.size()));
        this.strzel(cel);
        return true;
    }
    
    public boolean czyStrzeliłDoSzeryfa() {
        for(Gracz i : listaCelów) {
            if (i.toString().equals("Szeryf")) {
                return true;
            }
        }
        return false;
    }
    
    public boolean czyPodejrzany() {
        if(toString().equals("Szeryf")) {
            return false;
        }
        if(this.czyStrzeliłDoSzeryfa()) {
            return true;
        }
        int liczbaZabitychPomocników = 0;
        int liczbaZabitychBandytów = 0;
        for (Gracz i : listaZabójstw) {
            switch(i.toString()) {
                case "Bandyta":
                    liczbaZabitychBandytów++;
                    break;
                case "Pomocnik Szeryfa":
                    liczbaZabitychPomocników++;
                    break;
                default:
                    break;
            }
        }
        return liczbaZabitychPomocników > liczbaZabitychBandytów;
    }
    
    public void zabijNajsłabszegoLewegoPomocnika(ListaGraczy listaGraczy) {
        Gracz iter = listaGraczy.lewySąsiad(this);
        Gracz najsłabszy = new Szeryf();
        najsłabszy.punktyŻycia = Integer.MAX_VALUE;
        while (iter != listaGraczy.get(listaGraczy.getNumerSzeryfa())) {
            if(iter.toString().equals("Pomocnik Szeryfa") && najsłabszy.ilośćPunktówŻycia() > iter.ilośćPunktówŻycia()) {
                najsłabszy = iter;
            }
            iter = listaGraczy.lewySąsiad(iter);
        }
        for (int i = 0; i < najsłabszy.ilośćPunktówŻycia(); ++i) {
            this.strzel(najsłabszy);
        }
    }
    
    public void zabijNajsłabszegoLewegoBandytę(ListaGraczy listaGraczy) {
        Gracz iter = listaGraczy.lewySąsiad(this);
        Gracz najsłabszy = new Szeryf();
        najsłabszy.punktyŻycia = Integer.MAX_VALUE;
        while (iter != listaGraczy.get(listaGraczy.getNumerSzeryfa())) {
            if(iter.toString().equals("Bandyta") && najsłabszy.ilośćPunktówŻycia() > iter.ilośćPunktówŻycia()) {
                najsłabszy = iter;
            }
            iter = listaGraczy.lewySąsiad(iter);
        }
        for (int i = 0; i < najsłabszy.ilośćPunktówŻycia(); ++i) {
            this.strzel(najsłabszy);
        }
    }
    
    public void zabijNajsłabszegoPrawegoPomocnika(ListaGraczy listaGraczy) {
        Gracz iter = listaGraczy.lewySąsiad(this);
        Gracz najsłabszy = new Szeryf();
        najsłabszy.punktyŻycia = Integer.MAX_VALUE;
        while (iter != listaGraczy.get(listaGraczy.getNumerSzeryfa())) {
            if(iter.toString().equals("Pomocnik Szeryfa") && najsłabszy.ilośćPunktówŻycia() > iter.ilośćPunktówŻycia()) {
                najsłabszy = iter;
            }
            iter = listaGraczy.prawySąsiad(iter);
        }
        for (int i = 0; i < najsłabszy.ilośćPunktówŻycia(); ++i) {
            this.strzel(najsłabszy);
        }
    }
    
    public void zabijNajsłabszegoPrawegoBandytę(ListaGraczy listaGraczy) {
        Gracz iter = listaGraczy.lewySąsiad(this);
        Gracz najsłabszy = new Szeryf();
        najsłabszy.punktyŻycia = Integer.MAX_VALUE;
        while (iter != listaGraczy.get(listaGraczy.getNumerSzeryfa())) {
            if(iter.toString().equals("Bandyta") && najsłabszy.ilośćPunktówŻycia() > iter.ilośćPunktówŻycia()) {
                najsłabszy = iter;
            }
            iter = listaGraczy.prawySąsiad(iter);
        }
        for (int i = 0; i < najsłabszy.ilośćPunktówŻycia(); ++i) {
            this.strzel(najsłabszy);
        }
    }
}
