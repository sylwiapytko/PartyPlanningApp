package imprezy.logika;

/**
 * Interfejs dla obiektow obserwujacych zmiany na liscie zadan
 * 
 * @author Sylwia
 *
 */
public interface ObserwatorListyZadan {
	/**
	 * Metoda wywolywana dla powiadomienian obserwatora o zmianach na liscie
	 * zadan
	 */
	void powiadomOZmianieWZadaniach();

}
