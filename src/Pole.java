public class Pole  {
int x=0;
int y=0;
static final int SHIP_SHOOTED=99; // some value > PLANSZA_MAX_X & > PLANSZA_MAX_Y
/* SHIP_SHOOTED ogranicza max wartosci  PLANSZA_MAX_X i  PLANSZA_MAX_Y!! */

public	void getAll(){
		System.out.println("*Pole.getAll() x: " + x + " y: " + y);
	}

public	void setX(int xx){
		if (xx<SHIP_SHOOTED && xx<Mapa.PLANSZA_MAX_X && xx>=0) x=xx;
		else x=-1;
	}

	public	void setY(int yy){
		if (yy<SHIP_SHOOTED && yy<Mapa.PLANSZA_MAX_Y && yy>=0) y=yy;
		else y=-1;
	}


	public	void setXshooted(){
		x=SHIP_SHOOTED;
	}

	public	void setYshooted(){
	y=SHIP_SHOOTED;
	}

	public boolean isShooted(){
	return (x==SHIP_SHOOTED && y==SHIP_SHOOTED);
	}

	public	int getX() {
		return x;
	}

	public	int getY() {
		return y;
	}
}


