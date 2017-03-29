package imprezy.model;

import java.io.Serializable;

/**
 * Klasa Bud¿et przechowuj¹ca przeznaczon¹ i wydan¹ kwotê na Impreze
 * 
 * @author Sylwia
 *
 */
public class Budzet implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Kwota przeznaczona na Impreze
	 */
	private float przeznaczone = 0;
	/**
	 * Kwota wydana na Impreze
	 */
	private float wydane = 0;

	/**
	 * pobierz wydane
	 * 
	 * @return wydane
	 */
	public float getWydane() {
		return wydane;
	}

	/**
	 * nadaj wydane
	 * 
	 * @param wydane
	 */
	public void setWydane(float wydane) {
		this.wydane = wydane;
	}

	/**
	 * pobierz przeznaczone
	 * 
	 * @return przeznaczone
	 */
	public float getPrzeznaczone() {
		return przeznaczone;
	}

	/**
	 * nadaj przeznaczone
	 * 
	 * @param przeznaczone
	 */
	public void setPrzeznaczone(float przeznaczone) {
		this.przeznaczone = przeznaczone;
	}

}
