import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StatkiAppMenuBar {
    JMenuBar menuBar;
    JMenu menuNew;
    JMenu menuScore;
    JMenu menuHelp;
    JMenuItem opcjaNowaGra;
    JMenuItem opcjaAbout;
    JMenuItem opcjaShowScore;
    Kontroler kontroler;

    StatkiAppMenuBar(Kontroler kontroler){
    this.kontroler=kontroler;
    }

    void initMenu() {
        menuBar = new JMenuBar();
        menuNew = new JMenu("New");
        menuScore = new JMenu("Score");
        menuHelp = new JMenu("Help");
        opcjaNowaGra = new JMenuItem("New game");
        opcjaAbout = new JMenuItem("About");
        opcjaShowScore = new JMenuItem("ShowScore");

        opcjaNowaGra.addActionListener(new StatkiAppMenuBar.MenuNewGameListener());
        opcjaAbout.addActionListener(new StatkiAppMenuBar.MenuHelpAboutListener());
        //opcjaShowScore.addActionListener(new this.ShowScoreListener());


        menuNew.add(opcjaNowaGra);
        menuHelp.add(opcjaAbout);
        menuScore.add(opcjaShowScore);
        menuBar.add(menuNew);
        menuBar.add(menuScore);
        menuBar.add(menuHelp);

    }

    JMenuBar getJBarMenu(){
        return menuBar;
    }

    class MenuHelpAboutListener implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            kontroler.MenuHelpAboutClicked();

        }
    }

    class MenuNewGameListener implements ActionListener {

        public void	actionPerformed(ActionEvent ev){
            kontroler.startNewGame();
      /*      System.out.println("MenuNewGameListener clicked");
            Graphics gr=mapaGui.getGraphics();
            Graphics panell2=prawyPanel.getGraphics();
            gr.setColor(mapaGui.getBackground());
            gr.fillRect(0,0,300,300);
            panell2.setColor(mapaGui.getBackground());
            panell2.fillRect(0,0,300,300);
            System.out.println("Debug: mapaGui.getWidth(),mapaGui.getHeight(): ("+mapaGui.getWidth()+","+mapaGui.getHeight()+")");
            rozgrywka.rozpocznij();*/

        }
    }

        class ShowScoreListener implements ActionListener {
            public void actionPerformed(ActionEvent ev) {
                //hall.setVisible();

            }
        }

    }

