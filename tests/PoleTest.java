import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PoleTest {
final int INPUT=0;
final int OUTPUT=1;

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("PoleTest");
	}

	@org.junit.Test
	public void setX_setsProperly() throws NoSuchFieldException, IllegalAccessException {
		final Pole pole = new Pole();
		MapaModel mapa=new MapaModel();
		// tablica z parami {wejscie, wyjscie} (albo {wejscie,wartosc_oczekiwana} )
		int[][] inout= {
				{0, 0},
				{1, 1},
				{2, 2},
				{3, 3},
				{4, 4},
				{5, 5},
				{-1, -1},
				{-10, -1},
				{mapa.numberOfFieldsInXdirection()-1, mapa.numberOfFieldsInXdirection()-1},
				{mapa.numberOfFieldsInXdirection(), -1},
				{mapa.numberOfFieldsInXdirection()+1, -1},
				{mapa.numberOfFieldsInXdirection()+10, -1},
				{Pole.SHIP_SHOOTED,-1}
		};
		for (int i = 0; i < inout.length; i++) {

			pole.setX(inout[i][INPUT]);

			final Field field = pole.getClass().getDeclaredField("x");
			field.setAccessible(true);
			assertEquals("Fields didn't match", field.get(pole), inout[i][OUTPUT]);
		}
	}

	@org.junit.Test
	public void setY_setsProperly() throws NoSuchFieldException, IllegalAccessException {
		final Pole pole = new Pole();
		final MapaModel mapa=new MapaModel();
		// tablica z parami {wejscie, wyjscie} (albo {wejscie,wartosc_oczekiwana} )
		int[][] inout= {
				{0, 0},
				{1, 1},
				{2, 2},
				{3, 3},
				{4, 4},
				{5, 5},
				{-1, -1},
				{-10, -1},
				{mapa.numberOfFieldsInXdirection()-1, mapa.numberOfFieldsInXdirection()-1},
				{mapa.numberOfFieldsInXdirection(), -1},
				{mapa.numberOfFieldsInXdirection()+1, -1},
				{mapa.numberOfFieldsInXdirection()+10, -1},
				{Pole.SHIP_SHOOTED,-1}
		};
		for (int i = 0; i < inout.length; i++) {

			pole.setY(inout[i][INPUT]);

			final Field field = pole.getClass().getDeclaredField("y");
			field.setAccessible(true);
			assertEquals("Fields didn't match", field.get(pole), inout[i][OUTPUT]);
		}
	}


	@org.junit.Test
	public void constantSHIP_SHOOTED_hasProperValue() {
		final MapaModel mapa=new MapaModel();

		assertTrue("Condition not true", Pole.SHIP_SHOOTED >mapa.numberOfFieldsInXdirection() );
		assertTrue("Condition not true", Pole.SHIP_SHOOTED >mapa.numberOfFieldsInYdirection() );
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

