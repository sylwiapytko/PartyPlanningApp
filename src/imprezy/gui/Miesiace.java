package imprezy.gui;

/**
 * Enumerat posiadaj¹cy polskie nazwy miesiecy
 * 
 * @author Sylwia
 *
 */
public enum Miesiace {
	STYCZEN("Styczen"), LUTY("Luty"), MARZEC("Marzec"), KWIECIEN("Kwiecien"), MAJ("Maj"), CZERWIEC("Czerwiec"), LIPIEC(
			"Lipiec"), SIERPIEN("Sierpien"), WRZESIEN(
					"Wrzesien"), PAZDZIERNIK("Pazdziernik"), LISTOPAD("Listopad"), GRUDZIEN("Grudzien");
	/**
	 * Nazwa Miesiaca
	 */
	private String nazwa;

	/**
	 * Konstruktor nadajacy nazwe miesiacowi
	 * 
	 * @param nazwa
	 */
	Miesiace(String nazwa) {
		this.nazwa = nazwa;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return nazwa;
	}

}
