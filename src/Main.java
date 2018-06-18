// public / protected / private abstract static final <method/var name>
public class Main {

    public static final String BUILD_NUMBER = "865";
    static final String APP_VERSION="v0.9 build "+BUILD_NUMBER;

	public static void main (String[] args) {
		// trza podzielic to na odpalanie samego okna glownego
		// oraz osobno nowej gry w tym oknie
		//7:25 start
        // dodalem build number w 15 minut. :):)

		if (args.length>0 && args[0].equals("-d") ) Logger.setDebugEnabled();
		if (args.length>0 && args[0].equals("-dd") ) {
			Logger.setDebugEnabled();
			Logger.setDeepDebugEnabled();
		}

		// utworz kontroler i model, przekaz sterowanie do kontrolera
		Kontroler mojKontroler=new Kontroler();



		/*
		nowy koncept jest taki ze Game tutaj nie uruchamiamy
		Game jest tworzone dopiero jak user da New-Game z menuBar.
		Poczatkowo apka tworzy tylko GUI z menuBar. nie odpala gry od razu
		 */

		/*Game partyjkaGry=new Game();
		Logger.deepDebug("Main.partyjkaGry:"+partyjkaGry.hashCode());
		partyjkaGry.inicjalizuj();
		partyjkaGry.rozpocznij();*/

	}




}
