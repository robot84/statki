import org.junit.Assert;


public class PoleTest {

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("PoleTest");
	}

	@org.junit.Test
	public void testSetX() {
		Pole pole = new Pole();
		pole.setX(5);
		Assert.assertEquals(5, pole.getX());
		pole.setX(0);
		Assert.assertEquals(0, pole.getX());
		pole.setX(Pole.SHIP_SHOOTED);
		Assert.assertEquals(-1, pole.getX());
		pole.setX(Pole.SHIP_SHOOTED + 1);
		Assert.assertEquals(-1, pole.getX());
		pole.setX(-5);
		Assert.assertEquals(-1, pole.getX());
		pole.setX(MapaOBSOLETE.PLANSZA_MAX_X);
		Assert.assertEquals(-1, pole.getX());
		pole.setX(MapaOBSOLETE.PLANSZA_MAX_X - 1);
		Assert.assertEquals(MapaOBSOLETE.PLANSZA_MAX_X - 1, pole.getX());
		pole.setX(MapaOBSOLETE.PLANSZA_MAX_X + 10);
		Assert.assertEquals(-1, pole.getX());
	}

	@org.junit.Test
	public void testSetY() {
		Pole pole = new Pole();
		pole.setY(5);
		Assert.assertEquals(5, pole.getY());
		pole.setY(0);
		Assert.assertEquals(0, pole.getY());
		pole.setY(Pole.SHIP_SHOOTED);
		Assert.assertEquals(-1, pole.getY());
		pole.setY(Pole.SHIP_SHOOTED + 1);
		Assert.assertEquals(-1, pole.getY());
		pole.setY(-5);
		Assert.assertEquals(-1, pole.getY());
		pole.setY(MapaOBSOLETE.PLANSZA_MAX_Y - 1);
		Assert.assertEquals(MapaOBSOLETE.PLANSZA_MAX_Y - 1, pole.getY());
		pole.setY(MapaOBSOLETE.PLANSZA_MAX_Y);
		Assert.assertEquals(-1, pole.getY());
		pole.setY(MapaOBSOLETE.PLANSZA_MAX_Y + 10);
		Assert.assertEquals(-1, pole.getY());
	}

	@org.junit.Test
	public void testSetXshooted() {
		Pole pole = new Pole();
		pole.setXshooted();
		Assert.assertEquals(Pole.SHIP_SHOOTED, pole.getX());
	}

	@org.junit.Test
	public void testSetYshooted() {
		Pole pole = new Pole();
		pole.setYshooted();
		Assert.assertEquals(Pole.SHIP_SHOOTED, pole.getY());
	}

	@org.junit.Test
	public void testGetX() {
		Pole pole = new Pole();
		pole.setX(5);
		Assert.assertEquals(5, pole.getX());
		pole.setX(0);
		Assert.assertEquals(0, pole.getX());
		pole.setX(-5);
		Assert.assertEquals(-1, pole.getX());
	}

	@org.junit.Test
	public void testGetY() {
		Pole pole = new Pole();
		pole.setY(5);
		Assert.assertEquals(5, pole.getY());
		pole.setY(0);
		Assert.assertEquals(0, pole.getY());
		pole.setY(-5);
		Assert.assertEquals(-1, pole.getY());
	}

	@org.junit.Test
	public void testisShooted() {
		Pole pole = new Pole();
		pole.setYshooted();
		Assert.assertEquals(false, pole.isShooted());
		pole.setXshooted();
		Assert.assertEquals(true, pole.isShooted());
		pole.setY(0);
		Assert.assertEquals(false, pole.isShooted());

	}
}

