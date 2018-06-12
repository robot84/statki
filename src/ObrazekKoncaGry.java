import javax.swing.*;
import java.awt.*;


public class ObrazekKoncaGry extends JPanel{
		Image gratulacjeObrazek=(new ImageIcon(getClass().getResource("/img/gratulacje.jpg"))).getImage();


	public void paintComponent(Graphics g) {
		g.drawImage(gratulacjeObrazek,5,10,this);
	}
}

