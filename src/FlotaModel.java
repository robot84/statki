import java.util.ArrayList;

 class FlotaModel {
     enum Kierunek {
         PION,
         POZIOM
     }
     private static final  int MAX_PROB_USTAWIEN_STATKU=1000;
     MapaModel mapa;
    ArrayList<Statek> flota= new ArrayList<>();

    FlotaModel(MapaModel mapa){
        this.mapa=mapa;
    }

    void utworzFlote(FabrykaStatkow fabrykaStatkow){
        Statek []flota=fabrykaStatkow.utworzFloteStatkow(1,2,
                3,4);
        for(int i=0;i<flota.length;i++){
            this.flota.add(flota[i]);
        }

    }

    void ustawStatki() {


       /* for (Statek statek : flota) {
            statek.recycle();
            for (int w = 0; w < MAX_PROB_USTAWIEN_STATKU; w++)
                if ((!udaloSieUstawicLosowoPolozenieStatku(statek))) {
                    // co 10 nieudanych prob pisz blad (przy probach 9,19,29,...)
                    if (w % 100 == 99)
                        Logger.deepDebug("Proba " + w + " NIE MOZNA USTAWIC STATKU!");
                } else {
                    Logger.deepDebug("Ustawilem statek!");
                    break;
                }
            mapa.dodajStatek(statek);
        }

        if (Logger.isDeepDebugEnabled()) {
            System.out.println("Statkow na planszy: "+flota.size());
            for (Statek statek : flota)
                System.out.println(statek);

        }
*/    }

     /* zwraca true, jezeli udalo sie ustawic statek
           jezeli sie nie udalo, to false.
           moze tez sie zawiesic, czytaj komentarz z stawiaj1maszt()
      */
    /* boolean udaloSieUstawicLosowoPolozenieStatku(Statek statek){

         int x=(int)(Math.random()*(mapa.numberOfFieldsInXdirection()));
         int y=(int)(Math.random()*(mapa.numberOfFieldsInYdirection()));
         //int pionCzyPoziom=(int)(Math.random()*2);

         Kierunek pionCzyPoziom;
         if(((int)(Math.random()*2))>=1)
             pionCzyPoziom=Kierunek.POZIOM;
         else
             pionCzyPoziom=Kierunek.PION;


             if (pionCzyPoziom==Kierunek.PION)
                 Logger.deepDebug("(x,y) ("+x+","+y+")"+" pionowo");
             else
                 Logger.deepDebug("(x,y) ("+x+","+y+")"+" poziomo");


         if (pionCzyPoziom==Kierunek.PION){
             // 0 oznacza pion
             boolean moznaPostawic=true;
             for (int i=0;i<statek.getIloscMasztow();i++) moznaPostawic&=mapa.moznaPostawicStatek(y+i,x);
             if (moznaPostawic) {
                 // stawiamy statek, skoro sie da
                 for (int i=0;i<statek.getIloscMasztow();i++){
                     pozycjaStatkuMaszt[i].x=x;
                     pozycjaStatkuMaszt[i].y=y+i;
                 }
                 return true;
             }
             else return false;
         }
         else
         {
             // 1 oznacza poziom
             boolean moznaPostawic=true;
             for (int i=0;i<iloscMasztow;i++) moznaPostawic&=mapa.moznaPostawicStatek(y,x+i);
             if (moznaPostawic) {
                 // stawiamy statek, skoro sie da
                 for (int i=0;i<iloscMasztow;i++){
                     pozycjaStatkuMaszt[i].x=x+i;
                     pozycjaStatkuMaszt[i].y=y;
                 }
                 return true;
             }
             else return false;
         } // end of if

     } // end of method
     void umiescStatekNaMapie(Statek statek){
         Pole[] pozycja=statek.getPozycja();
         for (Pole pp:pozycja) {
             mapaPozycjiStatkow[pp.getY()][pp.getX()]=true;
             // w komentarzach przygotowana wersja zastepcza, gdyby obecna walila wyjatkami NullPointerException
             //for(int i=0;i<statek.getIloscMasztow;i++){
//		pozycja[i].getY();
//		Pole pozycjaA=statek.getPozycja(i);
             //mapaPozycjiStatkow[pozycja[i].getY()][pozycja[i].getX()]=true;
         }

     }
*/}
