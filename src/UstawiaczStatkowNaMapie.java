public class UstawiaczStatkowNaMapie {
    final static int MAX_PROB_USTAWIEN_STATKU=1000;

    static int ustawPrzypadkowoFlote(Statek[] flota, Mapa mapa){
        if(flota.length != Mapa.ILOSC_STATKOW_NA_PLANSZY){
            Logger.fatal("UstawiaczStatkowNaMapie.ustawPrzypadkowo: flota.length != Mapa.ILOSC_STATKOW_NA_PLANSZY");
            System.exit(-1);
        }

        for(int i = 0;i<flota.length;i++){
            flota[i].przygotujDoPonownegoUstawienia();
            for(int w=0;w<MAX_PROB_USTAWIEN_STATKU;w++)
                if ( (!flota[i].ustawLosowoPolozenieStatku(mapa)) ) {
                    // co 10 nieudanych prob pisz blad (przy probach 9,19,29,...)
                    if(Logger.getDeepDebugEnabled() && w%100==99) System.out.println("DeepDebug: Proba "+w+" NIE MOZNA USTAWIC STATKU!");
                }
                else
                {
                    if (Logger.getDeepDebugEnabled()) System.out.println("DeepDebug: Ustawilem statek!");
                    break;
                }

            //	   statkiNaPlanszy[i].getAll();
            mapa.dodajStatek(flota[i]);
            if (Logger.getDeepDebugEnabled()) mapa.wyswietlMapePozycjiStatkow();
        }
        return 0;
    }
    static int ustawCentralnie(Statek[] flota, Mapa mapa){
        return 0;
    }
    static int ustawNaObrzezach(Statek[] flota, Mapa mapa){
        return 0;
    }
}
