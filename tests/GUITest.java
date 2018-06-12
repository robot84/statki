public class GUITest{

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("GUITest");
	}

	@org.junit.Test public void testKoniecGry() throws InterruptedException {
		Statek[] s=new Statek[3];
		Mapa ma=new Mapa();
                GUI gui=new GUI(ma,s);

		for(int i=0;i<3;i++)
			s[i]=new Statek();
		for(int i=0;i<3;i++)
			s[i].setPole(Statek.generujPolozenie(i));

Thread.sleep(1000);
		//zatop je wszystkie
		Pole strzal=new Pole();
		strzal.setX(3);
		strzal.setY(3);
		s[0].stoiNaPolu(strzal);
Thread.sleep(1000);
		strzal.setX(4);
		strzal.setY(4);
		s[1].stoiNaPolu(strzal);
Thread.sleep(1000);
		strzal.setX(5);
		strzal.setY(5);
		s[2].stoiNaPolu(strzal);
Thread.sleep(2000);
		//gui.wyswietlObrazekKoncaGryIodlaczSluchaczaJTF();
	}


}

