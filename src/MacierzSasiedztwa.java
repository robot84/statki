class MacierzSasiedztwa{
static final int ILOSC_ELEMENTOW_MACIERZY_SASIEDZTWA=8;
	PoleMacierzySasiedztwa[] pola=new PoleMacierzySasiedztwa[ILOSC_ELEMENTOW_MACIERZY_SASIEDZTWA];
	static	int [][] p={{0,1},{0,-1},{1,-1},{1,0},{1,1},{-1,-1},{-1,0},{-1,1}};

   public static void main(String[] args){

MacierzSasiedztwa.wyswietlMacierzSasiedztwa();
}
	MacierzSasiedztwa(){
		for(int i=0;i<ILOSC_ELEMENTOW_MACIERZY_SASIEDZTWA;i++) {
			pola[i]=new PoleMacierzySasiedztwa();
			pola[i].setX(p[i][0]);
			pola[i].setY(p[i][1]);
		}
	}
	static void wyswietlMacierzSasiedztwa() {
		for(int i=0;i<ILOSC_ELEMENTOW_MACIERZY_SASIEDZTWA;i++) 
System.out.println(p[i][0]+" "+p[i][1]);
		

	}


}
