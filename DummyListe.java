//erstellt am 15.10.2023

package listen;
public class DummyListe implements DSAListe {

	int anzahl=0;
	int maxanzahl= 100;
	double resizefaktor2= 1.5;
	String[]array1;

	public DummyListe(){
			array1= new String[maxanzahl];
	}


	@Override
	public boolean anhaengen(String s) {
		String [] neuesArray;
		int neueAnzahl=0;
		if(anzahl>= maxanzahl){
			 neueAnzahl= (int)(maxanzahl*resizefaktor2);
			neuesArray= new String[neueAnzahl];
			for (int i=0; i<array1.length;i++){
				neuesArray[i]=array1[i];
			}
				neuesArray[array1.length+1]= s;
				array1=neuesArray;
				maxanzahl= neueAnzahl;

				return true;
		}
		array1[anzahl++]=s;
		
		// wenn arrayKapazität voll geworden ist
		


	
		

		return true;
	}

	@Override
	public boolean einfuegen(String s, int index) {
		String[] arrayPlus1= new String[maxanzahl++];
// array wird ueberschrieben und 
//maxanzahl wird zu der laenge dieses arrays 
		if(array1.length <= index){
			for(int n= 0;n<array1.length;n++){
				arrayPlus1[n]= array1[n];
			}

			for(int n= index;n<arrayPlus1.length;n++){
				String current= arrayPlus1[n];
				arrayPlus1[n+1]= current;
				arrayPlus1[index]=s;
			}

			anzahl= arrayPlus1.length;
			array1= arrayPlus1;
			return true;

		}
		// das ist der fall wenn array nicht out of bounds 
		// gehen wuerde
		for(int i=index+1;i<array1.length;i++){
				String current= array1[i-1];
			array1[i]= current;
			array1[index]=s;
		}
		return true;
	}

	@Override
	public boolean loeschen(int index) {
	  array1[index]=null;
		String[] arrayMinus1= new String[maxanzahl--];

		// neues array erstellen mit der grösse von array1.length-1
		// dann neues array mit array1 ueberschreiben nur wenn array1[i]!null ist
		
		for(int i=0; i<array1.length;i++){
			if(array1[i]!= null){
				arrayMinus1[i]= array1[i];
			}
		}

		array1= arrayMinus1;
		anzahl--;

		return true;
	}

	@Override
	public boolean ersetzen(String s, int index) {
			array1[index]= s;
			

		return true;
	}

	@Override
	public String holen(int index) {
		return null;
	}

//	@Override
//	public String holen(int index) {
		//String returnString= "";

//		return array1[index];
//	}

	@Override
	public int getAnzahl() {
		return anzahl;
	}

}

// vorgegenes Interfrace

public interface DSAListe {
// Ein Element hinten anf�gen	
	boolean anhaengen(String s);
// Ein Element auf einer bestimmten Position einf�gen
	boolean einfuegen(String s, int index);
// Ein Element auf einer bestimmten Position l�schen
	boolean loeschen(int index);
// Den Inhalt eines Elements austauschen
	boolean ersetzen(String s, int index);
// Den Inhalt eines Elements holen
	String holen(int index);
// Die Anzahl der Elemente abfragen 
	int getAnzahl();
// Vorgabeimplementierung um alle Elemente auszugeben
	default void alleAusgeben() {
		for (int i = 0; i < getAnzahl(); i++) {
			System.out.printf("%8d : %s\n", i, holen(i));
		}
		System.out.println("------");
	};
// Vorgabeimplementierung um alle Elemente zu l�schen, 
// ohne den Container selbst zu l�schen
	default void alleLoeschen() {
		for (int i = getAnzahl()-1; i>=0 ; --i) {
			loeschen(i);
		}
	};
}


// Klasse mit main zum testen:
public class ListenMain {
/* 
 * Hauptprogramm zum Testen der Listenimplementierungen
 * Der gew�nschte Listentyp muss jeweils auskommentiert werden
 * Ansonsten brauchen Sie in dieser Klasse keine �nderungen zu machen
 * 
 * Die Ausgabe des Programms soll sein
 * 
 *     0 : Hallo
       1 : Test
       2 : DSA
------
       0 : Hallo
       1 : DSA
------
       0 : Eintrag000
       1 : Eintrag001
       2 : Eintrag002
       3 : Eintrag003
       4 : Zwischen3und4
       5 : Eintrag004
       6 : Eintrag006
       7 : Sieben
       8 : Eintrag008
       9 : Eintrag009
------

 */
	public static void main(String[] args) {
//		DSAListe liste = new LinkedListe();
//		DSAListe liste = new ArrayListe();
		DSAListe liste = new DummyListe();
		test1(liste);
		test2(liste);
	}

	static void test1(DSAListe liste) {
		liste.anhaengen("Hallo");
		liste.anhaengen("Test");
		System.out.println(liste.holen(0)+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
		liste.anhaengen("DSA");
		System.out.println("euuewwdwqwd");
		liste.alleAusgeben();
		System.out.println("rfwrefgwg");
		liste.loeschen(1);
		System.out.println("tress");
		liste.alleAusgeben();
	}

	static void test2(DSAListe liste) {
		liste.alleLoeschen();
		for(int i = 0;i<10;i++) {
			liste.anhaengen(String.format("Eintrag%03d",i));
		}
		liste.alleAusgeben();
		System.out.println("8888888888");
		liste.ersetzen("Sieben", 7);
		liste.alleAusgeben();
		System.out.println("hier oben");
		liste.einfuegen("Zwischen3und4", 4);
		liste.loeschen(6);
		System.out.println("cinqo");
		liste.alleAusgeben();
	}
	
}


