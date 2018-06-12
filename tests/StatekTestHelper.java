public class StatekTestHelper {


    public static Pole[] utworzPola(int liczbaPol){
        Pole[] p=new Pole[liczbaPol];
        for (int i = 0; i < liczbaPol; i++) {
            p[i] = new Pole();
        }
        return p;
    }

    static void ustawWartosciPol(int[] x, int[]y, Pole[] p, int liczbaPol) {
        for (int i = 0; i < liczbaPol; i++) {
            p[i].setX(x[i]);
            p[i].setY(y[i]);
        }
    }


}
