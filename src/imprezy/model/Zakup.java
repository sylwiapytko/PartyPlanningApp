package imprezy.model;

import java.io.Serializable;

/**
 * Klasa Zakup
 * 
 * @author Sylwia
 *
 */
public class Zakup implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Nazwa Zakupu
	 */
	private String zakupNazwa;

	/**
	 * Konstruktor Zakupu. Nadaje Nazwe
	 * 
	 * @param zakupNazwa
	 */
	public Zakup(String zakupNazwa) {
		this.setZakupNazwa(zakupNazwa);
	}

	/**
	 * 
	 */
	public String toString() {
		return getZakupNazwa();
	}

	/**
	 * Pobierz Nazwe Zakupu
	 * 
	 * @return zakupNazwa
	 */
	public String getZakupNazwa() {
		return zakupNazwa;
	}

	/**
	 * Nadaj Nazwe Zakupu
	 * 
	 * @param zakupNazwa
	 */
	public void setZakupNazwa(String zakupNazwa) {
		this.zakupNazwa = zakupNazwa;
	}

}
