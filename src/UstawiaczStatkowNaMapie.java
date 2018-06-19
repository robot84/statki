class UstawiaczStatkowNaMapie {
    static final int MAX_PROB_USTAWIEN_STATKU=1000;

    static int ustawPrzypadkowoFlote(Statek[] flota, MapaOBSOLETE mapaOBSOLETE){
        if(flota.length != MapaOBSOLETE.ILOSC_STATKOW_NA_PLANSZY){
            Logger.fatal("UstawiaczStatkowNaMapie.ustawPrzypadkowo: flota.length != MapaOBSOLETE.ILOSC_STATKOW_NA_PLANSZY");
            System.exit(-1);
        }

        for(int i = 0;i<flota.length;i++){
            flota[i].recycle();
            for(int w=0;w<MAX_PROB_USTAWIEN_STATKU;w++)
                if ( (!flota[i].ustawLosowoPolozenieStatku(mapaOBSOLETE)) ) {
                    // co 10 nieudanych prob pisz blad (przy probach 9,19,29,...)
                    if(Logger.isDeepDebugEnabled() && w%100==99) System.out.println("DeepDebug: Proba "+w+" NIE MOZNA USTAWIC STATKU!");
                }
                else
                {
                    if (Logger.isDeepDebugEnabled()) System.out.println("DeepDebug: Ustawilem statek!");
                    break;
                }

            //	   statkiNaPlanszy[i].getAll();
            mapaOBSOLETE.dodajStatek(flota[i]);
            if (Logger.isDeepDebugEnabled()) mapaOBSOLETE.wyswietlMapePozycjiStatkow();
        }
        return 0;
    }
    static int ustawCentralnie(Statek[] flota, MapaOBSOLETE mapaOBSOLETE){
        return 0;
    }
    static int ustawNaObrzezach(Statek[] flota, MapaOBSOLETE mapaOBSOLETE){
        return 0;
    }
}
