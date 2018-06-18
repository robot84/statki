import org.junit.Assert;


// TODO: TC gdy probojemy postawic statek poza plansza

public class MapaOBSOLETETest {

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("MapaOBSOLETETest");
	}
// 8 TC, ktore testuja, czy mozliwe jest postawienie statku obok drugiego
 
	@org.junit.Test public void testMoznaPostawicStatekFALSE1() {
		MapaOBSOLETE m = new MapaOBSOLETE();
		Statek statek=new Statek();
		Statek statek2=new Statek();
		Pole[] p=new Pole[3];
		p[0]=new Pole();
		p[1]=new Pole();
		p[2]=new Pole();
		p[0].setX(0);
		p[0].setY(3);
		p[1].setX(0);
		p[1].setY(4);
		p[2].setX(0);
		p[2].setY(5);
		statek.setPole(p);
		m.dodajStatek(statek);
		p[0].setX(3);
		p[0].setY(2);
		p[1].setX(3);
		p[1].setY(3);
		p[2].setX(3);
		p[2].setY(4);
		statek2.setPole(p);
		m.dodajStatek(statek2);


		Assert.assertEquals(false,m.moznaPostawicStatek(1,2));
	}

	@org.junit.Test public void testMoznaPostawicStatekFALSE2() {
		MapaOBSOLETE m = new MapaOBSOLETE();
		Statek statek=new Statek();
		Statek statek2=new Statek();
		Pole[] p=new Pole[3];
		p[0]=new Pole();
		p[1]=new Pole();
		p[2]=new Pole();
		p[0].setX(0);
		p[0].setY(3);
		p[1].setX(0);
		p[1].setY(4);
		p[2].setX(0);
		p[2].setY(5);
		statek.setPole(p);
		m.dodajStatek(statek);
		p[0].setX(3);
		p[0].setY(2);
		p[1].setX(3);
		p[1].setY(3);
		p[2].setX(3);
		p[2].setY(4);
		statek2.setPole(p);
		m.dodajStatek(statek2);


		Assert.assertEquals(false,m.moznaPostawicStatek(1,3));
	}

	@org.junit.Test public void testMoznaPostawicStatekFALSE3() {
		MapaOBSOLETE m = new MapaOBSOLETE();
		Statek statek=new Statek();
		Statek statek2=new Statek();
		Pole[] p=new Pole[3];
		p[0]=new Pole();
		p[1]=new Pole();
		p[2]=new Pole();
		p[0].setX(0);
		p[0].setY(3);
		p[1].setX(0);
		p[1].setY(4);
		p[2].setX(0);
		p[2].setY(5);
		statek.setPole(p);
		m.dodajStatek(statek);
		p[0].setX(3);
		p[0].setY(2);
		p[1].setX(3);
		p[1].setY(3);
		p[2].setX(3);
		p[2].setY(4);
		statek2.setPole(p);
		m.dodajStatek(statek2);


		Assert.assertEquals(false,m.moznaPostawicStatek(1,4));
	}

	@org.junit.Test public void testMoznaPostawicStatekFALSE4() {
		MapaOBSOLETE m = new MapaOBSOLETE();
		Statek statek=new Statek();
		Statek statek2=new Statek();
		Pole[] p=new Pole[3];
		p[0]=new Pole();
		p[1]=new Pole();
		p[2]=new Pole();
		p[0].setX(0);
		p[0].setY(3);
		p[1].setX(0);
		p[1].setY(4);
		p[2].setX(0);
		p[2].setY(5);
		statek.setPole(p);
		m.dodajStatek(statek);
		p[0].setX(3);
		p[0].setY(2);
		p[1].setX(3);
		p[1].setY(3);
		p[2].setX(3);
		p[2].setY(4);
		statek2.setPole(p);
		m.dodajStatek(statek2);


		Assert.assertEquals(false,m.moznaPostawicStatek(5,2));
	}

	@org.junit.Test public void testMoznaPostawicStatekFALSE5() {
		MapaOBSOLETE m = new MapaOBSOLETE();
		Statek statek=new Statek();
		Statek statek2=new Statek();
		Pole[] p=new Pole[3];
		p[0]=new Pole();
		p[1]=new Pole();
		p[2]=new Pole();
		p[0].setX(0);
		p[0].setY(3);
		p[1].setX(0);
		p[1].setY(4);
		p[2].setX(0);
		p[2].setY(5);
		statek.setPole(p);
		m.dodajStatek(statek);
		p[0].setX(3);
		p[0].setY(2);
		p[1].setX(3);
		p[1].setY(3);
		p[2].setX(3);
		p[2].setY(4);
		statek2.setPole(p);
		m.dodajStatek(statek2);


		Assert.assertEquals(false,m.moznaPostawicStatek(5,3));
	}

	@org.junit.Test public void testMoznaPostawicStatekFALSE6() {
		MapaOBSOLETE m = new MapaOBSOLETE();
		Statek statek=new Statek();
		Statek statek2=new Statek();
		Pole[] p=new Pole[3];
		p[0]=new Pole();
		p[1]=new Pole();
		p[2]=new Pole();
		p[0].setX(0);
		p[0].setY(3);
		p[1].setX(0);
		p[1].setY(4);
		p[2].setX(0);
		p[2].setY(5);
		statek.setPole(p);
		m.dodajStatek(statek);
		p[0].setX(3);
		p[0].setY(2);
		p[1].setX(3);
		p[1].setY(3);
		p[2].setX(3);
		p[2].setY(4);
		statek2.setPole(p);
		m.dodajStatek(statek2);


		Assert.assertEquals(false,m.moznaPostawicStatek(5,4));
	}

	@org.junit.Test public void testMoznaPostawicStatekFALSE7() {
		MapaOBSOLETE m = new MapaOBSOLETE();
		Statek statek=new Statek();
		Statek statek2=new Statek();
		Pole[] p=new Pole[3];
		p[0]=new Pole();
		p[1]=new Pole();
		p[2]=new Pole();
		p[0].setX(3);
		p[0].setY(3);
		p[1].setX(4);
		p[1].setY(3);
		p[2].setX(5);
		p[2].setY(3);
		statek.setPole(p);
		m.dodajStatek(statek);


		Assert.assertEquals(false,m.moznaPostawicStatek(3,6));
	}

	@org.junit.Test public void testMoznaPostawicStatekFALSE8() {
		MapaOBSOLETE m = new MapaOBSOLETE();
		Statek statek=new Statek();
		Statek statek2=new Statek();
		Pole[] p=new Pole[3];
		p[0]=new Pole();
		p[1]=new Pole();
		p[2]=new Pole();
		p[0].setX(3);
		p[0].setY(3);
		p[1].setX(4);
		p[1].setY(3);
		p[2].setX(5);
		p[2].setY(3);
		statek.setPole(p);
		m.dodajStatek(statek);


		Assert.assertEquals(false,m.moznaPostawicStatek(3,2));
	}
}
