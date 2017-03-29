package imprezy.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import imprezy.logika.ObserwatorImprezy;
import imprezy.logika.ObslugaImprezy;
import imprezy.model.Impreza;
import imprezy.model.Uczestnik;
import imprezy.model.Zadanie;
import imprezy.model.Zakup;
import imprezy.zapisdopliku.ZapisOdczytPlikow;

public class Okno extends JFrame implements ObserwatorImprezy {

	private static final long serialVersionUID = 1L;
	/**
	 * Pole tekstowe na imie nowego uczestnika
	 */
	private JTextField imieNowegoUczestnika = new JTextField("Imie");
	/**
	 * Pole tekstowe na nazwisko nowego uczestnika
	 */
	private JTextField nazwiskoNowegoUczestnika = new JTextField("Nazwisko");
	/**
	 * Przycisk do usuwania wybranych uczestnikow z listy
	 */
	private JButton usunWybranych = new JButton("Usuñ wybranych uczestników");
	/**
	 * Label z informacj¹ ¿e tu mo¿na dodawaæ nowego uczestnika
	 */
	private JLabel dodanieUczestnika = new JLabel("Dodaj uczestnika:");
	/**
	 * Przycisk do dodania nowego Uczestnika o imieniu i nazwisku wpisanym w
	 * pola tekstowe imieNowegoUczestnika i nazwiskoNowegoUczestnika
	 */
	private JButton dodajUczestnika = new JButton("Dodaj Uczestnika");
	/**
	 * Lista uczestnikow
	 */
	private JList<Uczestnik> listaUczestnikow = new JList<>();
	/**
	 * Zmienna przechowujaca obslugeImprezy Imprezy która teraz jest obslugiwana
	 */
	private ObslugaImprezy obslugaImprezy;
	/**
	 * Label informujacy ile jest wolnych miejsc z ilu dostepnych, lub czy
	 * miejsca sa nieograniczone
	 */
	private JLabel wolneMiejsca = new JLabel("wolne miejsca: nieograniczone ");
	/**
	 * Grupa przyciskow do zmiany ograniczenia maksymalnej liczby uczestnikow
	 */
	private ButtonGroup maxUczestnikowJaka = new ButtonGroup();
	/**
	 * RadioButton do oznaczenia ¿e liczba uczestnikow moze byæ nieograniczona
	 */
	private JRadioButton maxUczestnikowNieograniczona = new JRadioButton("Liczba Uczestników nieograniczona");
	/**
	 * RadioButton do oznaczenia ¿e liczba uczestnikow jest ograniczona.
	 * Powoduje prosbe o nadanie maksymalnej liczby uczestnikow
	 */
	private JRadioButton maxUczestnikowOgraniczona = new JRadioButton("Liczba Uczestników Ograniczona");
	/**
	 * Lista zakupow
	 */
	private JList<Zakup> listaZakupow = new JList<>();
	/**
	 * Pole tekstowe do dodania nowego zakupu. Zakup jest dodawany na przycisk
	 * Enter
	 */
	private JTextField dodajZakup = new JTextField("Dodaj Zakup <Enter>");
	/**
	 * Przycisk do usuniecia zakupow zaznaczonych na liscie
	 */
	private JButton usunZakup = new JButton("Usuñ wybrane zakupy");
	/**
	 * Menu wyboru dlaczego usuwamy Zakup pojawiajace sie po kliknieciu w
	 * usunZakup
	 */
	private JPopupMenu usunZakupBo = new JPopupMenu("Usuñ Zakup Bo");
	/**
	 * Opcja menu wyboru dlaczego usuwamy Zakup. - Usuwamy bez ingerencji w
	 * Budzet
	 */
	private JMenuItem usunZakupPoprostu = new JMenuItem("Bo Poprostu");
	/**
	 * Opcja menu wyboru dlaczego usuwamy Zakup. - Usuwamy z mo¿liwa ingerencja
	 * w Budzet
	 */
	private JMenuItem usunZakupZakupione = new JMenuItem("Bo Zakupionie");
	/**
	 * Przycisk modyfikacji budzetu. Mozliwe dodanie dochodu lub wydatku
	 */
	private JButton modyfikujBudzet = new JButton("Modyfikuj Bud¿et");
	/**
	 * ProgressBar pokazujacy jaki procent z przeznaczonej kwoty zostal wydany.
	 * Jeœli powy¿ej 100% jest informacja o przekroczonym budzecie
	 * 
	 */
	private JProgressBar budzetBar = new JProgressBar();
	/**
	 * Label informuj¹cy o kwocie przeznaczonej na budzet
	 */
	private JLabel przeznaczonyBudzet = new JLabel("Przeznaczony Bud¿et");
	/**
	 * Label informujacy o kwocie wydanej
	 */
	private JLabel wydaneBudzet = new JLabel("Wydane");
	/**
	 * label informujacy o pozostalej kwocie budzetu
	 */
	private JLabel pozostalyBudzet = new JLabel("Pozosta³y bud¿et");
	/**
	 * Obiekt Kalendarza
	 */
	private Kalendarz kalendarz = new Kalendarz();
	/**
	 * ComboBox do wyboru roku przy ustawianiu daty
	 */
	private JComboBox<Integer> rokBox = new JComboBox<Integer>(kalendarz.setRoki());
	/**
	 * ComboBox do wyboru miesi¹ca przy ustawianiu daty
	 */
	private JComboBox<Miesiace> miesiacBox = new JComboBox<Miesiace>(kalendarz.setMiesiace());
	/**
	 * ComboBox do wyboru dnia przy ustawianiu daty
	 */
	private JComboBox<Integer> dzienBox = new JComboBox<Integer>(kalendarz.setDni());

	private Calendar calendar = Calendar.getInstance();
	/**
	 * MenuBar aplikacji
	 */
	private JMenuBar menuBar = new JMenuBar();
	/**
	 * Przycisk na MenuBarze - Menu Plik: posiada opcje Nowy, Otworz, Zapisz,
	 * ZapiszJako i AutoZapis
	 */
	private JMenu menuPlik = new JMenu("Plik");
	/**
	 * Opcja w menu Plik - Nowy: Otwiera czysta aplikacje z nowa Impreza
	 */
	private JMenuItem menuNowy = new JMenuItem("Nowy");
	/**
	 * Opcja w menu Plik - Otworz: Otwiera okno wyboru pliku do otwarcia.
	 * Otwiera zapisana Impreze
	 */
	private JMenuItem menuOtworz = new JMenuItem("Otworz");
	/**
	 * Opcja w menu Plik - Zapisz: Zapis Imprezy do pliku. Jeœli nie byla
	 * wczeœniej zapisana wystêpuje wybór sciezki
	 */
	private JMenuItem menuZapisz = new JMenuItem("Zapisz");
	/**
	 * Opcja w menu Plik - Zapisz jako: Zapis imprezy do pliku o wybranej
	 * sciezce
	 */
	private JMenuItem menuZapiszJako = new JMenuItem("Zapisz Jako");
	/**
	 * Opcja w menu Plik - Opcja Autozapisu - Jesli Impreza nie byla wczesniej
	 * zapisana wyswietli sie wybor sciezki
	 */
	private JCheckBoxMenuItem menuAutoZapis = new JCheckBoxMenuItem("Auto Zapis");
	/**
	 * Lista zadan
	 */
	private JList<Zadanie> listaZadan = new JList<>();
	/**
	 * Pole tekstowe na dodanie zadania. Dodaje sie na przycisk Enter
	 */
	private JTextField dodajZadanie = new JTextField("Dodaj Zadanie <Enter>");
	/**
	 * Przycisk do usuniecia wybranych Zadan
	 */
	private JButton usunZadanie = new JButton("Usuñ wybrane zadania");
	/**
	 * Obiekt zapisu i odczytu obiektow Imprezy
	 */
	private ZapisOdczytPlikow zapisywanie = new ZapisOdczytPlikow();
	/**
	 * Panel na zakladki - uczestnicy, zakupy i budzet, zadania
	 */
	private JTabbedPane zakladki = new JTabbedPane();
	/**
	 * Zakladka uczestnicy
	 */
	private JPanel uczestnicy = new JPanel();
	/**
	 * Zakladka zakupy i budzet
	 */
	private JPanel zakupy = new JPanel();
	/**
	 * Zakladka zadania
	 */
	private JPanel zadania = new JPanel();
	/**
	 * Glowne okno
	 */
	private Container zawartoscOkienka;
	/**
	 * Panel z menu i data
	 */
	private JPanel panelMenu = new JPanel();
	/**
	 * Panel z przyciskami na zakladce Uczestnicy
	 */
	private JPanel przyciskiUczestnicy = new JPanel();
	/**
	 * Panel z przyciaskami na zakladce Zakupy
	 */
	private JPanel przyciskiZakupy = new JPanel();
	/**
	 * Panel z przyciskami na zakladce Zadania
	 */
	private JPanel przyciskiZadania = new JPanel();

	/**
	 * Konstruktor Okno. Konfiguruje okno, dodaje wszystkie elementy, listenery
	 * tych elementow
	 */
	public Okno() {
		skonfigurujOkno();
		dodajKontrolki(getContentPane());
		dodajListenry();
		setVisible(true);
	}

	/**
	 * Ustanienie i dodanie wszystkich elemetow w oknie
	 * 
	 * @param zawartoscOkienka
	 */
	private void dodajKontrolki(Container zawartoscOkienka) {
		zawartoscOkienka.setLayout(new BorderLayout());
		setGlownyPanel();
		setMenu();
		menuBar.add(menuPlik);
		zawartoscOkienka.add(setZakladki());
		zakladki.setSelectedIndex(0);
		defaultUstawienia();

	}

	/**
	 * Ustawienie i dodanie elementow na glownym Panelu
	 */
	private void setGlownyPanel() {
		zawartoscOkienka.add(panelMenu, BorderLayout.NORTH);

		panelMenu.setLayout(new BorderLayout());
		panelMenu.add(menuBar, BorderLayout.NORTH);

		panelMenu.add(rokBox, BorderLayout.WEST);
		panelMenu.add(miesiacBox, BorderLayout.CENTER);
		panelMenu.add(dzienBox, BorderLayout.EAST);

	}

	/**
	 * Dodanie zakladek
	 * 
	 * @return zakladki
	 */
	private JTabbedPane setZakladki() {
		zakladki.addTab("Uczestnicy", setUczestnicy());
		zakladki.addTab("Zakupy i Bud¿et", setZakupy());
		zakladki.addTab("Zadania", setZadania());
		return zakladki;
	}

	/**
	 * Ustawienie i dodanie elementow na panelu Zadania
	 * 
	 * @return zakladka zadania
	 */
	private Component setZadania() {
		zadania.setLayout(new BorderLayout());
		zadania.add(przyciskiZadania, BorderLayout.NORTH);
		przyciskiZadania.setLayout(new BorderLayout());
		zadania.add(listaZadan);
		przyciskiZadania.add(usunZadanie, BorderLayout.EAST);
		przyciskiZadania.add(dodajZadanie);
		return zadania;
	}

	/**
	 * Ustawienie i dodanie elementow na panelu Zaakupy
	 * 
	 * @return zakladka zakupy
	 */
	private Component setZakupy() {
		zakupy.setLayout(new BorderLayout());
		zakupy.add(listaZakupow, BorderLayout.WEST);

		budzetBar = new JProgressBar(0, 100);
		budzetBar.setValue(0);
		budzetBar.setStringPainted(true);

		zakupy.add(budzetBar, BorderLayout.SOUTH);

		zakupy.add(przyciskiZakupy);
		przyciskiZakupy.setLayout(new GridLayout(3, 2));
		przyciskiZakupy.add(dodajZakup);
		przyciskiZakupy.add(przeznaczonyBudzet);
		przyciskiZakupy.add(usunZakup);
		usunZakupBo.add(usunZakupPoprostu);
		usunZakupBo.add(usunZakupZakupione);
		przyciskiZakupy.add(wydaneBudzet);
		przyciskiZakupy.add(modyfikujBudzet);
		przyciskiZakupy.add(pozostalyBudzet);

		return zakupy;
	}

	/**
	 * Ustawienie i dodanie elementow na panelu Uczestnicy
	 * 
	 * @return zakladka uczestnicy
	 */
	private JPanel setUczestnicy() {
		uczestnicy.setLayout(new BorderLayout());
		uczestnicy.add(listaUczestnikow, BorderLayout.WEST);
		uczestnicy.add(przyciskiUczestnicy);
		przyciskiUczestnicy.setLayout(new GridLayout(4, 2));
		przyciskiUczestnicy.add(dodanieUczestnika);
		przyciskiUczestnicy.add(wolneMiejsca);
		przyciskiUczestnicy.add(imieNowegoUczestnika);
		przyciskiUczestnicy.add(nazwiskoNowegoUczestnika);
		przyciskiUczestnicy.add(usunWybranych);
		przyciskiUczestnicy.add(dodajUczestnika);

		przyciskiUczestnicy.add(maxUczestnikowNieograniczona);
		przyciskiUczestnicy.add(maxUczestnikowOgraniczona);

		maxUczestnikowJaka.add(maxUczestnikowNieograniczona);
		maxUczestnikowJaka.add(maxUczestnikowOgraniczona);
		return uczestnicy;
	}

	/**
	 * Dodanie opcji do Menu
	 */
	private void setMenu() {
		menuPlik.add(menuNowy);
		menuPlik.add(menuOtworz);
		menuPlik.add(menuZapisz);
		menuPlik.add(menuZapiszJako);
		menuPlik.add(menuAutoZapis);
	}

	/**
	 * Konfiguracja okna aplikacji
	 */
	private void skonfigurujOkno() {
		setSize(600, 400);
		setTitle("Planer");
		zawartoscOkienka = getContentPane();
		zawartoscOkienka.setLayout(new BorderLayout());

	}

	/**
	 * Przypisanie poczatkowych ustanwien opcji i kalendarza
	 */
	private void defaultUstawienia() {
		menuAutoZapis.setSelected(false);
		maxUczestnikowNieograniczona.setSelected(true);
		rokBox.setSelectedIndex(0);
		miesiacBox.setSelectedIndex(kalendarz.setDefaultMiesiac());
		dzienBox.setSelectedIndex(kalendarz.setDefaultDzien());
	}

	/**
	 * Akcja przycisku dodajUczetnika - dodaje Uczestnika o podanym imieniu i
	 * nazwisku
	 */
	private void dodajUczestnika() {
		String imie = imieNowegoUczestnika.getText();
		String nazwisko = nazwiskoNowegoUczestnika.getText();
		obslugaImprezy.dodajUczestnika(imie, nazwisko);

	}

	/**
	 * Akcja prycisku maxUczestnikowNieograniczona - ustawia maxUczestnikow
	 * imprezy na 0 - nieoganiczona ilosc
	 */
	private void maxUczestnikowNieograniczona() {
		obslugaImprezy.getImpreza().setMaxUczestnikow(0);
		wolneMiejsca.setText("wolne miejsca: nieograniczone ");

	}

	/**
	 * Akcja prycisku maxUczestnikow Ograniczona - prosi o podanie liczby,
	 * ustawia ja jako maxUczestnikow. Iiczba nie mo¿e byc mniejsza od liczby
	 * zapisanych uczestnikow do tej pory
	 */
	private void mazUczestnikowOgraniczona() {
		boolean ponownieWyswietl = true;
		while (ponownieWyswietl) {
			String maxS = JOptionPane.showInputDialog(getContentPane(), "Podaj liczbê uczestnikow");
			if (maxS != null) {
				try {
					Integer max = Integer.parseInt(maxS);

					if (obslugaImprezy.dodajMaxUczestnikow(max)) {
						ponownieWyswietl = false;
					} else {
						JOptionPane.showMessageDialog(getContentPane(),
								"Za ma³e ograniczenie do liczby obecnych Uczestnikow");
					}
				} catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(getContentPane(), "Podaj liczbê");
				}
			} else {
				ponownieWyswietl = false;
			}
		}
	}

	/**
	 * Akcja prycisku usunWybranychUczestnikow - usuwa wybranych uczestnikow na
	 * liscie po uprzednim potwierdzeniu
	 */
	private void usunWybranychUczestnikow() {
		int[] selectedIndices = listaUczestnikow.getSelectedIndices();
		for (int i = selectedIndices.length - 1; i >= 0; i--) {
			Uczestnik zaznaczonyUczestnik = listaUczestnikow.getModel().getElementAt(selectedIndices[i]);
			int wynik = JOptionPane.showConfirmDialog(getContentPane(),
					"Zaznaczyles " + zaznaczonyUczestnik + ". Usunac?");
			if (wynik == JOptionPane.YES_OPTION) {
				obslugaImprezy.usunUczestnika(zaznaczonyUczestnik);
			}
		}
	}

	/**
	 * Akcja przycisku usunZakupPoprostu z menu przycisku usunZakup - usuwa
	 * wybrane zakupy z listy
	 */
	private void usunZakupPoprostu() {
		int[] selectedIndices = listaZakupow.getSelectedIndices();
		for (int i = selectedIndices.length - 1; i >= 0; i--) {
			obslugaImprezy.usunZakup(listaZakupow.getModel().getElementAt(selectedIndices[i]));
		}
	}

	/**
	 * Akcja przycisku usunZakupZakupione z menu przycisku usunZakup - usuwa
	 * wybrane zakupy z listy, pyta sie czy dodac wydatek, jesli tak prosi o
	 * liczbe wydatku i ja zapisuje
	 */
	private void usunZakupZakupione() {
		int wynik = JOptionPane.showConfirmDialog(getContentPane(), "Zakupiono produkty" + "Czy chcesz dodaæ wydatek?");
		if (wynik == JOptionPane.YES_OPTION) {
			boolean ponownieWyswietl = true;
			while (ponownieWyswietl) {
				String ile = JOptionPane.showInputDialog(getContentPane(), "Podaj kwote");
				if (ile != null) {
					try {
						obslugaImprezy.modyfikujBudzetWydatek(Float.parseFloat(ile));
						usunZakupPoprostu();
						ponownieWyswietl = false;
					} catch (NumberFormatException n) {
						JOptionPane.showMessageDialog(getContentPane(), "Bud¿et to liczba");
						ponownieWyswietl = true;
					}
				} else {
					ponownieWyswietl = false;
				}
			}
		} else if (wynik == JOptionPane.NO_OPTION) {
			usunZakupPoprostu();
		}
	}

	/**
	 * Akcja przycisku modyfikujBudzet - pyta sie czy przychod czy wydatek,
	 * pobiera liczbe, zmienia dane Budzetu
	 */
	private void dodajBudzet() {
		Object[] choices = { "Do³ó¿ do bud¿etu", "Wydatek" };
		String jak = (String) JOptionPane.showInputDialog(getContentPane(), "Modyfikuj Bud¿et", "Bud¿et",
				JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		if (jak != null) {
			boolean ponownieWyswietl = true;
			while (ponownieWyswietl) {
				String ile = JOptionPane.showInputDialog(getContentPane(), "Podaj kwote");
				if (ile != null) {

					try {
						obslugaImprezy.modyfikujBudzet(jak, Float.parseFloat(ile), choices);
						ponownieWyswietl = false;
					} catch (NumberFormatException n) {
						JOptionPane.showMessageDialog(getContentPane(), "Bud¿et to liczba");
						ponownieWyswietl = true;
					}
				} else {
					ponownieWyswietl = false;
				}
			}
		}
	}

	/**
	 * Akcja przycisku usun Zadanie - usuwa wybrane zadania z listy po uprzednim
	 * potwierdzeniu
	 */
	private void usunZadanie() {
		int wynik = JOptionPane.showConfirmDialog(getContentPane(), "Usunac zaznaczone ?");
		if (wynik == JOptionPane.YES_OPTION) {
			int[] selectedIndices = listaZadan.getSelectedIndices();
			for (int i = selectedIndices.length - 1; i >= 0; i--) {
				Zadanie zaznaczoneZadanie = listaZadan.getModel().getElementAt(selectedIndices[i]);
				obslugaImprezy.usunZadanie(zaznaczoneZadanie);
			}
		}
	}

	/**
	 * Akcja zmiany daty - powoduje zmiane daty, daje informacje gdy data ta
	 * jest z przeszlosci
	 */
	private void setDate() {
		calendar.set((int) rokBox.getSelectedItem(), miesiacBox.getSelectedIndex(), (int) dzienBox.getSelectedItem());
		if (!obslugaImprezy.dodajDate(calendar.getTime())) {
			JOptionPane.showInternalMessageDialog(getContentPane(), "Data z przesz³oœci");
		}
	}

	/**
	 * Dodanie litenerow do wszystkich elementow aplikacji
	 */
	private void dodajListenry() {

		dodajUczestnika.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dodajUczestnika();
			}
		});

		maxUczestnikowNieograniczona.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				maxUczestnikowNieograniczona();
			}
		});

		maxUczestnikowOgraniczona.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mazUczestnikowOgraniczona();
			}
		});

		usunWybranych.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				usunWybranychUczestnikow();
			}

		});

		dodajZakup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				obslugaImprezy.dodajZakup(dodajZakup.getText());

			}
		});

		usunZakup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (listaZakupow.getSelectedIndices().length != 0) {
					usunZakupBo.show(usunZakup, (int) usunZakup.getWidth() / 2, (int) usunZakup.getHeight() / 2);
				}
			}
		});
		usunZakupPoprostu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				usunZakupPoprostu();
			}
		});
		usunZakupZakupione.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				usunZakupZakupione();
			}
		});
		dodajZadanie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				obslugaImprezy.dodajZadanie(dodajZadanie.getText());
			}
		});
		usunZadanie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				usunZadanie();
			}
		});

		modyfikujBudzet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dodajBudzet();
			}
		});
		ActionListener listenerDaty = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setDate();
			}

		};
		rokBox.addActionListener(listenerDaty);
		miesiacBox.addActionListener(listenerDaty);
		dzienBox.addActionListener(listenerDaty);

		menuNowy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				setObslugaImprezy(new ObslugaImprezy(new Impreza()));

				defaultUstawienia();
				powiadomOZmianieImprezy();
			}

		});

		menuOtworz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuOtworz();
			}

		});
		menuZapisz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuZapisz();
			}

		});
		menuZapiszJako.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuZapiszJako();
			}
		});
		menuAutoZapis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuAutoZapis();
			}
		});

	}

	/**
	 * Akcja przycisku menuOtworz - powoduje otworzenie imprezy zapisanej na
	 * komputerze
	 */
	private void menuOtworz() {
		File file = wyborPliku();
		if (file != null) {
			ObslugaImprezy pobranaObslugaImprezy = zapisywanie.otworz(file);
			if (pobranaObslugaImprezy != null) {
				setObslugaImprezy(pobranaObslugaImprezy);
				powiadomOZmianieImprezy();
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "B³¹d Oczytu Pliku");
			}
		}
	}

	/**
	 * Akcja przycisku menuZapisz - powwoduje zapisanie imprezy, jesli nie byla
	 * zapisana wczesniej prosi o wybranie sciezki
	 */
	private void menuZapisz() {
		if (!zapisywanie()) {
			JOptionPane.showMessageDialog(getContentPane(), "B³¹d Zapisu Pliku");
		}
	}

	/**
	 * Akcja przycisku menuZapiszJako - powoduje zapisanie imprezy na wybranej
	 * sciezce
	 */
	private void menuZapiszJako() {
		if (!zapisywanieJako()) {
			JOptionPane.showMessageDialog(getContentPane(), "B³¹d Zapisu Pliku");
		}
	}

	/**
	 * Akcja CheckBoxa menuAutoZapis - zaznaczone powoduje AutoZapis przy ka¿dej
	 * zmianie w obiekcie Impreza, jesli nie by³a wczesniej zapisana wczesniej
	 * prosi o wybranie sciezki Odznaczenie wylacza te funkcje
	 */
	private void menuAutoZapis() {
		if (menuAutoZapis.isSelected()) {
			if (zapisywanie()) {
				// System.out.println("nie ma autozapisu");
				zapisywanie.dodajSieDoObserwowania(obslugaImprezy);
				// obslugaImprezy.getImpreza().setCzyAutoZapis(true);
			}
		} else {
			zapisywanie.wypiszSieZObserwowania(obslugaImprezy);
			// obslugaImprezy.getImpreza().setCzyAutoZapis(true);
		}
	}

	/**
	 * Wyswietla dialog wyboru sciezki do odczytu/ zapisu
	 * 
	 * @return plik do odczytu/zapisu, null gdy przerwano akcje
	 */
	private File wyborPliku() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(getContentPane());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		} else {
			return null;
		}
	}

	/**
	 * Zapisanie obiektu do wybranej sciezki
	 * 
	 * @return true jesli zakonczone pozytywnie, false w p.p.
	 */
	private boolean zapisywanieJako() {
		File file = wyborPliku();
		if (file != null) {
			if (zapisywanie.zapiszJako(obslugaImprezy.getImpreza(), file)) {
				setTytul();
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Nadpisanie obiektu w pliku
	 * 
	 * @return true jesli zakonczone pozytywnie, false w p.p.
	 */
	private boolean zapisywanie() {
		if (obslugaImprezy.getImpreza().isCzyZapisane()) {
			if (zapisywanie.zapis(obslugaImprezy.getImpreza(), obslugaImprezy.getImpreza().getSciezka())) {
				return true;
			} else {
				return false;
			}

		} else {
			return zapisywanieJako();
		}
	}

	/**
	 * Pobranie obslugiImprezy na ktorej wykonywane sa zmiany
	 * 
	 * @return obslugaImprezy
	 */
	public ObslugaImprezy getObslugaImprezy() {
		return obslugaImprezy;
	}

	/**
	 * Nadanie obslugi imprezy. Zapisanie okna jako obserwatora zmian atrybutow
	 * Imprerzy
	 * 
	 * @param obslugaImprezy
	 */
	public void setObslugaImprezy(ObslugaImprezy obslugaImprezy) {
		// System.out.println("set" + obslugaImprezy.getImpreza().getSciezka());
		this.obslugaImprezy = obslugaImprezy;
		// System.out.println("set" +
		// this.obslugaImprezy.getImpreza().getSciezka());
		obslugaImprezy.dodajObserwatoraListyUczestnikow(this);
		obslugaImprezy.dodajObserwatoraListyZakupow(this);
		obslugaImprezy.dodajObserwatoraListyZadan(this);
		obslugaImprezy.dodajObserwatoraBudzetu(this);
	}

	/**
	 * Akcja wykonywana po powiadomieniu o zmianie w uczestnikach - odswiezenie
	 * listy uczestnikow oraz wolnych miejsc
	 */
	@Override
	public void powiadomOZmianieWUczestnikach() {
		listaUczestnikow.setListData(obslugaImprezy.getImpreza().getUczestnicy().toArray(new Uczestnik[] {}));

		if (obslugaImprezy.czyIloscMiejscOgraniczona()) {
			wolneMiejsca.setText("wolne miejsca: " + obslugaImprezy.ileWolnychMiejsc() + "z "
					+ obslugaImprezy.getImpreza().getMaxUczestnikow());
			if (obslugaImprezy.ileWolnychMiejsc() == 0) {
				wolneMiejsca.setText("Wszystkie miejsca zajete");
			}
		} else {
			wolneMiejsca.setText("wolne miejsca: nieograniczone ");
		}
	}

	/**
	 * Akcja wykonywana po powiadoniemiu o zmianie w zakupach -
	 * odswiezenielistyzakupow
	 */
	@Override
	public void powiadomOZmianieWZakupach() {
		listaZakupow.setListData(obslugaImprezy.getImpreza().getZakupy().toArray(new Zakup[] {}));
	}

	/**
	 * Akcja wykonywana po powiadomieniu o zmianie w budzenic - odswiezenie
	 * informacji dotyczacych budzetu
	 */
	@Override
	public void powiadomOZmianieWBudzecie() {
		przeznaczonyBudzet.setText(
				"Przeznaczony Bud¿et: " + Float.toString(obslugaImprezy.getImpreza().getBudzet().getPrzeznaczone()));
		wydaneBudzet.setText("Wydatki: " + Float.toString(obslugaImprezy.getImpreza().getBudzet().getWydane()));
		pozostalyBudzet.setText("Pozosta³y Bud¿et: " + Float.toString(obslugaImprezy.pozostaloBudzet()));
		budzetBar.setValue(obslugaImprezy.procentIleWydane());
		if (!obslugaImprezy.czyDostepnyBudzet()) {
			budzetBar.setString("PRZEKROCZONO BUDZET");
			budzetBar.setStringPainted(true);
		} else {
			budzetBar.setString(obslugaImprezy.procentIleWydane() + "%");
			budzetBar.setStringPainted(true);
		}
	}

	/**
	 * 
	 * Akcja wykonywana po powiadoniemiu o zmianie w zadaniach -
	 * odswiezenielistyzadan
	 */
	@Override
	public void powiadomOZmianieWZadaniach() {
		listaZadan.setListData(obslugaImprezy.getImpreza().getZadania().toArray(new Zadanie[] {}));

	}

	/**
	 * 
	 * Akcja wykonywana po powiadoniemiu o zmianie Imprezy - odswiezenie okna
	 * dla nowej Imprezy - powiadomienie o zmianie uczestnikow, zadan, zakupow ,
	 * budzetu, ustawienie ustawien
	 */
	public void powiadomOZmianieImprezy() {
		powiadomOZmianieWBudzecie();
		powiadomOZmianieWUczestnikach();
		powiadomOZmianieWZakupach();
		powiadomOZmianieWZadaniach();

		setTytul();
		if (obslugaImprezy.czyIloscMiejscOgraniczona()) {
			maxUczestnikowOgraniczona.setSelected(true);
		}

		rokBox.setSelectedItem(obslugaImprezy.getImpreza().getData().getDate());
		miesiacBox.setSelectedIndex(obslugaImprezy.getImpreza().getData().getMonth());
		rokBox.setSelectedItem(obslugaImprezy.getImpreza().getData().getYear());

	}

	/**
	 * Nadanie tytulu oknu
	 */
	private void setTytul() {
		this.setTitle(obslugaImprezy.getImpreza().getSciezka());

	}

}
