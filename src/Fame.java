import java.util.*; // needed by ArrayList
import javax.swing.*; // needed by GUI
import java.awt.event.*; // needed by ActionListener, actionPerformed()
import java.awt.*; // needed by BorderLayout.CENTER
import java.io.*; // needed by implements Serializable
 
/*
Elementy klikniete dwukrotnie w napis Name podswietlaja sie na niebiesko.
Elementy klikniete jednokrotnie - na bialo
Jak sa podswietlone na niebiesko to dzialaja na nie przyciski Remove oraz Edit
*/
class Fame implements Serializable, Comparable<Fame> {
	private String name="ABC";
	private int score=7;
	 transient JLabel nameL;
	 transient JLabel scoreL;

	 public int compareTo(Fame f2){
	 return ((Integer)getScore()).compareTo(f2.getScore());
	 }

	Fame(){
	nameL=new JLabel();
	scoreL=new JLabel();
	nameL.addMouseListener(new SluchaczMyszyL());
	}

	Fame(String n,int s){
	this();
	setNamee(n);
	setScore(s);
	}
		class SluchaczMyszyL implements MouseListener {
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void mouseClicked(MouseEvent e) {
	System.out.println("Debug: Mouse clicked detected on "
	               + e.getComponent().getClass().getName()
				                  + "."+Fame.this.getNamee()+" ("+ e.getX() +","+ e.getY()+")" );
	if (e.getClickCount()==1)nameL.setForeground(Color.white);
	if (e.getClickCount()==2)nameL.setForeground(Color.blue);
	}

	} // end of inner class


	/*
	nadmuchuje pola transient - obiekt staje sie zywy i mozna z nim cos robic
	*/
	void blowAfterReadFromFile(){
	nameL=new JLabel();
	scoreL=new JLabel();
		scoreL.setText(Integer.toString(score));
		nameL.setText(name);
	nameL.addMouseListener(new SluchaczMyszyL());

	} // end of method

	void setScore(int s){
		score=s;
		scoreL.setText(Integer.toString(s));

	}

	void setNamee(String n){
		name=n;
		nameL.setText(n);
	}

	String getNamee(){
	return name;
	}

	int getScore(){
	return score;
	}

	JLabel getNameeL(){
	return nameL;
	}

	JLabel getScoreL(){
	return scoreL;
	}

public	String toString(){
	return name;
	}
} // end of class
