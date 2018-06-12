import org.junit.Assert;


public class StatekTest{

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("StatekTest");
	}

	@org.junit.Test public void test_Constructor() {
		Statek statek=new Statek();
		Pole[] p=statek.getPozycja();
		Assert.assertEquals(0,p[0].getX());
		Assert.assertEquals(0,p[0].getY());
		Assert.assertEquals(0,p[1].getX());
		Assert.assertEquals(0,p[1].getY());
		Assert.assertEquals(0,p[2].getX());
		Assert.assertEquals(0,p[2].getY());
		Assert.assertEquals(0,p[3].getX());
		Assert.assertEquals(0,p[3].getY());
		Assert.assertEquals(false,statek.getZatopiony());
		Assert.assertEquals(false,statek.getOstatnioZatopiony());

	}

	@org.junit.Test public void test_GenerujPolozenie_0() {
		Pole[] p=Statek.generujPolozenie(0);
		Assert.assertEquals(0,p[0].getX());
		Assert.assertEquals(0,p[0].getY());
		Assert.assertEquals(0,p[1].getX());
		Assert.assertEquals(1,p[1].getY());
		Assert.assertEquals(0,p[2].getX());
		Assert.assertEquals(2,p[2].getY());
		}
	@org.junit.Test public void test_GenerujPolozenie_1() {
		Pole[] p=Statek.generujPolozenie(1);
		Assert.assertEquals(2,p[0].getX());
		Assert.assertEquals(2,p[0].getY());
		Assert.assertEquals(2,p[1].getX());
		Assert.assertEquals(3,p[1].getY());
		Assert.assertEquals(2,p[2].getX());
		Assert.assertEquals(4,p[2].getY());
		}
	@org.junit.Test public void test_GenerujPolozenie_2() {

		Pole[] p=Statek.generujPolozenie(2);
		Assert.assertEquals(4,p[0].getX());
		Assert.assertEquals(4,p[0].getY());
		Assert.assertEquals(4,p[1].getX());
		Assert.assertEquals(5,p[1].getY());
		Assert.assertEquals(4,p[2].getX());
		Assert.assertEquals(6,p[2].getY());
		}

	@org.junit.Test public void test_GenerujPolozenie_minus5() {
		Pole[] p=Statek.generujPolozenie(-5);
		Assert.assertEquals(0,p[0].getX());
		Assert.assertEquals(0,p[0].getY());
		Assert.assertEquals(0,p[1].getX());
		Assert.assertEquals(1,p[1].getY());
		Assert.assertEquals(0,p[2].getX());
		Assert.assertEquals(2,p[2].getY());
		}

	@org.junit.Test public void test_GenerujPolozenie_5() {
		Pole[] p=Statek.generujPolozenie(5);
		Assert.assertEquals(0,p[0].getX());
		Assert.assertEquals(0,p[0].getY());
		Assert.assertEquals(0,p[1].getX());
		Assert.assertEquals(1,p[1].getY());
		Assert.assertEquals(0,p[2].getX());
		Assert.assertEquals(2,p[2].getY());
		}

	@org.junit.Test public void test_SetPoleGOODVALUE() {
		int[] x={3,4,5,6};
		int[] y={3,3,3,3};
		Statek statek=new Statek();

		Pole[] p= StatekTestHelper.utworzPola(Statek.MAX_ILOSC_MASZTOW);
		StatekTestHelper.ustawWartosciPol(x,y,p,Statek.MAX_ILOSC_MASZTOW);

		statek.setPole(p);
		for(int i=0;i<Statek.MAX_ILOSC_MASZTOW;i++)
			p[i]=null;
		p=null;
		p=statek.getPozycja();
		for(int i=0;i<Statek.MAX_ILOSC_MASZTOW;i++) {
			Assert.assertEquals(3+i, p[i].getX());
			Assert.assertEquals(3, p[i].getY());
		}

		}
		
	@org.junit.Test public void test_SetPoleBADVALUE() {
		// x,y - wymuszenia (wejscie)
		int[] x = {-5, 4, Pole.SHIP_SHOOTED, Mapa.PLANSZA_MAX_X + 1};
		int[] y = {-5, 4, Pole.SHIP_SHOOTED, Mapa.PLANSZA_MAX_Y + 1};
		// Out_x,Out_y - odpowiedzi (oczekiwane dane na wyjsciu)
		int[] Out_x = {-1, 4, -1, -1};
		int[] Out_y = {-1, 4, -1, -1};

		Statek statek = new Statek();
		Pole[] p= StatekTestHelper.utworzPola(Statek.MAX_ILOSC_MASZTOW);
		StatekTestHelper.ustawWartosciPol(x,y,p,Statek.MAX_ILOSC_MASZTOW);
		statek.setPole(p);
		for (int i = 0; i < Statek.MAX_ILOSC_MASZTOW; i++)
			p[i] = null;
		p = null;
		p = statek.getPozycja();
		for (int i = 0; i < Statek.MAX_ILOSC_MASZTOW; i++) {
			Assert.assertEquals(Out_x[i], p[i].getX());
			Assert.assertEquals(Out_y[i], p[i].getY());
		}
	}

	@org.junit.Test public void sprawdzCzyTrafiony() {
		int[] x={3,4,5,6};
		int[] y={3,3,3,3};
		int[] celne_x=x;
		int[] celne_y=y;
		int[] niecelne_x = {-1, 2, 7, Mapa.PLANSZA_MAX_X+1};
		int[] niecelne_y = {-1, 2, 7, Mapa.PLANSZA_MAX_Y+1};

		Statek statek=new Statek();
		Pole[] p= StatekTestHelper.utworzPola(Statek.MAX_ILOSC_MASZTOW);
		StatekTestHelper.ustawWartosciPol(x,y,p,Statek.MAX_ILOSC_MASZTOW);
		statek.setPole(p);

		Pole strzal=new Pole();
		int iloscStrzalowCelnych=Statek.MAX_ILOSC_MASZTOW; // ilosc prob, gdzie oczekujemy rezulatatu true
		for (int i = 0; i < iloscStrzalowCelnych; i++) {
			strzal.setX(celne_x[i]);
			strzal.setY(celne_y[i]);
			Assert.assertEquals(true, statek.sprawdzCzyTrafiony(strzal));
		}
		int iloscStrzalowNieCelnych=niecelne_x.length; // ilosc prob, gdzie oczekujemy rezulatatu false
		for (int i = 0; i < iloscStrzalowCelnych; i++) {
			strzal.setX(niecelne_x[i]);
			strzal.setY(niecelne_y[i]);
			Assert.assertEquals(false, statek.sprawdzCzyTrafiony(strzal));
		}

	
	}

	@org.junit.Test public void sprawdzCzyZatopiony_TRUE() {
		int[] x={3,4,5,6};
		int[] y={3,3,3,3};
		int[] celne_x=x;
		int[] celne_y=y;
		Statek statek=new Statek(); // TestHelper.utworzStatek();
		Pole[] p= StatekTestHelper.utworzPola(Statek.MAX_ILOSC_MASZTOW);
		StatekTestHelper.ustawWartosciPol(x,y,p,Statek.MAX_ILOSC_MASZTOW);

		statek.setPole(p);

		Pole strzal=new Pole();
		int iloscStrzalowCelnych=Statek.MAX_ILOSC_MASZTOW;
		for (int i = 0; i < iloscStrzalowCelnych; i++) {
			strzal.setX(celne_x[i]);
			strzal.setY(celne_y[i]);
			statek.sprawdzCzyTrafiony(strzal);
		}
		Assert.assertEquals(true,statek.sprawdzCzyZatopiony());

	}

	@org.junit.Test public void sprawdzCzyZatopiony_FALSE() {

		int[] x={3,4,5,6};
		int[] y={3,3,3,3};
		int[] Out_x = {3, 4, 7, 6};
		int[] Out_y = {3, 3, 3, 3};
		Statek statek=new Statek();
		Pole[] p= StatekTestHelper.utworzPola(Statek.MAX_ILOSC_MASZTOW);
		StatekTestHelper.ustawWartosciPol(x,y,p,Statek.MAX_ILOSC_MASZTOW);
		statek.setPole(p);

		Pole strzal=new Pole();
		int iloscStrzalow=Statek.MAX_ILOSC_MASZTOW;
		for (int i = 0; i < iloscStrzalow; i++) {
			strzal.setX(Out_x[i]);
			strzal.setY(Out_y[i]);
			statek.sprawdzCzyTrafiony(strzal);
		}
		Assert.assertEquals(false,statek.sprawdzCzyZatopiony());

}

	@org.junit.Test public void sprawdzCzyOstatnioZatopiony_TRUE() {

		int[] x={1,1,1,1};
		int[] y={1,2,3,4};
		int[] celne_x=x;
		int[] celne_y=y;
		Statek statek=new Statek();
		Pole[] p= StatekTestHelper.utworzPola(Statek.MAX_ILOSC_MASZTOW);
		StatekTestHelper.ustawWartosciPol(x,y,p,Statek.MAX_ILOSC_MASZTOW);
		statek.setPole(p);

		Pole strzal=new Pole();
		int iloscStrzalow=Statek.MAX_ILOSC_MASZTOW;
		for (int i = 0; i < iloscStrzalow; i++) {
			strzal.setX(celne_x[i]);
			strzal.setY(celne_y[i]);
			statek.sprawdzCzyTrafiony(strzal);
		}
		Assert.assertEquals(true,statek.sprawdzCzyOstatnioZatopiony());
	
	}
	
	@org.junit.Test public void sprawdzCzyOstatnioZatopiony_FALSE() {

		int[] x={3,4,5,6};
		int[] y={3,3,3,3};
		int[] Out_x = {3, 4, 7, 6};
		int[] Out_y = {3, 3, 3, 3};
		Statek statek=new Statek();
		Pole[] p= StatekTestHelper.utworzPola(Statek.MAX_ILOSC_MASZTOW);
		StatekTestHelper.ustawWartosciPol(x,y,p,Statek.MAX_ILOSC_MASZTOW);
		statek.setPole(p);


		Pole strzal=new Pole();
		int iloscStrzalow=Statek.MAX_ILOSC_MASZTOW;
		for (int i = 0; i < iloscStrzalow; i++) {
			strzal.setX(Out_x[i]);
			strzal.setY(Out_y[i]);
			statek.sprawdzCzyTrafiony(strzal);
		}
		Assert.assertEquals(false,statek.sprawdzCzyOstatnioZatopiony());
	
	}

	@org.junit.Test public void sprawdzCzyOstatnioZatopiony_FALSE2() {

		int[] x={3,4,5,6};
		int[] y={3,3,3,3};
		int[] Out_x = {3, 4, 5, 6};
		int[] Out_y = {3, 3, 3, 3};
		Statek statek=new Statek();
		Pole[] p= StatekTestHelper.utworzPola(Statek.MAX_ILOSC_MASZTOW);
		StatekTestHelper.ustawWartosciPol(x,y,p,Statek.MAX_ILOSC_MASZTOW);
		statek.setPole(p);


		Pole strzal=new Pole();
		int iloscStrzalow=Statek.MAX_ILOSC_MASZTOW;
		for (int i = 0; i < iloscStrzalow; i++) {
			strzal.setX(Out_x[i]);
			strzal.setY(Out_y[i]);
			statek.sprawdzCzyTrafiony(strzal);
		}
		statek.sprawdzCzyTrafiony(strzal);
		Assert.assertEquals(false,statek.sprawdzCzyOstatnioZatopiony());
	
	}
}

