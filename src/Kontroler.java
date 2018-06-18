import javax.swing.*;

class Kontroler {
    /*
    * udostepnia metody wywolywane przez UI (GUIWidok) i przeksztalca je na zbior polecen
    * dla modelu.
    * Jezeli trzeba przy okazji cos zmienic w samym UI, (w zwiazku z poleceniem wyslanym z UI)
    * to robi to.
    *
    * to kontroler kontroluje cala gre
    *
    * Modele:
    * mainWindowModel
    * roundModel - partyjka gry
    * mapaOBSOLETE
    * procesorStrzalow
    * */
    MapaModel mapa;
    MapaGUI mapaGui;
    FabrykaStatkow fabrykaStatkow;
    GUIWidok guiWidok;


        Kontroler(){
            guiWidok =new GUIWidok(this);
            guiWidok.tworzGUI();
            //model.inicjuj
        }

    void startNewGame(){
        // utworz wsio :) , , statki, procesor strzalu
        mapa =new MapaModel();
        mapaGui =new MapaGUI(mapa);
        fabrykaStatkow=new FabrykaStatkow();

        mapa.inicjujMapeStrzalow();
        guiWidok.addMapToWindow(mapaGui);
        guiWidok.refresh();
    }

    void exitApplication(){

    }
    void showAboutMessage(){

    }
    void showScoreWindow(){

    }

    public void MenuHelpAboutClicked() {
        // TODO HELPME: czy tu powinienem wywolac raczej metode, zeby ukryc implementacje?
        // a jesli tak, to w ktorej klasie te konkretna metode umiescic?
        // moze stworzyc nowa klase GameWindows i utworzyc w niej obiekt infoWindow, albo AboutWindow
        // utworzyc obiekt tej klasy i wywolac jego metode createWindow();
        JOptionPane.showMessageDialog(null, "Statki!!\nAutor Robert Zabkiewicz\n(c) 2017-2018", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
    }
}
