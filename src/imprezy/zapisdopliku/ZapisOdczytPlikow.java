package imprezy.zapisdopliku;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.SwingWorker;

import imprezy.logika.ObserwatorImprezy;
import imprezy.logika.ObslugaImprezy;
import imprezy.model.Impreza;

/**
 * Klasa ZapisOdczytPlikow. Logika zapisu i oczytu obiektu do plikow
 * 
 * @author Sylwia
 *
 */
public class ZapisOdczytPlikow implements ObserwatorImprezy {
	/**
	 * Impreza ktora bedzie autozapisywana
	 */
	private Impreza obecnaImpreza;

	/**
	 * Zapisanie imprezy do nowego pliku
	 * 
	 * @param impreza
	 * @param file
	 * @return true jesli zapisano poprawnie, false w p.p.
	 */
	public boolean zapiszJako(Impreza impreza, File file) {
		try (ObjectOutputStream zapis = new ObjectOutputStream(new FileOutputStream(file))) {
			impreza.setCzyZapisane(true);
			impreza.setSciezka(file.getPath());
			zapis.writeObject(impreza);
			return true;

		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Zapis imprezy do pliku o podanej sciezce
	 * 
	 * @param impreza
	 * @param sciezka
	 * @return true jesli zapisano poprawnie, false w p.p.
	 */
	public boolean zapis(Impreza impreza, String sciezka) {
		// System.out.println("bedzie zapis");
		try (ObjectOutputStream zapis = new ObjectOutputStream(new FileOutputStream(sciezka))) {
			zapis.writeObject(impreza);

			// System.out.println("zapis afa " + impreza.getSciezka());
			return true;
		} catch (IOException ex) {
			return false;
		}
	}

	/**
	 * Otworzenie Imprezy z podanego pliku
	 * 
	 * @param file
	 * @return true jesli otworzono poprawnie, false w p.p.
	 */
	public ObslugaImprezy otworz(File file) {
		try (ObjectInputStream otwartaImpreza = new ObjectInputStream(new FileInputStream(file));) {
			Impreza impreza = (Impreza) otwartaImpreza.readObject();
			// setObecnaImpreza(impreza);
			return new ObslugaImprezy(impreza);
		} catch (IOException | ClassNotFoundException ex) {

			return null;
		}
	}

	/**
	 * Dodanie tego obiektu jako obserwatora zmian w Imprezie
	 * 
	 * @param obslugaImprezy
	 */
	public void dodajSieDoObserwowania(ObslugaImprezy obslugaImprezy) {
		// System.out.println("dodaje sie do obserwatorow");
		setObecnaImpreza(obslugaImprezy.getImpreza());
		obslugaImprezy.dodajObserwatoraListyUczestnikow(this);
		obslugaImprezy.dodajObserwatoraListyZakupow(this);
		obslugaImprezy.dodajObserwatoraBudzetu(this);
		obslugaImprezy.dodajObserwatoraListyZadan(this);

	}

	/**
	 * Usuniecie tego obiektu z obserwowania zmian w Imprezie
	 * 
	 * @param obslugaImprezy
	 */
	public void wypiszSieZObserwowania(ObslugaImprezy obslugaImprezy) {

		obslugaImprezy.usunObserwatoraListyUczestnikow(this);
		obslugaImprezy.usunObserwatoraListyZakupow(this);
		obslugaImprezy.usunObserwatoraBudzetu(this);
		obslugaImprezy.usunObserwatoraListyZadan(this);

	}

	/**
	 * Akcja wykonana po powiadomieniu o zmianie w Budzecie - nadpisanie obiektu
	 * w pliku
	 */
	@Override
	public void powiadomOZmianieWBudzecie() {
		zapisWTle();
	}

	/**
	 * Akcja wykonana po powiadomieniu o zmianie w Zadaniach - nadpisanie
	 * obiektu w pliku
	 */
	@Override
	public void powiadomOZmianieWZadaniach() {
		zapisWTle();
	}

	/**
	 * Akcja wykonana po powiadomieniu o zmianie w Zakupach - nadpisanie obiektu
	 * w pliku
	 */
	@Override
	public void powiadomOZmianieWZakupach() {
		zapisWTle();
	}

	/**
	 * Akcja wykonana po powiadomieniu o zmianie w Uczestnikach - nadpisanie
	 * obiektu w pliku
	 */
	@Override
	public void powiadomOZmianieWUczestnikach() {
		zapisWTle();
	}

	/**
	 * Uruchomienie nowego watku SwingWorkera do wykonania akcji zapisu w tle
	 */
	private void zapisWTle() {
		new ZapisWTle().execute();

	}

	/**
	 * Pobierz obecnie zapisywana impreze
	 * 
	 * @return obecnaImpreza
	 */
	public Impreza getObecnaImpreza() {
		return obecnaImpreza;
	}

	/**
	 * Nadaj impreze do obecnego zapisu
	 * 
	 * @param obecnaImpreza
	 */
	public void setObecnaImpreza(Impreza obecnaImpreza) {
		this.obecnaImpreza = obecnaImpreza;
	}

	/**
	 * Klasa Zapis w Tle rozszerzaj¹ca Klasê SwingWorker powoduj¹ca wykonywanie
	 * watku wykonuj¹cego siê w tle - wykonuj¹ca nadpisywanie pliku
	 * 
	 * @author Sylwia
	 *
	 */
	private class ZapisWTle extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			// System.out.println("do in b");
			if (zapis(getObecnaImpreza(), getObecnaImpreza().getSciezka())) {
				// System.out.println();
			}
			return null;
		}

	}

}
