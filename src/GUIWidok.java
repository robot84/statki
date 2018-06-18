import javax.swing.*;
import java.awt.*;

class GUIWidok {
    static final int MIEJSCE_NA_LABEL_I_JTFstrzalW = 40; // tyle, zeby sie zmiesily
    static final int PANEL2_SIZE_X = 290; // czemu tyle? a zeby sie obrazek zmiescil
    JFrame ramka;
    Okno oknoGlowne;
    ObrazekKoncaGry obrazKoncaGry = new ObrazekKoncaGry();
    JPanel panelGlowny; // ten zawiera dwa ponizsze panele:
    JPanel lewyPanel; // tu bedzie mapaOBSOLETE i jest z lewej strony okna
    JPanel prawyPanel; // a ten jest pusty i jest z prawej strony okna
    HallofFame hall;
    JLabel zapytajOstrzal;
    JTextField JTextFieldStrzalW;
    JTextField koniecGry;
    MapaOBSOLETE mapaOBSOLETE;
    MapaGUI mapaGui;
    StatkiAppMenuBar menuOknaGlownego;
    Kontroler kontroler;


    GUIWidok(Kontroler kontroler) {
        this.kontroler = kontroler;
        //this.mapaOBSOLETE=new MapaOBSOLETE();
    }

    void tworzGUI(){
        panelGlowny = new JPanel();
        lewyPanel = new JPanel();
        prawyPanel = new JPanel();
        zapytajOstrzal = new JLabel("Podaj [xy]:");
        JTextFieldStrzalW = new JTextField(4);
        JTextFieldStrzalW.setColumns(4);
        koniecGry = new JTextField("Good game!");

        /* rezerwacja miejsca na panel mapy*/
        mapaGui = new MapaGUI(new MapaModel());
        int wysokoscPaneli = mapaGui.getYsize()+MIEJSCE_NA_LABEL_I_JTFstrzalW;
        lewyPanel.setPreferredSize(new Dimension(mapaGui.getXsize(), wysokoscPaneli));
        prawyPanel.setPreferredSize(new Dimension(PANEL2_SIZE_X, wysokoscPaneli));
        panelGlowny.add(lewyPanel);
        panelGlowny.add(prawyPanel);
        //lewyPanel.add(zapytajOstrzal);
        //lewyPanel.add(JTextFieldStrzalW);
        //prawyPanel.setBackground(Color.darkGray);
        lewyPanel.setLayout(new BoxLayout(lewyPanel, BoxLayout.Y_AXIS));

        menuOknaGlownego=new StatkiAppMenuBar(kontroler);
        menuOknaGlownego.initMenu();

        oknoGlowne=new Okno();
        oknoGlowne.ustawTytul("Statki " + Main.APP_VERSION);
        oknoGlowne.osadzMenu(menuOknaGlownego.getJBarMenu());
        oknoGlowne.dodajPanelCentralnie(panelGlowny);
        oknoGlowne.pokazOkno();

        //oknoGlowne.getContentPane().add(BorderLayout.SOUTH, infoIloscRuchow);

        if (Logger.isDebugEnabled()) System.out.println("LewyPanel x size:" + mapaGui.getXsize());
        if (Logger.isDebugEnabled()) System.out.println("LewyPanel y size:" + wysokoscPaneli);
    }

    void refresh(){
        mapaGui.refresh();
        panelGlowny.repaint();
        panelGlowny.revalidate();
    }

    void addMapToWindow(MapaGUI mapka){
    addMapToLeftPanel(mapka);
    }

    private void addMapToLeftPanel(MapaGUI mapka){
        lewyPanel.add(mapka);
    }

}
