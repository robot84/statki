public class FabrykaStatkow {
    Statek utworz4masztowiec() {
        return new Statek(4);
    }

    Statek utworz3masztowiec() {
        return new Statek(3);
    }

    Statek utworz2masztowiec() {
        return new Statek(2);
    }

    Statek utworz1masztowiec() {
        return new Statek(1);
    }

    Statek utworzNmasztowiec(int iloscMasztow) {
        return new Statek(iloscMasztow);
    }

    Statek[] utworzFloteStatkow(int tyle4masztowcow, int tyle3masztowcow, int tyle2masztowcow,
                                int tyle1masztowcow) {
        // TBD
        int wszystkichStatkow=tyle4masztowcow+tyle3masztowcow+tyle2masztowcow+tyle1masztowcow;
        Statek[] flota=new Statek[wszystkichStatkow];


        for(int i=0;i<tyle4masztowcow;i++) {
            flota[wszystkichStatkow-1-i]=utworz4masztowiec();
        }
        for(int i=0;i<tyle3masztowcow;i++) {
            flota[wszystkichStatkow-1-tyle4masztowcow-i]=utworz3masztowiec();
        }
        for(int i=0;i<tyle2masztowcow;i++) {
            flota[wszystkichStatkow-1-tyle4masztowcow-tyle3masztowcow-i]=utworz2masztowiec();
        }
        for(int i=0;i<tyle1masztowcow;i++) {
            flota[wszystkichStatkow-1-tyle4masztowcow-tyle3masztowcow-tyle2masztowcow-i]=utworz1masztowiec();
        }
        return flota;
    }

}
