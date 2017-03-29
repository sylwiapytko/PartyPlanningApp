package imprezy.model;

import java.io.Serializable;

/**
 * Klasa Zadanie
 * 
 * @author Sylwia
 *
 */
public class Zadanie implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Nazwa Zadania
	 */
	private String zadanieNazwa;

	/**
	 * Konstruktor Zadania. Nadaje nazwe
	 * 
	 * @param zadanieNazwa
	 */
	public Zadanie(String zadanieNazwa) {
		this.setZadanieNazwa(zadanieNazwa);
	}

	/**
	 * 
	 */
	public String toString() {
		return getZadanieNazwa();
	}

	/**
	 * Pobierz Nazwe Zadania
	 * 
	 * @return zadanieNazwa
	 */
	public String getZadanieNazwa() {
		return zadanieNazwa;
	}

	/**
	 * Nadaj Nazwe Zadania
	 * 
	 * @param zadanieNazwa
	 */
	public void setZadanieNazwa(String zadanieNazwa) {
		this.zadanieNazwa = zadanieNazwa;
	}

}
