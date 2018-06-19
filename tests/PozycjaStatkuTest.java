import org.junit.Assert;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class PozycjaStatkuTest {

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("PozycjaStatkuTest");
    }
/*
* czy mozna testowac setter czegos, skoro nie przetestowalismy jeszcze gettera, a chcemy go uzyc w tescie?
* ale jak przetestowac getter, skoro trza uzyc settera, zeby cos ustawic, a on jest nieprzetestowany??
* i kolo sie zamyka
* */
    /*
    * naming tests:
    * MethodName_StateUnderTest_ExpectedBehavior
    */
@org.junit.Test
public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {
    //given
    final Pole pole = new Pole();

    //when
    pole.setX(2);

    //then
    final Field field = pole.getClass().getDeclaredField("value");
    field.setAccessible(true);
    assertEquals("Fields didn't match", field.get(pole), 2);
}

    @org.junit.Test
    public void test1() {
        Statek statek=new Statek();
        Pole[] pole = new Pole[1];
        pole[0]=new Pole();

        statek.ustawPozycje(pole);

      //  assertEquals(5, pole.getX());
    }

    @org.junit.Test
    public void test2() {
        Pole pole = new Pole();
        pole.setX(5);
        assertEquals(5, pole.getX());
    }

    @org.junit.Test
    public void test3() {
        Pole pole = new Pole();
        pole.setX(5);
        assertEquals(5, pole.getX());
    }

}
