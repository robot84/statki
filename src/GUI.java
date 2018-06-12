import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/*

   +--------JFrame ramka-600x380---------------+
   |          panelCentralny CENTER            |
   | +panel1_290x300----+ +panel2_290x300----+ |
   | |                  | |                  | |
   | | +map_0x300-----+ | |                  | |
   | | |              | | |                  | |
   | | |              | | |                  | |
   | | |              | | |                  | |
   | | |              | | |                  | |
   | | |              | | |                  | |
   | | +--------------+ | |                  | |
   | |Podaj xy:         | |                  | |
   | |#############     | |                  | |
   | +------------------+ +------------------+ |
   |                                           |
   |                                           |
   +-------------------------------------------+
   | Ilosc ruchow: x  JLabel SOUTH             |
   +-------------------------------------------+
 */


class GUI  {
static final String VERSION="v0.9 build 683";
static final int MIEJSCE_NA_LABEL_I_JTFstrzalW=40; // tyle, zeby sie zmiesily
static final int PANEL2_SIZE_X=290; // czemu tyle? a zeby sie obrazek zmiescil
JFrame ramka;
ObrazekKoncaGry obrazKoncaGry=new ObrazekKoncaGry();
JPanel panelCentralny; // ten zawiera dwa ponizsze panele:
JPanel panel1; // tu bedzie mapa i jest z lewej strony okna
JPanel panel2; // a ten jest pusty i jest z prawej strony okna
Mapa mapa;
Mapa.MapaGUI mapaGui;
Statek[] stateczek;
HallofFame hall;
JLabel infoIloscRuchow;
JLabel zapytajOstrzal;
JTextField JTFstrzalW;
JTextField koniecGry;
Game rozgrywka;
// wybaczcie, ze ponizsze zmienne tu sa. to stan przejsciowy
boolean ostatnioTrafiony=false;
boolean ostatnioZatopiony=false;
Pole poleOstrzeliwane =new Pole();

GUI(Mapa map, Statek[] statki, Game partyjkaGry){
		rozgrywka=partyjkaGry;

		mapa=map; // zeby klasy wewnetrzne tez mialy dostep do tego
		stateczek =statki;// zeby klasy wewnetrzne tez mialy dostep do tego
	//Logger.deepDebug("stateczek[0].toString() "+statki[0].getIloscMasztow());
		mapaGui= mapa.new MapaGUI();
		panelCentralny=new JPanel();
		panel1=new JPanel();
		panel2=new JPanel();
		infoIloscRuchow=new JLabel("Wykonales " + (map.getIloscWyswietlen()) + " ruchow.");
		zapytajOstrzal=new JLabel("Podaj [xy]:");
		JTFstrzalW=new JTextField(4);
		JTFstrzalW.setColumns(4);
		JTFstrzalW.addActionListener(new JTFstrzalWlistener());
		koniecGry=new JTextField("Good game!");

		int panel12sizeY=mapaGui.getSizeY()+MIEJSCE_NA_LABEL_I_JTFstrzalW;
		int panel1sizeX=(Mapa.PLANSZA_MAX_X*(Mapa.GAP_BEETWEEN_SQUARES+Mapa.SIZE_OF_SQUARE)+Mapa.LEFT_OFFSET_FROM_PANEL*2);
		panel2.setPreferredSize(new Dimension(PANEL2_SIZE_X, panel12sizeY));
		panel1.setPreferredSize(new Dimension(panel1sizeX, panel12sizeY));
		if (Logger.getDebugEnabled()) System.out.println("Panel1 x size:"+panel1sizeX);
		if (Logger.getDebugEnabled()) System.out.println("Panel1 y size:"+panel12sizeY);
		panelCentralny.add(panel1);
		panelCentralny.add(panel2);
		panel1.add(mapaGui);
		panel1.add(zapytajOstrzal);
		panel1.add(JTFstrzalW);
		//		panel2.setBackground(Color.darkGray);
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));

		JMenuBar menu=new JMenuBar();
		JMenu menuNew=new JMenu("New");
		JMenu menuScore=new JMenu("Score");
		JMenu menuHelp=new JMenu("Help");
		JMenuItem opcjaNowaGra=new JMenuItem("New game");
		JMenuItem opcjaAbout=new JMenuItem("About");
		JMenuItem opcjaShowScore=new JMenuItem("ShowScore");
		opcjaNowaGra.addActionListener(new MenuNewGameListener());
		opcjaAbout.addActionListener(new MenuHelpAboutListener());
		opcjaShowScore.addActionListener(new ShowScoreListener());
		menuNew.add(opcjaNowaGra);
		menuHelp.add(opcjaAbout);
		menuScore.add(opcjaShowScore);
		menu.add(menuNew);
		menu.add(menuScore);
		menu.add(menuHelp);

		ramka = new JFrame("Statki " + VERSION);
		ramka.setJMenuBar(menu);
		ramka.getContentPane().add(BorderLayout.CENTER,panelCentralny);
		ramka.getContentPane().add(BorderLayout.SOUTH,infoIloscRuchow);
		ramka.pack();
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka.setVisible(true);
		//	ramka.setResizable(false);
		ramka.addKeyListener(new SluchaczKlawiatury());
		mapaGui.addMouseListener(new SluchaczMyszy());

		hall=new HallofFame();
		hall.gui.revalidate();
		hall.sortuj();
}

void przygotujDoPonownegoUzyciaGUI(){
		panel2.remove(obrazKoncaGry);
		mapaGui.repaint();
		mapaGui.revalidate();
		panelCentralny.repaint();
		panelCentralny.revalidate();
		ramka.repaint();
		ramka.revalidate();


		ActionListener[] AL;
		AL=JTFstrzalW.getActionListeners();
		if(AL.length!=0) return;
		JTFstrzalW.addActionListener(new JTFstrzalWlistener());
		MouseListener[] ML;
		ML=mapaGui.getMouseListeners();
		if(ML.length!=0) return;
		mapaGui.addMouseListener(new SluchaczMyszy());

}


void procesujStrzal(){
int trafionyStatekNr=999999; // wartosc > ilosc statkow na planszy

	/*  chyba ma za zadanie zliczanie ruchow. bardzo niefortunna nazwa */
		mapa.incrementIloscWyswietlen();

	/* odswiez ekran(okno) */
		mapaGui.repaint();
		mapaGui.revalidate();
		panelCentralny.repaint();
		panelCentralny.revalidate();

		refreshIloscRuchow();
		mapa.aktualizujMapeStrzalow(poleOstrzeliwane);
	 for(int i = 0;i<Mapa.ILOSC_STATKOW_NA_PLANSZY;i++) {
	 	Logger.deepDebug("statek nr="+i+" "+stateczek[i].toString());

	 	if (stateczek[i].jestTrafionyWPole(poleOstrzeliwane)) {
			 stateczek[i].setTrafiony(poleOstrzeliwane);
			 ostatnioTrafiony = true;
			 trafionyStatekNr = i;
			 ostatnioZatopiony = stateczek[trafionyStatekNr].sprawdzCzyOstatnioZatopiony();
			 if (ostatnioZatopiony) mapa.oznaczPustePolaWokolZatopionegoStatku(stateczek[trafionyStatekNr]);
			 if (Logger.getDebugEnabled()) {
				 wypiszKomunikatNaKonsoliJesliTrafionyLubZatopiony();
			 }
		 }
	 }
		ostatnioTrafiony=false;
		ostatnioZatopiony=false;

		if(wszystkieStatkiZatopione()) {
				wyswietlObrazekKoncaGry();
				odlaczSluchaczaJTF();
				odlaczSluchaczaMyszyMapy();

				hall.setVisible();
				if (Logger.getDebugEnabled())
						hall.showFameListInConsole();
				if (hall.czyWynikNadajeSieDoRankingu((mapa.getIloscWyswietlen())))
						hall.dodajWynikDoRankingu(mapa.getIloscWyswietlen());

		}
} // end of method

boolean wszystkieStatkiZatopione()
{
boolean wszystkieZatopione=true;
for(int i = 0;i<Mapa.ILOSC_STATKOW_NA_PLANSZY;i++) wszystkieZatopione&= stateczek[i].sprawdzCzyZatopiony();

		return wszystkieZatopione;
}

void wypiszKomunikatNaKonsoliJesliTrafionyLubZatopiony(){
		if (ostatnioTrafiony) System.out.println("Debug:    TRAFIONY!!");
		if (ostatnioZatopiony) System.out.println("Debug:    TRAFIONY ZATOPIONY!!");

}
boolean walidujWprowadzoneDane(String strzal){
		if (strzal.length()!=2) return false;
		String s1 = strzal.substring(0,1);
		String s2 = strzal.substring(1,2);
		try{
				poleOstrzeliwane.setX(Integer.parseInt(s1));
				poleOstrzeliwane.setY(Integer.parseInt(s2));
		}
		catch (NumberFormatException e)
		{
				return false;
		}


		System.out.println("Wprowadzono strzal z GUI na pozycje ("+s1+","+s2+")");
		return true;
} // end of method


void odlaczSluchaczaMyszyMapy(){
		MouseListener[] ML;
		ML=mapaGui.getMouseListeners();
		mapaGui.removeMouseListener(ML[0]);

}


void odlaczSluchaczaJTF(){
		ActionListener[] AL;
		AL=JTFstrzalW.getActionListeners();
		JTFstrzalW.removeActionListener(AL[0]);

}

void wyswietlObrazekKoncaGry()
{
		if (Logger.getDebugEnabled())   System.out.println("ZATOPILES WSZYSTKO!! w "+mapa.getIloscWyswietlen() + " ruchach.");
		obrazKoncaGry.setPreferredSize(new Dimension(200, 260));
		panel2.add(obrazKoncaGry);
		JTFstrzalW.setEnabled(false);
		JTFstrzalW.setText("THE END");

}


void refreshIloscRuchow(){
		infoIloscRuchow.setText("Wykonales " + (mapa.getIloscWyswietlen()) + " ruchow.");
}


class MenuNewGameListener implements ActionListener {

		public void	actionPerformed(ActionEvent ev){
				System.out.println("MenuNewGameListener clicked");
				Graphics gr=mapaGui.getGraphics();
				Graphics panell2=panel2.getGraphics();
				gr.setColor(mapaGui.getBackground());
				gr.fillRect(0,0,300,300);
				panell2.setColor(mapaGui.getBackground());
				panell2.fillRect(0,0,300,300);
				System.out.println("Debug: mapaGui.getWidth(),mapaGui.getHeight(): ("+mapaGui.getWidth()+","+mapaGui.getHeight()+")");
				rozgrywka.rozpocznij();

		}
}

class ShowScoreListener implements ActionListener {
		public void  actionPerformed(ActionEvent ev){
				hall.setVisible();

		}
}

class MenuHelpAboutListener implements ActionListener {

		public void	actionPerformed(ActionEvent ev){
				JOptionPane.showMessageDialog(null, "Statki\nAutor Robert Zabkiewicz\n(c) 2017", "InfoBox: " , JOptionPane.INFORMATION_MESSAGE);
		}
}



class SluchaczMyszy implements MouseListener {

		public void mousePressed(MouseEvent e) {
				//			saySomething("Mouse pressed; # of clicks: " + e.getClickCount(), e);
		}

		public void mouseReleased(MouseEvent e) {
				//			saySomething("Mouse released; # of clicks: " + e.getClickCount(), e);
		}

		public void mouseEntered(MouseEvent e) {
				//			saySomething("Mouse entered", e);
		}

		public void mouseExited(MouseEvent e) {
				//			saySomething("Mouse exited", e);
		}

		public void mouseClicked(MouseEvent e) {

				Pole[] p;
				Pole strzal=new Pole();

				if (Logger.getDebugEnabled())
						saySomething("Mouse clicked (# of clicks: "
										+ e.getClickCount() + ")", e);

				// block for secret code that shoot all ships
				if ((e.getX()<10) & (e.getY() < 30)){
						System.out.println("Secret accepted. All objects shooted!");	
						for (int b=0;b<3;b++){
								p= stateczek[b].getPozycja();
								for (int a=0;a<3;a++){
										strzal.setX(p[a].getX());
										strzal.setY(p[a].getY());
										stateczek[b].jestTrafionyWPole(strzal);
								} // end of for
						} // end of for
				} // enf of if

				// wyswietlamy liste pol na konsoli:
				/*
				   for(int y=0;y<Mapa.PLANSZA_MAX_Y;y++){
				   for(int x=0;x<Mapa.PLANSZA_MAX_X;x++) {
				   System.out.println("field ("+x+","+y+"): ("+(x*(Mapa.GAP_BEETWEEN_SQUARES+Mapa.SIZE_OF_SQUARE)+Mapa.LEFT_OFFSET_FROM_PANEL)+","+((Mapa.LOWER_END_OF_IMAGE-(y*(Mapa.GAP_BEETWEEN_SQUARES+Mapa.SIZE_OF_SQUARE)+Mapa.UPPER_OFFSET_FROM_PANEL)))+") size: "+Mapa.SIZE_OF_SQUARE);
				   }
				   } 
				 */
				// konwersja pozycji klikniecia myszka w wspolrzedne pola na planszy
				int xx=(e.getX()-Mapa.LEFT_OFFSET_FROM_PANEL)/(Mapa.GAP_BEETWEEN_SQUARES+Mapa.SIZE_OF_SQUARE);
				if (((e.getX()-Mapa.LEFT_OFFSET_FROM_PANEL)%(Mapa.GAP_BEETWEEN_SQUARES+Mapa.SIZE_OF_SQUARE))>Mapa.SIZE_OF_SQUARE) return; // strzal w przestrzen miedzy polami
				if (e.getX()<Mapa.LEFT_OFFSET_FROM_PANEL) return; //strzal na lewo od pol	
				if (e.getX()>(Mapa.LEFT_OFFSET_FROM_PANEL+(Mapa.PLANSZA_MAX_X*(Mapa.GAP_BEETWEEN_SQUARES+Mapa.SIZE_OF_SQUARE)))) return; //strzal na prawo od pol	
				int yy=(Mapa.PLANSZA_MAX_Y-1)-((e.getY()-Mapa.UPPER_OFFSET_FROM_PANEL)/(Mapa.GAP_BEETWEEN_SQUARES+Mapa.SIZE_OF_SQUARE));
				if (((e.getY()-Mapa.UPPER_OFFSET_FROM_PANEL)%(Mapa.GAP_BEETWEEN_SQUARES+Mapa.SIZE_OF_SQUARE)>Mapa.SIZE_OF_SQUARE)) return;
				if (e.getY()>(Mapa.PLANSZA_MAX_Y*(Mapa.GAP_BEETWEEN_SQUARES+Mapa.SIZE_OF_SQUARE)+Mapa.UPPER_OFFSET_FROM_PANEL-Mapa.GAP_BEETWEEN_SQUARES)) return; //strzal na dol od pol	
				if (e.getY()<Mapa.UPPER_OFFSET_FROM_PANEL) return; //strzal na gore od pol	
		//		if (Logger.getDebugEnabled())   System.out.println("Strzal trafil w pole ("+xx+","+yy+")");
		System.out.println("Wprowadzono strzal z GUI na pozycje ("+xx+","+yy+")");
//System.out.flush();
				// strzelamy myszka w pole:
				poleOstrzeliwane.setX(xx);
				poleOstrzeliwane.setY(yy);
				procesujStrzal();

		} //end of method

		boolean wszystkieStatkiZatopione()
		{
				return stateczek[0].getZatopiony() & stateczek[1].getZatopiony() & stateczek[2].getZatopiony();
		}


		void saySomething(String eventDescription, MouseEvent e) {
				System.out.println(eventDescription + " detected on "
								+ e.getComponent().getClass().getName()
								+ ". ("+ e.getX() +","+ e.getY()+")" );
		}
}

class SluchaczKlawiatury implements KeyListener {
		@Override
				public void keyPressed(KeyEvent evt) {
				}

		@Override
				public void keyReleased(KeyEvent evt) {
						int c = evt.getKeyCode();
						if (c == KeyEvent.VK_W)System.out.println("Wcisnieto W"); 
				}

		@Override
				public void keyTyped(KeyEvent evt) {

				}

}
/*
   obsluga pola tekstowego do wpisywania wspolrzednych
 */
class JTFstrzalWlistener implements ActionListener {

		public void	actionPerformed(ActionEvent ev){
				// DO PRZEROBIENIA NA BARDZIEJ OBIEKTOWE - te zmienne globalne wywalic jakos

				if (!walidujWprowadzoneDane(JTFstrzalW.getText())) return;
				// sprawdza czy user wprowadzil obie wspolrzedne w JTF i sa one w granicy planszy

				JTFstrzalW.setText("");
				procesujStrzal();
		}


} //end of inner class

} // end of GUI class


