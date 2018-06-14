import javax.swing.*;
import java.awt.*;

class MainWindow {
    static final int MIEJSCE_NA_LABEL_I_JTFstrzalW=40; // tyle, zeby sie zmiesily
    static final int PANEL2_SIZE_X=290; // czemu tyle? a zeby sie obrazek zmiescil
    JFrame ramka;
    ObrazekKoncaGry obrazKoncaGry=new ObrazekKoncaGry();
    JPanel panelCentralny; // ten zawiera dwa ponizsze panele:
    JPanel panel1; // tu bedzie mapa i jest z lewej strony okna
    JPanel panel2; // a ten jest pusty i jest z prawej strony okna
    Mapa mapa;
    Mapa.MapaGUI mapaGui;
    Statek[] stateczek;
    HallofFame hall;
    JLabel infoIloscRuchow;
    JLabel zapytajOstrzal;
    JTextField JTFstrzalW;
    JTextField koniecGry;
    Game rozgrywka;
    Pole poleOstrzeliwane =new Pole();

    MainWindow(){
//        mapaGui= mapa.new MapaGUI();
        panelCentralny=new JPanel();
        panel1=new JPanel();
        panel2=new JPanel();

        zapytajOstrzal=new JLabel("Podaj [xy]:");
        JTFstrzalW=new JTextField(4);
        JTFstrzalW.setColumns(4);

        koniecGry=new JTextField("Good game!");

        int panel12sizeY= mapaGui.getSizeY();
        //+MIEJSCE_NA_LABEL_I_JTFstrzalW;
        int panel1sizeX=(Mapa.PLANSZA_MAX_X*(Mapa.GAP_BEETWEEN_SQUARES+Mapa.SIZE_OF_SQUARE)+Mapa.LEFT_OFFSET_FROM_PANEL*2);
        panel2.setPreferredSize(new Dimension(PANEL2_SIZE_X, panel12sizeY));
        panel1.setPreferredSize(new Dimension(panel1sizeX, panel12sizeY));
        if (Logger.isDebugEnabled()) System.out.println("Panel1 x size:"+panel1sizeX);
        if (Logger.isDebugEnabled()) System.out.println("Panel1 y size:"+panel12sizeY);
        panelCentralny.add(panel1);
        panelCentralny.add(panel2);
        panel1.add(mapaGui);
        panel1.add(zapytajOstrzal);
        panel1.add(JTFstrzalW);
        //		panel2.setBackground(Color.darkGray);
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));

        JMenuBar menu=new JMenuBar();
        JMenu menuNew=new JMenu("New");
        JMenu menuScore=new JMenu("Score");
        JMenu menuHelp=new JMenu("Help");
        JMenuItem opcjaNowaGra=new JMenuItem("New game");
        JMenuItem opcjaAbout=new JMenuItem("About");
        JMenuItem opcjaShowScore=new JMenuItem("ShowScore");

        menuNew.add(opcjaNowaGra);
        menuHelp.add(opcjaAbout);
        menuScore.add(opcjaShowScore);
        menu.add(menuNew);
        menu.add(menuScore);
        menu.add(menuHelp);

        ramka = new JFrame("Statki " + Main.APP_VERSION);
        ramka.setJMenuBar(menu);
        ramka.getContentPane().add(BorderLayout.CENTER,panelCentralny);
        ramka.getContentPane().add(BorderLayout.SOUTH,infoIloscRuchow);
        ramka.pack();
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setVisible(true);
    }

}
