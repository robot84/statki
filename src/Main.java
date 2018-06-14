// public / protected / private abstract static final <method/var name>
public class Main {

    public static final String BUILD_NUMBER = "710";
    static final String APP_VERSION="v0.9 build "+BUILD_NUMBER;

	public static void main (String[] args) {
		// trza podzielic to na odpalanie samego okna glownego
		// oraz osobno nowej gry w tym oknie
		//7:25 start
        // dodalem build number w 15 minut. :):)
		//MainWindow mainWindow=new MainWindow();
		Game partyjkaGry=new Game();

		if (args.length>0 && args[0].equals("-d") ) Logger.setDebugEnabled();
		if (args.length>0 && args[0].equals("-dd") ) {
			Logger.setDebugEnabled();
			Logger.setDeepDebugEnabled();
		}

		//Logger.deepDebug("Main.partyjkaGry:"+partyjkaGry.hashCode());
		partyjkaGry.inicjalizuj();
		partyjkaGry.rozpocznij();
		//  ustawia pozycje statkow i stawia statki na mapie:
	}




}
