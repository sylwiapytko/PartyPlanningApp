package imprezy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Klasa impreza przechowuj¹ca wszystkie informacje o Imprezie
 * 
 * @author Sylwia
 *
 */
public class Impreza implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Sciezka dostêpu do zapisanego obiektu
	 */
	private String sciezka = null;
	/**
	 * Ustaawiona data Imprezy
	 */
	private Date data = new Date();
	/**
	 * Lista uczestnikow
	 */
	private List<Uczestnik> uczestnicy;
	/*
	 * Lista zadan
	 */
	private List<Zadanie> zadania;
	/**
	 * Lista zakupow
	 */
	private List<Zakup> zakupy;
	/**
	 * Budzet Imprezy
	 */
	private Budzet budzet;
	/**
	 * Okreœlona maxymalna liczba uczestnikow. Gdy 0 to nieograniczona.
	 */
	private int maxUczestnikow = 0;
	/**
	 * Informacja czy obiekt by³ zapisywany (ma nadan¹ œcie¿kê do zapisu)
	 */
	private boolean czyZapisane = false;

	/**
	 * Konstruktor imprezy. Tworzy listy uczestnikow, zadan, zakupow i budzet.
	 * 
	 * @param sciezka
	 */
	public Impreza() {
		uczestnicy = new ArrayList<>();
		zadania = new ArrayList<>();
		zakupy = new ArrayList<>();
		setBudzet(new Budzet());

	}

	/**
	 * 
	 */
	public String toString() {

		return getSciezka();

	}

	/**
	 * Pobierz ustawiona maxymalna liczbe Uczestnikow
	 * 
	 * @return maxUczestnikow
	 */
	public int getMaxUczestnikow() {
		return maxUczestnikow;
	}

	/***
	 * Nadaj maxymalna liczbe Uczestnikow
	 * 
	 * @param maxUczestnikow
	 */
	public void setMaxUczestnikow(int maxUczestnikow) {
		this.maxUczestnikow = maxUczestnikow;
	}

	/**
	 * Pobierz liste Uczestnikow
	 * 
	 * @return uczestnicy
	 */
	public List<Uczestnik> getUczestnicy() {
		return uczestnicy;
	}

	/**
	 * Nadaj liste Uczestnikow
	 * 
	 * @param uczestnicy
	 */
	public void setUczestnicy(List<Uczestnik> uczestnicy) {
		this.uczestnicy = uczestnicy;
	}

	/**
	 * Pobierz date Imprezy
	 * 
	 * @return data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * Nadaj date Imprezy
	 * 
	 * @param data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * Pobierz liste Zakupow
	 * 
	 * @return zakupy
	 */
	public List<Zakup> getZakupy() {
		return zakupy;
	}

	/**
	 * Nadaj liste Zakupow
	 * 
	 * @param zakupy
	 */
	public void setZakupy(List<Zakup> zakupy) {
		this.zakupy = zakupy;
	}

	/**
	 * Pobierz Budzet
	 * 
	 * @return budzet
	 */
	public Budzet getBudzet() {
		return budzet;
	}

	/**
	 * Nadaj Budzet
	 * 
	 * @param budzet
	 */
	public void setBudzet(Budzet budzet) {
		this.budzet = budzet;
	}

	/**
	 * Pobierz czy Impreza byla zapisana
	 * 
	 * @return true gdy Impreza byla zapisana, false w p.p.
	 */
	public boolean isCzyZapisane() {
		return czyZapisane;
	}

	/**
	 * Nadaj czy Impreza by³a zapisana
	 * 
	 * @param czyZapisane
	 */
	public void setCzyZapisane(boolean czyZapisane) {
		this.czyZapisane = czyZapisane;
	}

	/**
	 * Pobierz sciezke gdzie Impreza jest zapisana
	 * 
	 * @return nazwa, null gdy nie byla zapisana
	 */
	public String getSciezka() {
		return sciezka;
	}

	/**
	 * Nadaj sciezke gdzie Impreza jest zapisana
	 * 
	 * @param sciezka
	 */
	public void setSciezka(String sciezka) {
		this.sciezka = sciezka;
	}

	/**
	 * Pobierz liste Zadan
	 * 
	 * @return zadania
	 */
	public List<Zadanie> getZadania() {
		return zadania;
	}

	/**
	 * Nadaj liste Zadan
	 * 
	 * @param zadania
	 */
	public void setZadania(List<Zadanie> zadania) {
		this.zadania = zadania;
	}

}
