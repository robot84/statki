class MapaModel {

    /* Pole.java SHIP_SHOOTED ogranicza max wartosci  PLANSZA_MAX_X i  PLANSZA_MAX_Y!! */
    private   int PLANSZA_MAX_X = 10;
    private   int PLANSZA_MAX_Y = 10;

    private static final char  COVERED_MAP_FIELD= '.';

    private  char[][] mapaStrzalow;

MapaModel(){

}
    MapaModel(int mapWidth,int mapHeight){
        PLANSZA_MAX_X=mapWidth;
        PLANSZA_MAX_Y=mapHeight;
    }

    int numberOfFieldsInXdirection(){
        return PLANSZA_MAX_X;
    }

    int numberOfFieldsInYdirection(){
        return PLANSZA_MAX_Y;
    }

    void inicjujMapeStrzalow() {
    // TODO to jest combo - zawiera info o stanie mapy ale rowniez graficzna reprezentacje dla TextMode
        // trza te funkcjonalnosci rozdzielic
     mapaStrzalow= new char[PLANSZA_MAX_Y][PLANSZA_MAX_X];
        int x;
        int y;
        for(y=(PLANSZA_MAX_Y-1);y>=0;y--){
            for(x=0;x<PLANSZA_MAX_X;x++) mapaStrzalow[y][x]=COVERED_MAP_FIELD;
        }

    }

   /* void przygotujDoPonownegoUzycia(){
        if (Logger.isDebugEnabled()) System.out.println("Debug: metoda przygotujDoPonownegoUzycia()");
        iloscWyswietlen=0;
        for(int y=0;y<PLANSZA_MAX_Y;y++)
            for(int x=0;x<PLANSZA_MAX_X;x++)
            {
                mapaPozycjiStatkow[y][x]=false; //[y][x]
                mapaStrzalow[y][x]='.';
            }


    }


    void dodajStatek(Statek statek){
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

    void wyswietlMapeStrzalow() {
        int x;
        int y;
        System.out.println("(y)");
        for(y=PLANSZA_MAX_Y-1;y>=0;y--){
            //                      System.out.print((char)(y+'A'));
            if (y<=9) System.out.print(" "); // dodatkowa spacja dla jednocyfrowych liczb zeby dobrze wyswietlalo je, choc sa krotsze o jeden znak od dwucyfrowych
            System.out.print(y+" ");
            for(x=0;x<PLANSZA_MAX_X;x++) {
                System.out.print(mapaStrzalow[y][x]);
            }
            System.out.println();
        }
        System.out.print("   ");
        for(x=0;x<PLANSZA_MAX_X;x++) if (x>9) System.out.print(x/10); else System.out.print(" ");
        System.out.println(" (x) dziesiatki");
        System.out.print("   ");
        for(x=0;x<PLANSZA_MAX_X;x++) System.out.print(x%10);
        System.out.println(" (x) jednosci");
    }


    void wyswietlMapePozycjiStatkow() {
        int x;
        int y;
        //	System.out.println("Debug: Path: " + (this.getClass().getClassLoader().getResource("")).getPath());
        System.out.println("(y)");
        for(y=(PLANSZA_MAX_Y-1);y>=0;y--){
            System.out.print((" "+y));
            for(x=0;x<PLANSZA_MAX_X;x++) {
                if (mapaPozycjiStatkow[y][x]) System.out.print("X"); else System.out.print(".");
            }
            System.out.println();
        }
        System.out.print("  ");
        for(x=0;x<PLANSZA_MAX_X;x++) System.out.print(x);
        System.out.println(" (x)");

        System.out.println();
    }
    void incrementIloscWyswietlen(){
        iloscWyswietlen++;
    }



    void aktualizujMapeStrzalow(Pole p){
        if (mapaPozycjiStatkow[p.getY()][p.getX()]) mapaStrzalow[p.getY()][p.getX()]='X';
        else mapaStrzalow[p.getY()][p.getX()]=' ';
    }


    void oznaczPustePolaWokolZatopionegoStatku(Statek statek){
        Pole[] pozycja=statek.getPozycja();
        Pole p=new Pole();
        int maszty=statek.getIloscMasztow();
        MacierzSasiedztwa ms=new MacierzSasiedztwa();
        System.out.println(maszty+"-masztowiec trafiony!");
        System.out.println("na pozycji "+pozycja[0].getX()+","+pozycja[0].getY());
        // jesli pole znajduje sie na planszy, a nie poza nia
        for (int j=0;j<maszty;j++)
            for(int i=0;i<MacierzSasiedztwa.ILOSC_ELEMENTOW_MACIERZY_SASIEDZTWA;i++)
                if((pozycja[j].getY()+ms.pola[i].getY())>=0 & (pozycja[j].getY()+ms.pola[i].getY())<PLANSZA_MAX_Y & (pozycja[j].getX()+ms.pola[i].getX()>=0) & (pozycja[j].getX()+ms.pola[i].getX()<PLANSZA_MAX_X) )
                {

                    p.setX(pozycja[j].getX()+ms.pola[i].getX());
                    p.setY(pozycja[j].getY()+ms.pola[i].getY());
                    aktualizujMapeStrzalow(p);
                    System.out.println("p.X, p.Y: "+p.getX()+","+p.getY());
                }
    }

    int getIloscWyswietlen(){
        return iloscWyswietlen;
    }

    boolean  moznaPostawicStatek(int y, int x){
        // sprawdz, czy nie chcesz stawiac statku poza plansza (mapaOBSOLETE)
        // jesli tak, to zwroc false
        if (y<0 | y>=PLANSZA_MAX_Y | x<0 | x>=PLANSZA_MAX_X) return false;

        // sprawdz, czy nie chcesz stawiac statku w sasiedztwie innego statku
        MacierzSasiedztwa ms=new MacierzSasiedztwa();
        int sumaTrue=0;
        for(int i=0;i<MacierzSasiedztwa.ILOSC_ELEMENTOW_MACIERZY_SASIEDZTWA;i++) // jesli sasiednie pole jest na planszy to jesli jest na nim juz statek to zwieksz sumaTrue o 1; i tym samym zwroc false - nie mozna tu postawic statku
            if((y+ms.pola[i].getY())>=0 & (y+ms.pola[i].getY())<PLANSZA_MAX_Y & (x+ms.pola[i].getX()>=0) & (x+ms.pola[i].getX()<PLANSZA_MAX_X) ) 		if(mapaPozycjiStatkow[y+ms.pola[i].getY()][x+ms.pola[i].getX()]) sumaTrue++;
        if (sumaTrue>0) return false;

        return true;
    }*/
}
