class Statek  {
	static final int MAX_ILOSC_MASZTOW=4;

	private int iloscMasztow=MAX_ILOSC_MASZTOW;
	private Pole[] pozycjaStatkuMaszt; // nazwa dziwna, ale chodzi  oto, zeby ladnie
	// sie czytalo jak jest tablica, np pozycjaStatkuMaszt[1]
	// czytamy pozycjaStatkuMaszt pierwszy. czyli pozycja na mapie masztu jeden
	private Boolean[] masztTrafiony;
	private boolean zatopiony;
	private boolean ostatnioZatopiony;


	Statek(int ilosc_masztow){
	iloscMasztow=ilosc_masztow;
	pozycjaStatkuMaszt=new Pole[iloscMasztow];
	masztTrafiony=new Boolean[iloscMasztow];
		for (int i=0;i<iloscMasztow;i++){
			pozycjaStatkuMaszt[i]=new Pole();
			masztTrafiony[i]=false;
		}

	}

	Statek(){
	this(MAX_ILOSC_MASZTOW);
	}

	boolean czyMasztTrafiony(int maszt) throws NieosiagalnyMasztException {
if (maszt >= 0 & maszt <iloscMasztow)
		 return masztTrafiony[maszt];
    else throw new NieosiagalnyMasztException();

	}


	int getIloscMasztow(){
	return iloscMasztow;
	}

	void przygotujDoPonownegoUstawienia(){
	for (int i=0;i<iloscMasztow;i++){
		pozycjaStatkuMaszt[i].x=0;
		pozycjaStatkuMaszt[i].y=0;
		}
		zatopiony=false;
		ostatnioZatopiony=false;
	}


	boolean getZatopiony(){
		return zatopiony;
	}


	boolean getOstatnioZatopiony(){
		return ostatnioZatopiony;
	}


void setMasztTrafiony(int maszt){
masztTrafiony[maszt]=true;
}


boolean getMasztTrafiony(int maszt){
return masztTrafiony[maszt];
}


	void pokazPozycje(){
		for (int i=0;i<iloscMasztow;i++) System.out.println(" x: " + pozycjaStatkuMaszt[i].getX() + " y: " + pozycjaStatkuMaszt[i].getY());
	}

	void getAll(){
		System.out.println("-statek.getAll() zatopiony: " + zatopiony);
		for (int i=0;i<iloscMasztow;i++) System.out.println(" x: " + pozycjaStatkuMaszt[i].getX() + " y: " + pozycjaStatkuMaszt[i].getY());
	}

	boolean ustawLosowoPolozenieStatku(Mapa m){
		// zwraca true, jezeli udalo sie ustawic statek
		// jezeli sie nie udalo, to false.
		// moze tez sie zawiesic, czytaj komentarz z stawiaj1maszt()
		int x=(int)(Math.random()*(Mapa.PLANSZA_MAX_X)); 
		int y=(int)(Math.random()*(Mapa.PLANSZA_MAX_Y)); 
		int pionCzyPoziom=(int)(Math.random()*2);
		if (Logger.getDeepDebugEnabled()) {
			if (pionCzyPoziom==0)		Logger.deepDebug("(x,y) ("+x+","+y+")"+" pionowo");
			else Logger.deepDebug("(x,y) ("+x+","+y+")"+" poziomo");
		}

		if (pionCzyPoziom==0){
			// 0 oznacza pion
			boolean moznaPostawic=true;
			for (int i=0;i<iloscMasztow;i++) moznaPostawic&=m.moznaPostawicStatek(y+i,x);
			if (moznaPostawic) {
				// stawiamy statek, skoro sie da
				for (int i=0;i<iloscMasztow;i++){
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
			for (int i=0;i<iloscMasztow;i++) moznaPostawic&=m.moznaPostawicStatek(y,x+i);
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

	static Pole[] generujPolozenie(int i){
	// metoda uzywana zanim powstal random generator. moze byc przydatna przy testach?
	// w grze nie uzywana
	// stawia 3-masztowiec
		// int i - taki seed, zeby wygenerowac 3 rozne pozycje dla statkow
		// uwaga. generuja ciagle to samo polozenie! to nie jest random
		Pole[] pol=new Pole[MAX_ILOSC_MASZTOW];
		for(int j=0;j<MAX_ILOSC_MASZTOW;j++) pol[j]=new Pole();

		if (i>=0 & i<3){
			pol[0].setX(0+i*2);
			pol[0].setY(0+i*2);
			pol[1].setX(0+i*2);
			pol[1].setY(1+i*2);
			pol[2].setX(0+i*2);
			pol[2].setY(2+i*2);
		}
		else
		{
			pol[0].setX(0);
			pol[0].setY(0);
			pol[1].setX(0);
			pol[1].setY(1);
			pol[2].setX(0);
			pol[2].setY(2);
		}
		return pol;
	}

	void pokazStatekNaMapie(){
	// mam wrazenie ze gra obecnie tego teez nie uzywa - potwierdzic
		int x;
		int y;
		for(y=(Mapa.PLANSZA_MAX_Y-1);y>=0;y--){
			for(x=0;x<Mapa.PLANSZA_MAX_X;x++) {
				boolean jest = false;
				for(int z=0;z<iloscMasztow;z++) if (pozycjaStatkuMaszt[z].getX()==x && pozycjaStatkuMaszt[z].getY()==y) jest = true;
				if (jest) System.out.print("X");
				else System.out.print(".");
			}
			System.out.println();
		}
		System.out.println();

	}

	void setPole(Pole[] po){
		for (int i=0;i<iloscMasztow;i++){
			System.out.println("-->"+i);
			pozycjaStatkuMaszt[i].setX(po[i].getX());
			pozycjaStatkuMaszt[i].setY(po[i].getY());
		}
		// tu byl blad. 17:25 znaleziony. szukany od
	}

	Pole[] getPozycja(){
		return pozycjaStatkuMaszt;
	}

	Pole getPozycja(int i) throws NieosiagalnyMasztException {
		if (i<iloscMasztow && i>=0)
			return pozycjaStatkuMaszt[i];
		else throw new NieosiagalnyMasztException();
	}
	// zmienilem tu cos !!!  bylo tak :   if (i<=iloscMasztow && i>0)

	void wyswietlPolozenie(){
		for(Pole p:pozycjaStatkuMaszt) System.out.println("x: " + p.getX() + " y: " + p.getY());
	}


	boolean jestTrafionyWPole(Pole p) {
		// ta metoda wyglada jakby odpowiadala na pytanie czy w ogole trafiony w ktorys z masztow w tym strzale
		// ale tylko mowi tak/nie, a jak 'tak' to nie oznacza nigdzie trafienia! i dalej ten zagiel wyglada na nietrafiony
	// czy tu nie powstanie NULL pointer?? dla statkow 1,2masztowych?
		// AHA! dlaczego dla 1,2maszt? bo max maszt byl3 wtedy! czyli teraz dla 1,2,3maszt!!
		// lepiej bylo to opisac "dla statkow o liczbie masztow < MAX"
		Logger.deepDebug(p.toString());

		/* jezeli chociaz jedno pole z pol zajmowanych przez statek na mapie ma te sama pozycje co pole z parametru metody
		to znaczy ze statek zostal trafiony w to pole i zwracamy true.
		UWAGA! Nie ma znaczenia czy trafienie nastapilo w tym strzale czy 10 strzalow temu
		Ta metoda nie zmienia stanu zmiennych maszTrafiony, a powinna!!
		 */
		for (Pole po:pozycjaStatkuMaszt)
			if((p.getX()==po.getX()) && (p.getY()==po.getY()) ) {
				return true;
			}

		ostatnioZatopiony=false;
		return false;
	}

	void setTrafiony(Pole p) {
				// ustaw magic number czyli SHIP_SHOOTED jako jego pozycje
				int i=0;
				for (Pole po:pozycjaStatkuMaszt)
				{
					if((p.getX()==po.getX()) && (p.getY()==po.getY()) ) {
						
					setMasztTrafiony(i);	
					}
				i++;
				}
				sprawdzCzyWlasnieZatopiony();
	}

	boolean sprawdzCzyZatopiony() {
 			boolean zatopiony=true;
			for (int i=0;i<iloscMasztow;i++) zatopiony&=getMasztTrafiony(i);
			if (zatopiony) {
				return true;
			}
			else return false;
	}

	boolean sprawdzCzyWlasnieZatopiony() {
			boolean zatopionyyy=true;
			for (int i=0;i<iloscMasztow;i++) zatopionyyy&=getMasztTrafiony(i);
			if (zatopionyyy) {
			zatopiony=true;
			ostatnioZatopiony=true; // poniewaz wlasnie go zatopilo obecne trafienie
System.out.println("Wlasnie zatopiony!");
			return true;
		}
		else return false;
	}


	boolean sprawdzCzyOstatnioZatopiony() {
		return ostatnioZatopiony;
	}



}

