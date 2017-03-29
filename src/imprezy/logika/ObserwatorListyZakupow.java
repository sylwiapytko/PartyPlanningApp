package imprezy.logika;

/**
 * Interfejs dla obiektow obserwujacych zmiany na licie zakupow
 * 
 * @author Sylwia
 *
 */
public interface ObserwatorListyZakupow {
	/**
	 * Metoda wywolywana dla powiadomienian obserwatora o zmianach na liscie
	 * zakupow
	 */
	void powiadomOZmianieWZakupach();
}
