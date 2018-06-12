import javax.swing.*;
import java.awt.*;


// obiekt Mapa uzywany do grania na konsoli tekstowej
// po wprowadzeniu GUI uzyteczny chyba tylko do debugowania
// dlatego nie pisalismy testow do tej klasy
// wiec program powinien dbac o jej aktualizacje po kazdym strzale
// a wczesniej o jej inicjalizacje. inaczej gra nie dziala prawidlowo
// natomiast wyswietlanie mapy na konsoli tekstowej 
// uzyteczne tylko do debugowania

public class Mapa  {
	static final int ILOSC_STATKOW_NA_PLANSZY=10;
final static int PLANSZA_MAX_X=10;
final static int PLANSZA_MAX_Y=10;
/* Pole.java SHIP_SHOOTED ogranicza max wartosci  PLANSZA_MAX_X i  PLANSZA_MAX_Y!! */

final static int LEFT_OFFSET_FROM_PANEL=20; // start drawing figures with this offset from left line of panel
final static int UPPER_OFFSET_FROM_PANEL=20; // start drawing figures with this offset from upper line of panel
static int SIZE_OF_SQUARE=30;
static int GAP_BEETWEEN_SQUARES=5;
//final static int LOWER_END_OF_IMAGE=PLANSZA_MAX_Y*(SIZE_OF_SQUARE+GAP_BEETWEEN_SQUARES); // because of fact, that y axis has got 0 point at upper line of panel, not in lower line of panel like in mathematics, we need this to know from what lower position we start drawing to up
final static int LOWER_END_OF_IMAGE=250;

GameLauncher rozgrywka;
int iloscWyswietlen;
boolean[][] mapaPozycjiStatkow=new boolean[PLANSZA_MAX_Y][PLANSZA_MAX_X]; //[y][x]
char[][] mapaStrzalow=new char[PLANSZA_MAX_Y][PLANSZA_MAX_X];
Graphics naszPedzel;
//	JButton przycisk;
//JFrame ramka;
Image poleNieSprawdzone=(new ImageIcon(getClass().getResource("/img/poleUnk.jpg"))).getImage();
Image polePudlo=(new ImageIcon(getClass().getResource("/img/polePudlo.jpg"))).getImage();
Image poleShip=(new ImageIcon(getClass().getResource("/img/poleShip.jpg"))).getImage();


Mapa(){
	przygotujDoPonownegoUzycia();
}

public class MapaGUI extends JPanel{

	MapaGUI(){
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension d = tk.getScreenSize();

		if (Logger.isDebugEnabled()) {
			Logger.debug("Screen width = " + d.width);
			Logger.debug("Screen height = " + d.height);
			 }

	//	if (d.width>1024) {
		if (d.height>1024) {
			SIZE_OF_SQUARE+=10;
			GAP_BEETWEEN_SQUARES+=2;
			System.out.println("High Resolution mode enabled.");	
		}
		else 
			System.out.println("Low Resolution mode.");	
		this.setPreferredSize(new Dimension(0, getSizeY()));
	}

	int getSizeX(){
	return PLANSZA_MAX_X*(GAP_BEETWEEN_SQUARES+SIZE_OF_SQUARE)+LEFT_OFFSET_FROM_PANEL;
	}
	int getSizeY(){
	return PLANSZA_MAX_Y*(GAP_BEETWEEN_SQUARES+SIZE_OF_SQUARE)+UPPER_OFFSET_FROM_PANEL;
	}

	public void paintComponent(Graphics g) {
		// wiem ze bez sensu to liczyc tyle razy ile petla sie obraca i mozna raz,
		// a pozniej podstawic do obrotow petli. optymalizacja na koncu zrobie ;)
		for(int y=0;y<PLANSZA_MAX_Y;y++){
			for(int x=0;x<PLANSZA_MAX_X;x++) {
				int imageX=x*(GAP_BEETWEEN_SQUARES+SIZE_OF_SQUARE)+LEFT_OFFSET_FROM_PANEL;
				int imageY=UPPER_OFFSET_FROM_PANEL+(PLANSZA_MAX_Y-1-y)*(GAP_BEETWEEN_SQUARES+SIZE_OF_SQUARE);

				switch (mapaStrzalow[y][x])
				{
					case '.':// g.drawImage(poleNieSprawdzone,imageX,imageY,this);
							 g.drawRect(imageX,imageY,SIZE_OF_SQUARE,SIZE_OF_SQUARE);
							  break;
					case 'X': g.drawImage(poleShip,imageX,imageY,imageX+SIZE_OF_SQUARE,imageY+SIZE_OF_SQUARE,0,0,poleShip.getWidth(null),poleShip.getHeight(null),this);
					// case 'X': g.drawImage(poleShip,imageX,imageY,this);
							  break;
					//default: g.drawImage(polePudlo,imageX,imageY,this);
					default: g.drawImage(polePudlo,imageX,imageY,imageX+SIZE_OF_SQUARE,imageY+SIZE_OF_SQUARE,0,0,poleShip.getWidth(null),poleShip.getHeight(null),this);
							 break;

				}
			}
		}

if (Logger.isDebugEnabled())	wyswietlMapeStrzalow();
// System.out.println("poleShip.Width Height:"+poleShip.getWidth(null)+" "+poleShip.getHeight(null));
	}

} //end of inner class

void przygotujDoPonownegoUzycia(){
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

void inicjujMapeStrzalow() {
	int x;
	int y;
	for(y=(PLANSZA_MAX_Y-1);y>=0;y--){
		for(x=0;x<PLANSZA_MAX_X;x++) mapaStrzalow[y][x]='.';
	}
	System.out.println();
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
	// sprawdz, czy nie chcesz stawiac statku poza plansza (mapa)
	// jesli tak, to zwroc false
	if (y<0 | y>=PLANSZA_MAX_Y | x<0 | x>=PLANSZA_MAX_X) return false;

	// sprawdz, czy nie chcesz stawiac statku w sasiedztwie innego statku
	MacierzSasiedztwa ms=new MacierzSasiedztwa();
		int sumaTrue=0;
		for(int i=0;i<MacierzSasiedztwa.ILOSC_ELEMENTOW_MACIERZY_SASIEDZTWA;i++) // jesli sasiednie pole jest na planszy to jesli jest na nim juz statek to zwieksz sumaTrue o 1; i tym samym zwroc false - nie mozna tu postawic statku
				if((y+ms.pola[i].getY())>=0 & (y+ms.pola[i].getY())<PLANSZA_MAX_Y & (x+ms.pola[i].getX()>=0) & (x+ms.pola[i].getX()<PLANSZA_MAX_X) ) 		if(mapaPozycjiStatkow[y+ms.pola[i].getY()][x+ms.pola[i].getX()]) sumaTrue++;
		if (sumaTrue>0) return false;

		return true;
}
}
