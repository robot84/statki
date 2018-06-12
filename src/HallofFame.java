import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
 
/*

Uwaga! Rozszerzamy klase StaticVariables, gdyz chcemy miec zdefiniowane zmienne debugEnabled.
Jak chcesz uzyc tej klasy w innej grze, to pamietaj, zeby zmienne debugEnabled i deepDebugEnabled jej przekazac - inaczej sie nie skompiluje
Albo pomysl o klasie Debug lub interfejsie takim...

@param fameList lista wpisow wynikow
@param SER_FILE sciezka do pliku z wynikami
@param gui No GUI :)
*/
class HallofFame  {
static final String SER_FILE = "./hall.ser";
static final int MAX_FAMELIST_SIZE=20;
	ArrayList<Fame> fameList=new ArrayList<Fame>();
		HallofFameGUI gui;

	HallofFame(){
	this(false);
	}

	/*
	createList==true - wypelniamy liste fameList zahardcodowanymi wpisami
		warto uzyc, gdy plik SER_FILE nie istnieje, jest uszkodzony,
		albo nie chce sie wygenerowac, bo zmienilismy cos w klasie Fame
	==false - wczytujemy wpisy do fameList z pliku SER_FILE
	*/
	HallofFame(boolean createList){
		gui=new HallofFameGUI();
		gui.setSize(400,500);
		gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		if (createList) createSampleList();
		else readFromFile(SER_FILE);

	}


	void setAdminMode(){
	gui.setAdminMode();
	}

	
	void setVisible(){
		gui.setVisible(true);
		}

	void unsetVisible(){
		gui.setVisible(false);
		}

	public static void main(String args[]) {
		boolean createList=false;
		if (args.length>0 && args[0].equals("-c")) createList=true;
		HallofFame hall=new HallofFame(createList);
		if (args.length>0 && args[0].equals("--admin-mode")) hall.setAdminMode();
		hall.dzialamyNieStatycznie();
		hall.setVisible();
	} // end of main()
	
	void dzialamyNieStatycznie(){
	NameDialogWindow dialog0=new NameDialogWindow();
		dialog0.setLocationRelativeTo(null);
		dialog0.showDialogWin();
		// use -c if file hall.ser is missing or corrupted
		this.gui.revalidate();
		this.showFameListInConsole();
		this.sortuj();
		}


/*
Przydatne, jak zmienimy cos w klasie Fame i musimy utworzyc od nowa liste
Albo jak plik .ser nam zginie i chcemy wygenerowac od nowa jakis
*/
void createSampleList(){
Fame test_F=new Fame("Piotr",50);
Fame test_A=new Fame("Macrin",30);
Fame test_B=new Fame("Stefan",24);
addEntry(test_F);
addEntry(test_A);
addEntry(test_B);
}

boolean czyWynikNadajeSieDoRankingu(int wynik){
// jezeli lista mniejsza niz MAX_FAMELIST_SIZE elementow - zawsze wynik wchodzi na liste
// bierzemy MAX_FAMELIST_SIZE najlepszych wynikow - wpisow w fameList
// i okreslamy prog, powyzej ktorego nowy wynik kwalifikujemy na HoF
if (fameList.size()<=MAX_FAMELIST_SIZE)return true;
else {
Fame f=fameList.get(MAX_FAMELIST_SIZE);
if (wynik<f.getScore()) return true; else return false;
}
}

void dodajWynikDoRankingu(int wynik){
// w tej metodzie nie widac tak naprawde procesu dodawania... bo jest on robiony po kliknieciu OK w oknie
// czyli w ActionListener buttonOK....
// tu tylko tworzymy okno i czekamy na wpisanie imienia do niego....
		NameDialogWindow dialog0;
		dialog0=new NameDialogWindow(wynik);
		dialog0.setLocationRelativeTo(null);
		dialog0.showDialogWin(); //dialog win, co ta nazwa nam mowi?? co jest w tym oknie???

}

void sortuj(){
		Collections.sort(fameList);

}
	void readFromFile(String path){

	try {
	FileInputStream fs = new FileInputStream(path);
	ObjectInputStream os = new ObjectInputStream(fs);
	Integer listSize= (Integer) os.readObject();
	if (listSize==0) return;
if (Logger.getDebugEnabled()) System.out.println("Debug: listSize: "+listSize);
	for (int i=1;i<=listSize;i++)
	{
if (Logger.getDebugEnabled()) System.out.println("Debug: readFromFile() listElement: "+i);
		Fame fameEntry= (Fame)os.readObject();
//		fameList.addEntry(fameEntry);
		fameEntry.blowAfterReadFromFile();
		 HallofFame.this.addEntry(fameEntry);
		}
	os.close();
	} catch (Exception ex) {
	ex.printStackTrace();
	}
} // end of method

	void writeToFile(String path){
	Integer listSize=fameList.size();
	try {
	FileOutputStream fs = new FileOutputStream(path);
	ObjectOutputStream os = new ObjectOutputStream(fs);

	os.writeObject(listSize);
	for (Fame fameEntry:fameList)
		os.writeObject(fameEntry);
	os.close();
	} catch (Exception ex) {
	ex.printStackTrace();
	}

	} // end of method


void showFameListInConsole(){
System.out.println("Debug: fameList contains these elements: ");
for (Fame fameEntry:fameList) System.out.println("Debug: name: "+fameEntry.getNamee()+" score: "+fameEntry.getScore());

}

	void addEntry(Fame f){
		fameList.add(f);
		gui.panel1.add(f.getNameeL());
		gui.panel2.add(f.getScoreL());
	if (Logger.getDebugEnabled())	System.out.println("Debug: addEntry(): "+f+" dodany na liste z wynikiem "+f.getScore());
		sortuj(); 
		gui.repaint();
		gui.revalidate();

	}

	void refresh(){

			//zapisz do pliku liste, kasuj liste (a w zasadzie stworz nowa pusta, bo tak szybciej) i wczytaj
			// z pliku ladna posortowana liste :)
			writeToFile(SER_FILE);
			removeAllEntries();
							readFromFile(SER_FILE);
							//wczyraj na nowo z pliku
			}

void removeAllEntries(){
		Iterator<Fame> iter = fameList.iterator();

		while (iter.hasNext()) {
				Fame f = iter.next();
				iter.remove();
				removeEntry(f);
		}
}

	void removeEntry(Fame f){
		fameList.remove(f);
		gui.panel1.remove(f.getNameeL());
		gui.panel2.remove(f.getScoreL());
if (Logger.getDebugEnabled()) 	System.out.println("Debug: jestem w metodzie removeEntry()");
        if (fameList.contains(f))System.out.println("Debug: "+f+" usuniety z listy");
        gui.repaint();
        gui.revalidate();
		} // end of method


	/*

	 +---+--------JFrame ramka-400x500-------+---+
	 |JL main_label    NORTH                     |
	 |   +------------panel0-CENTER----------+   +
	 |   |                                   |   |
	 |   | +panel1_150x400+ +panel2_150x400+ |   |
	 |   | |              | |              | |   |
	 | W | |  JL name_lab | | JL score_lab | | E |
	 | E | |              | |              | | A |
	 | S | |              | |              | | S |
	 | T | |              | |              | | T |
	 |   | |              | |              | |   |
	 |   | |              | |              | |   |
	 |   | |              | |              | |   |
	 |   | |              | |              | |   |
	 |   | |              | |              | |   |
	 |   | |              | |              | |   |
	 |   | |              | |              | |   |
	 |   | +--------------+ +--------------+ |   |
	 |   |                                   |   |
	 +   +-----------------------------------+   +
	 |                  SOUTH                    |
	 +---+-----------------------------------+---+
	 */

	class HallofFameGUI extends JFrame{
		JPanel panel0;
		JPanel panel1;
		JPanel panel2;
		JLabel name_lab;
		JLabel score_lab;
		JLabel main_label;
		JPanel panelSOUTH;
		JButton writeToSerFileButton;
		JButton removeEntryButton;
		JButton editEntryButton;
		boolean adminMode=false;

		HallofFameGUI(){
			panel0=new JPanel();
			panel1=new JPanel();
			panel2=new JPanel();
			panelSOUTH=new JPanel();
			score_lab=new JLabel("Score");
			name_lab=new JLabel("Name");
			Font myFont = new Font("Courier", Font.BOLD,14);
			name_lab.setFont(myFont);
			score_lab.setFont(myFont);
			main_label=new JLabel("Hall of fame");
			writeToSerFileButton=new JButton("Write to File");
			removeEntryButton=new JButton("RemoveEntry");
			editEntryButton=new JButton("EditEntry");

			panel1.setPreferredSize(new Dimension(150, 400));
			panel2.setPreferredSize(new Dimension(150, 400));
			panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
			panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
			main_label.setAlignmentX(Component.CENTER_ALIGNMENT);
			score_lab.setAlignmentX(Component.CENTER_ALIGNMENT);
			name_lab.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.setTitle("Hall of Fame");
			this.getContentPane().add(BorderLayout.CENTER,panel0);
			this.getContentPane().add(BorderLayout.NORTH,main_label);
			this.getContentPane().add(BorderLayout.SOUTH,panelSOUTH);
//			panel1.addMouseListener(new SluchaczMyszy());

			panel0.add(panel1);
			panel0.add(panel2);
			panel1.setBackground(Color.lightGray);
			panel2.setBackground(Color.lightGray);
			panel1.add(name_lab);
			panel2.add(score_lab);

		} // end of constructor

	void setAdminMode(){
			panelSOUTH.add(writeToSerFileButton);
			panelSOUTH.add(editEntryButton);
			writeToSerFileButton.addActionListener(new WriteToSerFileButtonListener());
			editEntryButton.addActionListener(new EditEntryButtonListener());
			panelSOUTH.add(removeEntryButton);
			removeEntryButton.addActionListener(new RemoveEntryButtonListener());
	}

		class WriteToSerFileButtonListener implements ActionListener {

				public void actionPerformed(ActionEvent ev){

						HallofFame.this.writeToFile(SER_FILE);
						HallofFame.this.showFameListInConsole();
				}
		} // end of class

		class EditEntryButtonListener implements ActionListener {
				public void actionPerformed(ActionEvent ev){

					for(Fame f:fameList)
					if (f.getNameeL().getForeground()==Color.blue) {
					System.out.println("Debug: Element "+f.getNamee()+
						" do edycji");
					f.setScore(f.getScore()+1);
}
		
				}
				}

		class RemoveEntryButtonListener implements ActionListener {
				public void actionPerformed(ActionEvent ev){

				Iterator<Fame> iter = fameList.iterator();

				while (iter.hasNext()) {
				    Fame f = iter.next();
					if (f.getNameeL().getForeground()==Color.blue) {
					System.out.println("Debug: Element "+f.getNamee()+
						" do usuniecia");
			        iter.remove();
					HallofFame.this.removeEntry(f);
}
}

				}
		} // end of class

	} // end of class HallofFrameGUI


	class NameDialogWindow extends JFrame implements ActionListener {
	JLabel lab=new JLabel();
	JTextField name=new JTextField("");
	JButton buttonOK=new JButton("OK");
	int score=0;

	// jesli utworzymy okno konstruktorem bezparametrowym, czyli nie interesuje nas wynik, to niech on bedzie 50
	NameDialogWindow(){
	this(50);
	}

	NameDialogWindow(int wynik){
	lab.setText("<HTML><BODY>Congratulations!<br>With a score of "+wynik+" you qualified to TOP "+MAX_FAMELIST_SIZE+"!<br>Enter your name:</BODY></HTML>");
	this.setTitle("High score!");
    this.setSize(290,160);
	this.getContentPane().add(BorderLayout.SOUTH,buttonOK);
	this.getContentPane().add(BorderLayout.NORTH,lab);
	this.getContentPane().add(BorderLayout.CENTER,name);
	buttonOK.addActionListener(this);
	score=wynik;
	}

	public void actionPerformed(ActionEvent ev){
			if(name.getText().isEmpty()) return;
			// jesli nic nie wpisano, to nie akceptujemy takiego wyniku

			Fame f=new Fame();
			buttonOK.setText("Added");
			f.setNamee(name.getText());
			f.setScore(score);
			this.setVisible(false);
			// jesli lista jest dluga, to usuwamy ostatni element, zeby zmiescic nasz nowy
			if (fameList.size()>=MAX_FAMELIST_SIZE) removeEntry(fameList.get(fameList.size()-1));
			HallofFame.this.addEntry(f);

			HallofFame.this.sortuj();
			HallofFame.this.refresh();

	}

		void showDialogWin(){
		setVisible(true);
		}
		void hideDialogWin(){
		setVisible(false);
		}



	} // end of inner class 

} // end of outer class
