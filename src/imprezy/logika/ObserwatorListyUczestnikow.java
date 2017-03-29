package imprezy.logika;

/**
 * Interfejs dla obiektow obserwujacych zmiany na liscie uczestnikow
 * 
 * @author Sylwia
 *
 */
public interface ObserwatorListyUczestnikow {
	/**
	 * Metoda wywolywana dla powiadomienian obserwatora o zmianach na liscie
	 * uczesnikow
	 */
	void powiadomOZmianieWUczestnikach();

}
