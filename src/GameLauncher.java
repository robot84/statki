class GameLauncher  {


	public static void main (String[] args) {
		Game partyjkaGry=new Game();

		if (args.length>0 && args[0].equals("-d") ) Logger.setDebugEnabled();
		if (args.length>0 && args[0].equals("-dd") ) {
			Logger.setDebugEnabled();
			Logger.setDeepDebugEnabled();
		}

		Logger.deepDebug("GameLauncher.partyjkaGry:"+partyjkaGry.hashCode());
		partyjkaGry.inicjalizuj();
		partyjkaGry.rozpocznij();
		//  ustawia pozycje statkow i stawia statki na mapie:
	}




}
