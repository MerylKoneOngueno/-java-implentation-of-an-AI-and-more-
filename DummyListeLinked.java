// erstellt am 20.10.2023
package listen;
// eigene Implementation einer LinkedList
public class DummyListeLinked implements DSAListe{
    
 
  LinkedListeElement anker;
	LinkedListeElement letztes;
	int anzahl;

    public DummyListeLinked(){
		anker= null;
		letztes= null;
		anzahl=0;
}

	@Override
	public boolean anhaengen(String s) {

		LinkedListeElement el= new LinkedListeElement(s, null);
// wenn anker null wird string s zum anker	
// weil anker ja first element wird			
			if(anzahl==0){
				anker= el;
				letztes= anker;
				//anzahl++;
			}
			else{
				letztes.setNachfolger(el);
				letztes= el;
			}
		//	LinkedListeElement vorgaenger= letztes;
		//el= new LinkedListeElement(s, vorgaenger);
		anzahl++;

		//letztes=el ;

        return true;
	}

	@Override
	public boolean einfuegen(String s, int index) {
		if (index > anzahl) {
			return false;
		}
		if (index == 0) {
			anker = new LinkedListeElement(s, anker);
		} else {
			LinkedListeElement el = holeElement(index - 1);
			el.setNachfolger(new LinkedListeElement(s, el.getNachfolger()));
		}
		anzahl++;
		return true;
	}

	@Override
	public boolean loeschen(int index) {
		if (index > anzahl - 1) {
			return false;
		}
// Wenn das zu loeschende Element der Anker ist,
// das nachfolgende Element zum Anker machen.
		if (index == 0) {
			if (anzahl == 1) {
				anker = null;
				letztes = null;
				anzahl = 0;
				return true;
			}
			anker = anker.getNachfolger();
// sonst das vorherige Element holen
// und als Nachfolger den nach-nachfolger eintragen
		} else {
			LinkedListeElement el = holeElement(index - 1);
			el.nachfolger = el.getNachfolger().getNachfolger();
		}
		anzahl--;
		return true;
	}






	@Override
	public boolean ersetzen(String s, int index) {
		LinkedListeElement el = anker;
		el = holeElement(index );
		el.s = s;
		return true;
	}



	public String holen1(int index){
		LinkedListeElement le = anker;
		for (int i = 0; i < index  ; i++) {
			if(i < index )
			le= le.getNachfolger();
		}
		return le.getS();
	}

	@Override
	public String holen(int index) {
		if (index > anzahl - 1) {
			return null;
		}
		LinkedListeElement el = holeElement(index);
		return el.s;
	}



	@Override
	public int getAnzahl() {
		return anzahl;
	}


	public LinkedListeElement holeElement(int index) {
		if (index > anzahl - 1) {
			return null;
		}
		int pos = 0;
// Anker ist das "Root" Element
		LinkedListeElement el = anker;
// Jetzt durch die Liste laufen bis das Element erreicht wird
		while (pos < index) {
			el = el.getNachfolger();
			pos = pos + 1;
		}
		return el;
	}

}

// vorgegebene Klasse:

package listen;
/*
 * Ein Element einer verketteten Liste besteht immer aus den Daten selbst
 * und einer Referenz auf das naechste folgende Element (bzw. NULL wenn es kein 
 * naechstes Element gibt)
 * 
 */

public class LinkedListeElement {
// Die eigentlichen Daten (hier String)
	String s;
// Eine Referenz auf das naechste Element in der verketteten Liste
	LinkedListeElement nachfolger;
// Der Konstruktor
	LinkedListeElement(String s, LinkedListeElement nachfolger) {
		this.s = s;
		this.nachfolger = nachfolger;
	}
// Getter & Setter ...
	void setS(String s) {
		 this.s = s; 
		}
	String getS() { 
		return s;
	}

	void setNachfolger(LinkedListeElement nachfolger) { 
		this.nachfolger = nachfolger; 
	}

	LinkedListeElement getNachfolger() { 
		return nachfolger; 
	}
}
// vorgegebenes Interface:

package listen;

public interface DSAListe {
// Ein Element hinten anfügen	
	boolean anhaengen(String s);
// Ein Element auf einer bestimmten Position einfügen
	boolean einfuegen(String s, int index);
// Ein Element auf einer bestimmten Position löschen
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
// Vorgabeimplementierung um alle Elemente zu löschen, 
// ohne den Container selbst zu löschen
	default void alleLoeschen() {
		for (int i = getAnzahl()-1; i>=0 ; --i) {
			loeschen(i);
		}
	};
}

// vorgegebene Klasse mit main um die eigene Linkedlist zu testen

package listen;

public class LinkedMain {

/* 
 * Hauptprogramm zum Testen der Listenimplementierungen
 * Der gewünschte Listentyp muss jeweils auskommentiert werden
 * Ansonsten brauchen Sie in dieser Klasse keine Änderungen zu machen
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
		DSAListe liste = new DummyListeLinked();
		test1(liste);
		test2(liste);
	}

	static void test1(DSAListe liste) {
		liste.anhaengen("Hallo");
		liste.anhaengen("Test");
		liste.anhaengen("DSA");
		liste.alleAusgeben();
        System.out.println("break");
		liste.loeschen(1);
		liste.alleAusgeben();
	}

	static void test2(DSAListe liste) {
		liste.alleLoeschen();
		for(int i = 0;i<10;i++) {
			liste.anhaengen(String.format("Eintrag%03d",i));
		}
		liste.ersetzen("Sieben", 7);
	    liste.einfuegen("Zwischen3und4", 4);
		liste.loeschen(6);
		liste.alleAusgeben();
	}
	
}





