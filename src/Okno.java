import javax.swing.*;
import java.awt.*;

public class Okno  extends JFrame {
    //JFrame okno;

    Okno(){
        utworzOkno();
    }

    void utworzOkno(){
      //  okno =new JFrame();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void ustawTytul(String tytul){
        this.setTitle(tytul);
    }

    void osadzMenu(JMenuBar menu){
        this.setJMenuBar(menu);
    }

    void dodajPanel(JPanel panel,String layout){
        this.getContentPane().add(layout, panel);
    }

    void dodajPanelCentralnie(JPanel panel){
        dodajPanel(panel,BorderLayout.CENTER);
    }

    void pokazOkno(){
        this.pack();
        this.setVisible(true);
    }

}
