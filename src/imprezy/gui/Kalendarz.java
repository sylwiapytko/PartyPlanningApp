package imprezy.gui;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;

/**
 * Klasa Kalendarz. Nadaje wartoœci wyœwietlane w trzech ComboBoxach
 * pozwalajacych na ustawienie wartoœci daty. Rok, Miesiac, Dzien. Oraz podajacy
 * pocz¹tkow¹(aktualna) wyswietlana date
 * 
 * @author Sylwia
 *
 */
public class Kalendarz {
	/**
	 * Wartosci wyswietlane w ComboBox z latami - trzy lata naprzod
	 */
	private DefaultComboBoxModel<Integer> rokModel = new DefaultComboBoxModel<Integer>();
	/**
	 * Wartosci wyswietlane w ComboBox z miesiacami - polskie nazwy miesiecy
	 */
	private DefaultComboBoxModel<Miesiace> miesiacModel = new DefaultComboBoxModel<Miesiace>();
	/**
	 * Wartosci wyswietlane w ComboBox z dniami - dni od 1 do 31
	 */
	private DefaultComboBoxModel<Integer> dzienModel = new DefaultComboBoxModel<Integer>();
	/**
	 * Dzisiejsza data
	 */
	private Date date = new Date();
	/**
	 * Kalendarz do pobrania z daty roku, miesiaca i dnia
	 */
	private Calendar calendar = new GregorianCalendar();

	/**
	 * Nadanie wartosci trzech lat na przod Modelowi ComboBoxa. Zwraca ten
	 * model.
	 * 
	 * @return rokModel
	 */
	public DefaultComboBoxModel<Integer> setRoki() {
		calendar.setTime(date);
		int rok = calendar.get(Calendar.YEAR);
		for (int i = 0; i < 3; i++) {
			rokModel.addElement(rok + i);
		}
		return rokModel;
	}

	/**
	 * Nadanie nazw miesiecy Modelowi ComboBoxa. Zwraca ten model
	 * 
	 * @return miesiacModel
	 */
	public DefaultComboBoxModel<Miesiace> setMiesiace() {
		for (Miesiace miesiac : Miesiace.values()) {
			miesiacModel.addElement(miesiac);
		}
		return miesiacModel;
	}

	/**
	 * Nadanie waroœci dni Modelowi ComboBoxa. Zwraca ten model
	 * 
	 * @return dzienModel
	 */
	public DefaultComboBoxModel<Integer> setDni() {
		for (int i = 1; i <= 31; i++) {
			dzienModel.addElement(i);
		}
		return dzienModel;
	}

	/**
	 * Pobranie numeru obecnego miesiaca
	 * 
	 * @return numer Miesiaca
	 */
	public int setDefaultMiesiac() {
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * Pobranie obecnego dnia
	 * 
	 * @return dzien
	 */
	public int setDefaultDzien() {
		return (calendar.get(Calendar.DAY_OF_MONTH) - 1);
	}
}
