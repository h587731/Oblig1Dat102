package no.hvl.dat102.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import no.hvl.dat102.Fil;
import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;
import no.hvl.dat102.adt.FilmarkivADT;
import no.hvl.dat102.klient.KlientFilmarkiv;

/**
 * Gui klasse som jobber med FilmarkivADT, Tekstgrensesnitt MenyComponent, Fil
 * og Film klassene. Tegner hoved rammen til gui vindu, og tegner så meste
 * parten av komponenter via hjelpe klassen MenyComponent. Denne klassen var
 * orginalt alene uten MenyComponent, og nær 900 linjer med kode. Så at det var
 * ekstremt mye repetative kode struktur. Vi valgte derfor å skrive om til en
 * mer generisk struktur og MenyComponent ble laget for å ta
 * autogenereringsprossesen ut koden.
 * 
 * @author Eskil Oscar Ehrensvärd, Per Otto Sande Furre
 *
 */
public class Meny {

	private Font font = new Font("Courier New", Font.BOLD, 15);
	private Tekstgrensesnitt tekstgr;
	private FilmarkivADT filma;
	private String filNavn = null;
	private boolean typeStruktur;
	private boolean lagtTil = false;
	private JFrame mainVindu = new JFrame("Verdens beste filmarkiv");

	private JPanel mainPanel = new JPanel();
	private JTextArea mainVinduTA = new JTextArea();

	// MenyBar
	private JMenuBar mb = new JMenuBar();

	// Menyer

	private JMenu fil = new JMenu("Fil");
	private JMenu hjelp = new JMenu("Hjelp");
	private JMenu arkiv = new JMenu("Arkiv");
	private MenyComponent comp = new MenyComponent();

	public Meny(FilmarkivADT filma) {
		tekstgr = new Tekstgrensesnitt();
		this.filma = filma;

	}

	/**
	 * Kalles fra KlientArkiv ved start av main method.
	 * 
	 * Metode som bygger Gui. Venter først på svar fra bruker om type datastruktur
	 * som skal brukes.
	 * 
	 * Lager Hoved frame med Swing objecter også lager vi resten av elementer med
	 * comp objectet som kaller på attachComponents()
	 * 
	 * Setter så lagre på til exitAction
	 * 
	 * 
	 */
	public void start() {

		Object[] options = { "Tabell", "LinketListe", "Avbryt" };
		int n = JOptionPane.showOptionDialog(new JFrame(), "Velg type arkivstruktur", "Filmarkivet 2000",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
		if (n == -1 || n == 2)
			return;

		if (n == 1) {
			setTypeStruktur(true);

		} else {
			setTypeStruktur(false);

		}

		// Main vindu
		mainVindu.setSize(1325, 800); // setter opp main vindu

		// scroll funksjon i textArea
		JScrollPane scrollPane = new JScrollPane(mainVinduTA, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		mainVindu.add(scrollPane);
		mainVindu.getContentPane().add(BorderLayout.SOUTH, mainPanel); // fester mainPanel
		mainVindu.getContentPane().add(BorderLayout.NORTH, mb); // fester menyBar og vindu
		mainVindu.getContentPane().add(BorderLayout.CENTER, scrollPane); // Legger main vindu textArea til via scollPane
		mainVindu.setVisible(true);
		mainVindu.setFont(font); // Main vindu font

		mainVinduTA.setFont(font); // main vindu textArea font og uneditable
		mainVinduTA.setEditable(false);
		mainVinduTA.append("Velkommen!");

		mb.add(fil);
		mb.add(hjelp);
		filma = KlientFilmarkiv.sendNytt(0, isTypeStruktur());

		// lager meny elementer, og deres under elementer og fester dem med metoder og
		// action listener med metoden attachComponents

		comp.attachComponents(fil, this, "Nytt", "Opprett", 1);
		comp.attachComponents(fil, this, "Åpne", "Åpne", 2);

		comp.attachComponents(arkiv, this, "Legg til", "Legg til film", 5);
		comp.attachComponents(arkiv, this, "Slett", "Slett filmerID", 6);
		comp.attachComponents(arkiv, this, "Søk Tittel", "Søk", 7);
		comp.attachComponents(arkiv, this, "Søk Produsent", "Søk", 9);
		comp.attachComponents(arkiv, this, "Info", "", 10);

		comp.attachComponents(hjelp, this, "Hjelp", "", 8);

		// exit betingelser som lagrer ved lukking av programmet
		mainVindu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainVindu.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				if (filNavn != null) {
					lagreTilFil();
				}
				mainVindu.dispose();

				System.exit(0);
			}
		});

	}

	/**
	 * Oppretter arkiv fil. Metode som kalles fra methodChoice() i MenyComponnt
	 * klassen. Som igjen blir kalt via actionListener som er festet til Opprett
	 * nytt arkiv popup vindu.
	 * 
	 * tar inn string fra textfield fra popup vindu og tester om det finnest fil med
	 * samme navn om fil navn ledig oppretter ny fil med filmarkiv størrelse 0.
	 * 
	 * @param tfInput textfield som er navn på fil vi ønsker opprettet.
	 */
	public void opprettArkiv(JTextField tfInput) {
		String x = tfInput.getText(); // lese text fra textfield
		// Skjekke om filnavn finnes
		try {
			File opprettetFil = new File(x + ".txt");
			if (opprettetFil.exists()) {

				JOptionPane.showMessageDialog(new JFrame(),
						"Det finnes allerede en fil med samme navn. \nPrøv et annet navn", "Feil!",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			opprettetFil.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();

		}

		festLagreMeny();
		filNavn = x; // setter filnavn til userinput fra textfield
		FilmarkivADT ny = KlientFilmarkiv.sendNytt(0, typeStruktur); // oppretter nytt film arkiv med størresle 0.
		Fil.skrivTilFil(ny, filNavn + ".txt"); // skriver til fil. får da en text fil med kunn et nulltall. Altsa "0"
		filma = ny; // sier at Filma referansen skal peke på det nye filmarkiv objectet

		refresh();
	}

	/**
	 * Åpner ønsket fil om mulig Metode som kalles fra methodChoice() i MenyComponnt
	 * klassen. Som igjen blir kalt via actionListener som er festet til Åpne nytt
	 * arkiv popup vindu.
	 * 
	 * tar inn string fra textfield fra popup vindu og tester om det finnest fil med
	 * samme navn. Åpner om det finnes
	 * 
	 * @param tfInput textfield som er navn på fil vi ønsker å Åpne
	 */
	public void openFile(JTextField tfInput, Meny main) {

		String x = tfInput.getText(); // lese text fra textfield

		File skjekk = new File(x + ".txt");
		if (!skjekk.exists()) {
			JOptionPane.showMessageDialog(new JFrame(), "Fil ikke funnet! \nPrøv et annet navn", "Feil",
					JOptionPane.WARNING_MESSAGE);

			return;
		}

		filNavn = x; // lagrer filnavn string for lagring seinere
		filma = Fil.lesFraFil(x + ".txt", isTypeStruktur()); // leser fra fil med
																// filnavn x +
																// ".txt"
		festLagreMeny();
		refresh();
	}

	/**
	 * Lagrer filmarkiv til fil det ble lagret til opprettet, eller åpnet fra.
	 * 
	 * Metode som kalles fra methodChoice() i MenyComponnt klassen. Som igjen blir
	 * kalt via actionListener som er festet til lagre valg i Fil meny.
	 * 
	 * Sender filnavn og filmarkiv til skrivTilFil()
	 * 
	 */
	public void lagreTilFil() {

		Fil.skrivTilFil(filma, filNavn + ".txt");

	}

	/**
	 * Lagrer til ny fil om nyfil ikke eksisterer
	 * 
	 * Metode som kalles fra methodChoice() i MenyComponnt klassen. Som igjen blir
	 * kalt via actionListener som er festet til LagreSom nytt arkiv popup vindu.
	 * 
	 * tar inn string fra textfield fra popup vindu og tester om det finnest fil med
	 * samme navn. Om navn ledig. Oppretter nyfill med opprett() også lagreTilFil()
	 * 
	 * @param tfInput textfield som er navn på fil vi ønsker å Lagre til
	 */
	public void lagreSomTilFil(JTextField tfInput) {

		String x = tfInput.getText(); // lese text fra textfield
		File skjekk = new File(x + ".txt"); // lager File object med string input + .text. Feks arkiv55.txt
		try {

			if (skjekk.exists()) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Det finnes allerede en fil med samme navn. \nPrøv et annet navn", "Feil",
						JOptionPane.WARNING_MESSAGE);

				return;
			}
			skjekk.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();

		}

		filNavn = x; // lagrer filnavn string for lagring seinere
		FilmarkivADT temp = filma; // lager midlertidig peker til filma

		opprettArkiv(tfInput); // oppretter tomt arkiv med nyttFilnavn
		filma = temp; // får filma til å peke på temp object ( orginale filmarkivet vi ønsket å lagre)
						// filma blir satt til et tomt filmarkiv i opprettArkiv()
		lagreTilFil(); // lagrer til nye fil
		refresh();
	}

	/**
	 * Gir bruker kort informasjon
	 * 
	 */
	public void openHjelpKlikk() {
		mainVinduTA.setText("");
		mainVinduTA.append("For å få tilgang på Arkiv-valg Åpne eller oppret Nytt Arkiv. \n"
				+ "\n\nFor å teste hvilke filstruktur prøv å slette mer en en film med samme film ID"
				+ "\ntabell strukturen sletter alle forekomster av sletteID mens linket\n"
				+ "liste strukturen kun sletter første forekomst.\n\n" + "Får å få vekk denne teksten velg enten:\n"
				+ "Nytt, Åpne, Lagre, LagreSom, søk tittel eller produsent med tomt tekstfelt" + "\neller vi info.");
	}

	/**
	 * Metode som tar input fra brukeri popup vindu i gui sender til
	 * FilmarkivADT.leggTilFilm()
	 * 
	 * Skriver så ut i textarea
	 * 
	 * @param tfInput TextFields fra pop up boks
	 */
	public void leggTilKlikk(MenyComponent textfields) {

		String tittel = textfields.getTittel().getText();
		String produsent = textfields.getProdusent().getText();
		String selskap = textfields.getSelskap().getText();
		// Prøver å opprette film med inputs, kaster exeptions hvor nødvendig
		try {
			if (tittel.isEmpty() || produsent.isEmpty() || selskap.isEmpty()) {
				throw new RuntimeException("Tittel, produsent og selskap må ha tekstinput");
			}

			Film temp = new Film(Integer.parseInt(textfields.getId().getText()), textfields.getProdusent().getText(),
					textfields.getTittel().getText(), Integer.parseInt(textfields.getAar().getText()),
					Sjanger.finnSjanger(textfields.getSjanger().getText()), textfields.getSelskap().getText());

			// Legger til i filmarkiv
			filma.leggTilFilm(temp);

		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(new JFrame(), "Kun heltall! \nPrøv igjen", "Feil",
					JOptionPane.WARNING_MESSAGE);

		} catch (RuntimeException er) {

			JOptionPane.showMessageDialog(new JFrame(), "Film trenger tittel, produsent \nog selskap! \nPrøv igjen",
					"Feil", JOptionPane.WARNING_MESSAGE);

		}

	}

	public void slettFilm(JTextField tfInput) {
		try {
			filma.slettFilm(Integer.parseInt((tfInput.getText())));

		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(new JFrame(), "Kun heltall! \nPrøv igjen", "Feil",
					JOptionPane.WARNING_MESSAGE);

		}

	}

	/**
	 * Metode som tar input fra sokTittel popup vindu i gui sender til
	 * tekstgrensesnitt skrivUtTittel(), som sender videre til respektiv Filmarkiv
	 * klasse(Tabell eller Linket liste) for søking.
	 * 
	 * Skriver så ut i textarea
	 * 
	 * @param tfInput TextField fra pop up boks
	 */
	public void sokTittel(JTextField tfInput) {

		mainVinduTA.setText("");
		mainVinduTA.append(tekstgr.printFilmCategory());
		mainVinduTA.append(tekstgr.skrivUtFilmDelstrengITittel(filma, tfInput.getText()));
	}

	/**
	 * Metode som tar input fra sokProdusent popup vindu i gui sender til
	 * tekstgrensesnitt skrivUtProdl(), som sender videre til respektiv Filmarkiv
	 * klasse(Tabell eller Linket liste) for søking.
	 * 
	 * Skriver så ut i textarea
	 * 
	 * @param tfInput TextField fra pop up boks
	 */
	public void sokProd(JTextField tfInput) {
		mainVinduTA.setText("");
		mainVinduTA.append(tekstgr.printFilmCategory());
		mainVinduTA.append(tekstgr.skrivUtFilmProdusent(filma, tfInput.getText()));
	}

	public void refresh() {

		mainVinduTA.setText(""); // fjerne tekst fra TextArea i hoved vindu
		mainVinduTA.append(tekstgr.printFilmCategory());
		mainVinduTA.append(tekstgr.visFilmer(filma.hentFilmTabell(), 0, filma.hentFilmTabell().length));

		mb.remove(hjelp); // fjerner Hjelp meny, legger til Arkiv meny, legger til Hjelp meny
		mb.add(arkiv);
		mb.add(hjelp);
		mainVindu.setTitle("Arkiv: " + filNavn);
		SwingUtilities.updateComponentTreeUI(mainVindu); // oppdaterer mainVindu slik at Arkiv meny vises
	}

	private void festLagreMeny() {
		if (!lagtTil) {
			comp.attachComponents(fil, this, "Lagre", "Lagre", 3);
			comp.attachComponents(fil, this, "Lagre som", "Lagre som", 4);
			lagtTil = true;

		}
	}

	public void info() {
		mainVinduTA.setText("");
		mainVinduTA.append(tekstgr.skrivUtStatistikk(filma) + "\n\n\n\n");
		mainVinduTA.append(tekstgr.printFilmCategory());
		mainVinduTA.append(tekstgr.visFilmer(filma.hentFilmTabell(), 0, filma.hentFilmTabell().length));

	}

	// Get metoder brukt i deler av programmet
	public JTextArea getMainVinduTA() {
		return mainVinduTA;
	}

	public boolean isTypeStruktur() {
		return typeStruktur;
	}

	public void setTypeStruktur(boolean typeStruktur) {
		this.typeStruktur = typeStruktur;
	}

}
