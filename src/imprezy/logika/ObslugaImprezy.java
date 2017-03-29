package imprezy.logika;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import imprezy.model.Impreza;
import imprezy.model.Uczestnik;
import imprezy.model.Zadanie;
import imprezy.model.Zakup;

/**
 * klasa Obsluga Imprezy. Logika zmian atrybutow imprezy
 * 
 * @author Sylwia
 *
 */
public class ObslugaImprezy {
	/**
	 * Obiekt na którym bêd¹ wykonywane zmiany atrybutow
	 */
	private Impreza impreza;
	/**
	 * Lista obserwatorow listy Uczestnikow
	 */
	private Collection<ObserwatorListyUczestnikow> obserwatorzyListyUczestnikow = new HashSet<>();
	/**
	 * Lista obserwatorow listy Zakupow
	 */
	private Collection<ObserwatorListyZakupow> obserwatorzyListyZakupow = new HashSet<>();
	/**
	 * Lista obserwatorow listy Zadan
	 */
	private Collection<ObserwatorListyZadan> obserwatorzyListyZadan = new HashSet<>();
	/**
	 * Lista obserwatorow budzetow
	 */
	private Collection<ObserwatorBudzetu> obserwatorzyBudzetu = new HashSet<>();

	/**
	 * Konsturktor Obslugi imprezy. Nadaje obiekt impreza na ktorym beda
	 * wykonywane zmiany atrybutow
	 * 
	 * @param impreza
	 */
	public ObslugaImprezy(Impreza impreza) {
		this.setImpreza(impreza);
	}

	/**
	 * Dodanie obiektu do listy obserwatorow zmian na liscie uczestnikow
	 * 
	 * @param obserwatorDoDodania
	 */
	public void dodajObserwatoraListyUczestnikow(ObserwatorListyUczestnikow obserwatorDoDodania) {
		// System.out.println("dodaje");
		obserwatorzyListyUczestnikow.add(obserwatorDoDodania);
	}

	/**
	 * Powiadomienie wszystkich obserwatorow o zmianie na liscie uczestnikow
	 */
	private void powiadomOZmianachNaLiscieUczestnikow() {
		for (ObserwatorListyUczestnikow obserwatorListyUczestnikow : obserwatorzyListyUczestnikow) {
			obserwatorListyUczestnikow.powiadomOZmianieWUczestnikach();
		}
	}

	/**
	 * Dodanie obiektu do listy obserwatorow zmian na liscie zakupy
	 * 
	 * @param obserwatorDoDodania
	 */
	public void dodajObserwatoraListyZakupow(ObserwatorListyZakupow obserwatorDoDodania) {
		obserwatorzyListyZakupow.add(obserwatorDoDodania);
	}

	/**
	 * Powiadomienie wszystkich obserwatorow o zmianie na liscie zakupow
	 */
	private void powiadomOZmianachNaLiscieZakupow() {
		for (ObserwatorListyZakupow obserwatorListyZakupow : obserwatorzyListyZakupow) {
			obserwatorListyZakupow.powiadomOZmianieWZakupach();
		}
	}

	/**
	 * Dodanie obiektu do listy obserwatorow zmian na liscie zadan
	 * 
	 * @param obserwatorDoDodania
	 */
	public void dodajObserwatoraListyZadan(ObserwatorListyZadan obserwatorDoDodania) {
		obserwatorzyListyZadan.add(obserwatorDoDodania);
	}

	/**
	 * Powiadomienie wszystkich obserwatorow o zmianie na liscie zadan
	 */
	private void powiadomOZmianachNaLiscieZadan() {
		for (ObserwatorListyZadan obserwatorListyZadan : obserwatorzyListyZadan) {
			obserwatorListyZadan.powiadomOZmianieWZadaniach();
		}
	}

	/**
	 * Dodanie obiektu do listy obserwatorow zmian w budzecie
	 * 
	 * @param obserwatorDoDodania
	 */
	public void dodajObserwatoraBudzetu(ObserwatorBudzetu obserwatorDoDodania) {
		obserwatorzyBudzetu.add(obserwatorDoDodania);
	}

	/**
	 * Powiadomienie wszystkich obserwatorow o zmianie w budzecie
	 */
	private void powiadomOZmianachBudzetu() {
		for (ObserwatorBudzetu obserwatorListyZakupow : obserwatorzyBudzetu) {
			obserwatorListyZakupow.powiadomOZmianieWBudzecie();
		}
	}

	/**
	 * Usuniecie obiektu z listy obserwatorow listy uczesnikow
	 * 
	 * @param obserwator
	 */
	public void usunObserwatoraListyUczestnikow(ObserwatorListyUczestnikow obserwator) {
		obserwatorzyListyUczestnikow.remove(obserwator);
	}

	/**
	 * Usuniecie obiektu z listy obserwatorow listy zakupow
	 * 
	 * @param obserwator
	 */
	public void usunObserwatoraListyZakupow(ObserwatorListyZakupow obserwator) {
		obserwatorzyListyZakupow.remove(obserwator);
	}

	/**
	 * Usuniecie obiektu z listy obserwatorow listy zadan
	 * 
	 * @param obserwator
	 */
	public void usunObserwatoraListyZadan(ObserwatorListyZadan obserwator) {
		obserwatorzyListyZadan.remove(obserwator);
	}

	/**
	 * Usuniecie obiektu z listy obserwatorow budzetu
	 * 
	 * @param obserwator
	 */
	public void usunObserwatoraBudzetu(ObserwatorBudzetu obserwator) {
		obserwatorzyBudzetu.remove(obserwator);
	}

	/**
	 * Dodanie uczestnika do listyUczestnikow o imieniu i nazwisku jesli sa
	 * dostepne miejsca
	 * 
	 * @param imie
	 * @param nazwisko
	 * @return true jesli dodano, false w p.p.
	 */
	public boolean dodajUczestnika(String imie, String nazwisko) {
		if (czyMoznaDodacUczesnika()) {
			getImpreza().getUczestnicy().add(new Uczestnik(imie, nazwisko));
			powiadomOZmianachNaLiscieUczestnikow();
			return true;
		}
		return false;
	}

	/**
	 * Czy liczba miejsc dla uczestnikow jest ograniczona
	 * 
	 * @return true jesli tak, false w p.p.
	 */
	public boolean czyIloscMiejscOgraniczona() {
		return getImpreza().getMaxUczestnikow() > 0;
	}

	/**
	 * Ile jest wolnych miejsc na liscie Uczestnikow
	 * 
	 * @return ilosc wolnych miejsc
	 */
	public int ileWolnychMiejsc() {
		return getImpreza().getMaxUczestnikow() - getImpreza().getUczestnicy().size();
	}

	/**
	 * Czy mozna dodac uczestnika
	 * 
	 * @return true jesli tak, false w p.p.
	 */
	public boolean czyMoznaDodacUczesnika() {
		if (czyIloscMiejscOgraniczona() == false) {
			return true;
		}
		return ileWolnychMiejsc() > 0;
	}

	/**
	 * Dodanie liczby maksymalnej liczby uczesnikow
	 * 
	 * @param maxUczestnikow
	 * @return true jesli jest to liczba dodatnia i wiêksza od liczby
	 *         uczesnikow, false w p.p.
	 */
	public boolean dodajMaxUczestnikow(int maxUczestnikow) {

		if (maxUczestnikow > 0 && czyMoznaDodacMaxUczestnikow(maxUczestnikow)) {
			getImpreza().setMaxUczestnikow(maxUczestnikow);
			powiadomOZmianachNaLiscieUczestnikow();
			return true;
		} else
			return false;

	}

	/**
	 * Czy maxUczestnikow wieksza od ilosci uczesnitkow
	 * 
	 * @param maxUczestnikow
	 * @return true jesli maxUczestnikow wieksza od ilosci uczestnikow, false w
	 *         p.p.
	 */
	public boolean czyMoznaDodacMaxUczestnikow(int maxUczestnikow) {
		return maxUczestnikow >= getImpreza().getUczestnicy().size();
	}

	/**
	 * Pobierz Impreza
	 * 
	 * @return impreza
	 */
	public Impreza getImpreza() {
		return impreza;
	}

	/**
	 * Nadaj Impreza
	 * 
	 * @param impreza
	 */
	public void setImpreza(Impreza impreza) {
		this.impreza = impreza;
	}

	/**
	 * Usun uczesnika z liczby uczestnikow
	 * 
	 * @param Uczestnik
	 */
	public void usunUczestnika(Uczestnik Uczestnik) {
		getImpreza().getUczestnicy().remove(Uczestnik);
		powiadomOZmianachNaLiscieUczestnikow();

	}

	/**
	 * Nadaj date
	 * 
	 * @param data
	 * @return true jesli pozniejsza niz dzisiejsza, false w p.p.
	 */
	public boolean dodajDate(Date data) {
		if (czyPrzyszlaData(data)) {
			getImpreza().setData(data);
			powiadomOZmianachNaLiscieUczestnikow();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Czy to jest przyszla data
	 * 
	 * @param data
	 * @return true jesli data to przyszla data, false w p.p.
	 */
	private boolean czyPrzyszlaData(Date data) {
		Date dzis = new Date();
		if (data.getMonth() >= dzis.getMonth()) {
			if (data.getDate() >= dzis.getDate()) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Dodanie zakupu do listy zakupow
	 * 
	 * @param zakup
	 */
	public void dodajZakup(String zakup) {
		getImpreza().getZakupy().add(new Zakup(zakup));
		powiadomOZmianachNaLiscieZakupow();

	}

	/**
	 * Usuniecie zakupu z listy zakupow
	 * 
	 * @param zakup
	 */
	public void usunZakup(Zakup zakup) {
		getImpreza().getZakupy().remove(zakup);
		powiadomOZmianachNaLiscieZakupow();

	}

	/**
	 * Dodanie zadania do listy zadan
	 * 
	 * @param zadanie
	 */
	public void dodajZadanie(String zadanie) {
		getImpreza().getZadania().add(new Zadanie(zadanie));
		powiadomOZmianachNaLiscieZadan();
	}

	/**
	 * Usuniecie zadania z listy zadan
	 * 
	 * @param zadanie
	 */
	public void usunZadanie(Zadanie zadanie) {
		getImpreza().getZadania().remove(zadanie);
		powiadomOZmianachNaLiscieZadan();
	}

	/**
	 * Modyfikacja budzetu, dodanie przychodu lub wydatku
	 * 
	 * @param jak
	 * @param ile
	 * @param choices
	 */
	public void modyfikujBudzet(String jak, float ile, Object[] choices) {
		if (jak == choices[0]) {
			float budzet = getImpreza().getBudzet().getPrzeznaczone() + ile;
			getImpreza().getBudzet().setPrzeznaczone(budzet);
			powiadomOZmianachBudzetu();
		} else {
			modyfikujBudzetWydatek(ile);
		}
	}

	/**
	 * Dodanie wydatku
	 * 
	 * @param ile
	 */
	public void modyfikujBudzetWydatek(float ile) {
		float wydatki = getImpreza().getBudzet().getWydane() + ile;
		getImpreza().getBudzet().setWydane(wydatki);
		powiadomOZmianachBudzetu();
	}

	/**
	 * Obliczenie procentu wydanego z przeznaczonego budzetu
	 * 
	 * @return
	 */
	public int procentIleWydane() {
		return ((int) ((getImpreza().getBudzet().getWydane() * 100) / getImpreza().getBudzet().getPrzeznaczone()));
	}

	/**
	 * Obliczenie ile budzetu pozostalo
	 * 
	 * @return ile budzetu pozostalo
	 */
	public float pozostaloBudzet() {
		return (getImpreza().getBudzet().getPrzeznaczone() - getImpreza().getBudzet().getWydane());
	}

	/**
	 * Czy jest dostepny budzet
	 * 
	 * @return true jesli jest dostepny, false w p.p.
	 */
	public boolean czyDostepnyBudzet() {

		return pozostaloBudzet() > 0;
	}

}
