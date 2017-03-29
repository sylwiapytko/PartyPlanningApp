package imprezy.logika;

import imprezy.gui.Okno;
import imprezy.model.Impreza;

/**
 * Klasa Main - glowna klasa
 * 
 * @author Sylwia
 *
 */
public class Main {
	/**
	 * Metoda main. Metoda rozpoczynajaca dzialanie programu. Tworzy Okno,
	 * Impreze oraz ObslugeImprezy. Przypisuje do okna odpowiednia
	 * obslugeImprezy z nowa impreza.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Okno okno = new Okno();

		Impreza impreza = new Impreza();
		ObslugaImprezy obslugaImprezy = new ObslugaImprezy(impreza);

		okno.setObslugaImprezy(obslugaImprezy);
	}

}
