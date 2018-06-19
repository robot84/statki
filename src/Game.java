class Game  {
	MapaOBSOLETE mapaOBSOLETE;
	Statek[] statkiNaPlanszy =new Statek[MapaOBSOLETE.ILOSC_STATKOW_NA_PLANSZY];
	//Statek[] statkiNaPlanszy ;
	GUI gui;
	FabrykaStatkow fabrykaStatkow;



	void inicjalizuj(){
		mapaOBSOLETE =new MapaOBSOLETE();
		gui=new GUI(mapaOBSOLETE, statkiNaPlanszy,this); // utworz GUI, daj mu info o mapie i statkach
fabrykaStatkow=new FabrykaStatkow();
}

void rozpocznij(){
	Logger.debug("metoda rozpocznij() wywolana");
	/*
	for (int i=0;i<statkiNaPlanszy.length;i++) {
		// generujemy statki o liczbie masztow 1..MAX_ILOSC_MASZTOW
		statkiNaPlanszy[i]=new Statek((int)((Math.random()*MAX_ILOSC_MASZTOW)+1));
		if (Logger.isDeepDebugEnabled())		System.out.println("Debug: Utworzono nowy statek o liczbie masztow: "+statkiNaPlanszy[i].getIloscMasztow());

	}
	*/
	
	// utworz 4 jednomasztowce, 3 dwumasztowce, 2 trzymasztowce i jeden 4masztowiec
	// kolejnosc jest wazna o tyle, ze latwiej postawic statek 1masztowy, gdy stoja pozostale, niz 4masztowy...

	statkiNaPlanszy[9]=fabrykaStatkow.utworz4masztowiec();
	statkiNaPlanszy[7]=fabrykaStatkow.utworz3masztowiec();
	statkiNaPlanszy[8]=fabrykaStatkow.utworz3masztowiec();
	statkiNaPlanszy[4]=fabrykaStatkow.utworz2masztowiec();
	statkiNaPlanszy[5]=fabrykaStatkow.utworz2masztowiec();
	statkiNaPlanszy[6]=fabrykaStatkow.utworz2masztowiec();
	statkiNaPlanszy[0]=fabrykaStatkow.utworz1masztowiec();
	statkiNaPlanszy[1]=fabrykaStatkow.utworz1masztowiec();
	statkiNaPlanszy[2]=fabrykaStatkow.utworz1masztowiec();
	statkiNaPlanszy[3]=fabrykaStatkow.utworz1masztowiec();

	//statkiNaPlanszy=fabrykaStatkow.utworzFloteStatkow(1,2,3,4);
	
		mapaOBSOLETE.przygotujDoPonownegoUzycia(); // kolejnosc jest wazna!!
		gui.przygotujDoPonownegoUzyciaGUI();
	/*
	Stara metoda ustawStatki() ma zostac zastapiona nowa:
	UstawiaczStatkowNaMapie.ustawPrzypadkowoFlote()
	odkomentuj nowa, gdy bedzie zakodzona dobrze
	 */
		ustawStatki(); // jak zamienimy te linie i linie wyzej miejscami, to gra nie dziala!!
	//UstawiaczStatkowNaMapie.ustawPrzypadkowoFlote(statkiNaPlanszy,mapaOBSOLETE);
		mapaOBSOLETE.inicjujMapeStrzalow();

	}

	void ustawStatki(){
		final  int MAX_PROB_USTAWIEN_STATKU=1000;
		for(int i = 0; i< MapaOBSOLETE.ILOSC_STATKOW_NA_PLANSZY; i++){
			statkiNaPlanszy[i].recycle();
			for(int w=0;w<MAX_PROB_USTAWIEN_STATKU;w++) 
				if ( (!statkiNaPlanszy[i].ustawLosowoPolozenieStatku(mapaOBSOLETE)) ) {
				// co 10 nieudanych prob pisz blad (przy probach 9,19,29,...)
					if(Logger.isDeepDebugEnabled() && w%100==99) System.out.println("DeepDebug: Proba "+w+" NIE MOZNA USTAWIC STATKU!");
				}
				else 
				{
					if (Logger.isDeepDebugEnabled()) System.out.println("DeepDebug: Ustawilem statek!");
					break;
				}

			//	   statkiNaPlanszy[i].getAll();
			mapaOBSOLETE.dodajStatek(statkiNaPlanszy[i]);
			if (Logger.isDeepDebugEnabled()) mapaOBSOLETE.wyswietlMapePozycjiStatkow();
		}


	}
}
