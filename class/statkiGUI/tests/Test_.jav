import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;


public class Test{

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("Test");
	}

	@org.junit.Test public void testSetX() {
		Pole pole = new Pole();
		pole.setX(5);
		Assert.assertEquals(5,pole.getX());
		pole.setX(9);
		Assert.assertEquals(0,pole.getX());
		pole.setX(-5);
		Assert.assertEquals(0,pole.getX());
		pole.setX(7);
		Assert.assertEquals(0,pole.getX());
	}


}

