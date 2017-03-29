package imprezy.logika;

/**
 * Interfejs dla obiektow obserwujacych wszystkie zmiany w Imprezie
 * 
 * @author Sylwia
 *
 */
public interface ObserwatorImprezy
		extends ObserwatorBudzetu, ObserwatorListyUczestnikow, ObserwatorListyZadan, ObserwatorListyZakupow {

	int a = 1;
}
