package imprezy.model;

import java.io.Serializable;

/**
 * Klasa Uczestnik przetrzymuj¹ca informacje o Uczestniku Imprezy
 * 
 * @author Sylwia
 *
 */
public class Uczestnik implements Serializable {
	/**
	 * Imie Uczestnika
	 */
	private String imie;
	/**
	 * Nazwisko Uczestnika
	 */
	private String nazwisko;

	/**
	 * Konstruktor Uczestnika Nadaje Imie i Nazwisko
	 * 
	 * @param imie
	 * @param nazwisko
	 */
	public Uczestnik(String imie, String nazwisko) {
		this.setImie(imie);
		this.setNazwisko(nazwisko);
	}

	/**
	 * 
	 */
	public String toString() {
		return getImie() + " " + getNazwisko();
	}

	/**
	 * Pobierz Imie Uczestnika
	 * 
	 * @return imie
	 */
	public String getImie() {
		return imie;
	}

	/**
	 * Nadaj Imie Uczestnika
	 * 
	 * @param imie
	 */
	public void setImie(String imie) {
		this.imie = imie;
	}

	/**
	 * Pobierz Nazwisko Uczestnika
	 * 
	 * @return nazwisko
	 */
	public String getNazwisko() {
		return nazwisko;
	}

	/**
	 * Nadaj Nazwisko Uczestnika
	 * 
	 * @param nazwisko
	 */
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

}
