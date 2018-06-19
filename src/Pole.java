class Pole  {
int x=0;
int y=0;
// TODO pozbyc sie tego badziewia w ogole
static final int SHIP_SHOOTED=99; // some value > PLANSZA_MAX_X & > PLANSZA_MAX_Y
/* SHIP_SHOOTED ogranicza max wartosci  PLANSZA_MAX_X i  PLANSZA_MAX_Y!! */

public	void setX(int xx){

	//TODO refaktor, zmienic ponizsza linie na:
	//if ( xx< MapaOBSOLETE.PLANSZA_MAX_X && xx>=0) x=xx;
	// TODO 2: a nastepnie zrobic metode isBetween(int lowerValue, int higherValue) dla typu int
	// i wtedy linie mozna dalej uproscic
	// SuperInt xxx=new SuperInt(xx);
	// if (xxx.isBetween(0,MapaOBSOLETE.PLANSZA_MAX_X)) x=xx;
	// albo:
	// if (Segments.value(xx).IsBetween(0,MapaOBSOLETE.PLANSZA_MAX_X)) x=xx;

		if (xx< MapaOBSOLETE.PLANSZA_MAX_X && xx>=0) x=xx;
		else x=-1;
	}

	public	void setY(int yy){
		if (yy< MapaOBSOLETE.PLANSZA_MAX_Y && yy>=0) y=yy;
		else y=-1;
	}

	public	int getX() {
		return x;
	}

	public	int getY() {
		return y;
	}

	//TODO te rzeczy nie powinny byc w tej klasie. Pole to pole, nie ma nic wspolnego z jakimis statkami i strzelaniem
	// trza bedzie te rzeczy przeniesc gdzies, albo zmienic nazwe klasy na PolePozycjiStatku i wtedy takie pole moze byc
	// ostrzelane. zreszta zwykle pole tez moze, ale niech posiada zmienna isShooted, albo cos takiego.
	// a nie jakies dziwne wartosci pola swiadczace o czyms, do czego pokazania powinna byc osobna zmienna
	// te metody nigdy nie sa uzyte w kodzie, a tylko w testach:
	public	void setXshooted(){
		x=SHIP_SHOOTED;
	}

	public	void setYshooted(){
	y=SHIP_SHOOTED;
	}

	public boolean isShooted(){
	return (x==SHIP_SHOOTED && y==SHIP_SHOOTED);
	}


}


