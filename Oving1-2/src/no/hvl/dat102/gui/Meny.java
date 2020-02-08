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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import no.hvl.dat102.Fil;
import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;
import no.hvl.dat102.adt.FilmArchiveADT;
import no.hvl.dat102.klient.KlientFilmarkiv;

public class Meny {

	private Font font = new Font("Courier New", Font.BOLD, 15);

	private Tekstgrensesnitt tekstgr;
	private FilmArchiveADT filma;
	private String filNavn = null;

	// Frame / mainvindu med panel og textArea( der hvor arkiv printes til)
	private JFrame mainVindu = new JFrame("Verdens beste filmarkiv");
	private JPanel mainPanel = new JPanel();
	private JTextArea mainVinduTA = new JTextArea();

	// MenyBar
	private JMenuBar mb = new JMenuBar();

	// Menyer
	private JMenu fil = new JMenu("Fil");
	private JMenu hjelp = new JMenu("Hjelp");
	private JMenu arkiv = new JMenu("Arkiv");

	public Meny(FilmArchiveADT filma) {
		tekstgr = new Tekstgrensesnitt();
		this.filma = filma;

	}

	public void start() {

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
		mb.add(fil); // festet fil til menybar
		mb.add(hjelp);

		// lager meny elementer, og deres under elementer og fester dem med metoder og
		// action listener med metoden attachComponents
		MenyComponent.attachComponents(fil, this, "Nytt", "Opprett", 1);
		MenyComponent.attachComponents(fil, this, "Åpne", "Åpne", 2);
		MenyComponent.attachComponents(fil, this, "Lagre", "Lagre", 3);
		MenyComponent.attachComponents(fil, this, "Lagre som", "Lagre som", 4);
		MenyComponent.attachComponents(arkiv, this, "Legg til", "Legg til film", 5);
		MenyComponent.attachComponents(arkiv, this, "Slett", "Slett filmerID", 6);
		MenyComponent.attachComponents(arkiv, this, "Søk Tittel", "Søk", 7);
		MenyComponent.attachComponents(arkiv, this, "Søk Produsent", "Søk", 9);
		MenyComponent.attachComponents(arkiv, this, "Info", "", 10);

		MenyComponent.attachComponents(hjelp, this, "Hjelp2", "", 8);

		// exit betingelser
		mainVindu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainVindu.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				if (filNavn != null) {
					lagreTilFil();
				}
				// mainVindu.dispose();
//				openVindu.dispose();
//				opprettVindu.dispose();
//				lagreSomVindu.dispose();
				System.exit(0);
			}
		});

	}

	public void opprettArkiv(JTextField tfInput) {
		String x = tfInput.getText(); // lese text fra textfield
		// Skjekke om filnavn finnes
		try {
			File opprettetFil = new File(x + ".txt");
			if (opprettetFil.exists()) {

				mainVinduTA.setText("");
				mainVinduTA.append("Det finnes allerede en fil med samme navn. \nPrøv et annet navn");
				return;
			}
			opprettetFil.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();

		}

		filNavn = x; // setter filnavn til userinput fra textfield
		FilmArchiveADT ny = KlientFilmarkiv.sendNewArchive(0); // oppretter nytt film arkiv med størresle 0.
		Fil.skrivTilFil(ny, filNavn + ".txt"); // skriver til fil. får da en text fil med kunn et nulltall. Altsa "0"
		filma = ny; // sier at Filma referansen skal peke på det nye filmarkiv objectet

	}

	// metode som kalles nÅr Åpne knapp i openVindu trykkes
	// leser input fra txtfield og Åpner doc
	public void openFile(JTextField tfInput, Meny main) {

		String x = tfInput.getText(); // lese text fra textfield

		File skjekk = new File(x + ".txt");
		if (!skjekk.exists()) {
			mainVinduTA.setText("");
			mainVinduTA.append("Fil ikke funnet!");
			SwingUtilities.updateComponentTreeUI(mainVindu); // oppdaterer mainVindu slik at Arkiv meny vises
			return;
		}

		filNavn = x; // lagrer filnavn string for lagring seinere

//		FilmarkivADT sizeCheck = Fil.lesFraFil(x + ".txt" , 0, 0);
//		System.out.println(sizeCheck.hentFilmTabell().length);
//		
//		if(sizeCheck.hentFilmTabell().length >100) {
//		
//			filma = Fil.lesFraFil(x + ".txt" , 0, 100);
//			
//			mainVinduTA.append(tekstgr.visFilmer(filma.hentFilmTabell(), 0, 100));
//			SwingUtilities.updateComponentTreeUI(mainVinduTA);
//		}

		filma = Fil.lesFraFil(x + ".txt"); // leser fra fil med filnavn x + ".txt"

	}

	// Lagre

	public void lagreTilFil() {

		Fil.skrivTilFil(filma, filNavn + ".txt");

	}

	public void lagreSomTilFil(JTextField tfInput) {

		String x = tfInput.getText(); // lese text fra textfield
		File skjekk = new File(x + ".txt"); // lager File object med string input + .text. Feks arkiv55.txt
		if (skjekk.exists()) { // skjekker om det finnest i mappen fra før

			mainVinduTA.setText(""); // om fil med likt filnavn eksisterer gjør clean up og stop prosess
			mainVinduTA.append("Det finnes allerede en fil med samme navn. \nPrøv et annet navn");

			return;
		}

		filNavn = x; // lagrer filnavn string for lagring seinere
		FilmArchiveADT temp = filma; // lager midlertidig peker til filma

		opprettArkiv(tfInput); // oppretter tomt arkiv med nyttFilnavn
		filma = temp; // får filma til å peke på temp object ( orginale filmarkivet vi ønsket å lagre)
						// filma blir satt til et tomt filmarkiv i opprettArkiv()
		lagreTilFil(); // lagrer til nye fil

	}

	// Metode som sletter tekst og skrive git gud
	public void openHjelpKlikk() {
		mainVinduTA.setText("");
		mainVinduTA.append("git gud");
	}

	public void leggTilKlikk(MenyComponent textfields) {

		Film temp = new Film(Integer.parseInt(textfields.getId().getText()), textfields.getProdusent().getText(),
				textfields.getTittel().getText(), Integer.parseInt(textfields.getAar().getText()),
				Sjanger.finnSjanger(textfields.getSjanger().getText()), textfields.getSelskap().getText());

		// Skjekk alle innputs
		filma.addMovie(temp);

	}

	public void slettFilm(JTextField tfInput) {

		filma.removeFilm(Integer.parseInt((tfInput.getText())));

	}

	public void sokTittel(JTextField tfInput) {
		Film[] temp = filma.siftTitle(tfInput.getText());

		String str = tekstgr.visFilmer(temp, 0, temp.length);

		mainVinduTA.setText("");
		mainVinduTA.append(tekstgr.printFilmCategory());
		mainVinduTA.append(str);
		// vise resultat av listen
		// ta tabell fra Filmarkiv.sok og kjøre igjennom tekstgrensesnitt metode kalt
		// visFilmer

	}

	public void sokProd(JTextField tfInput) {
		Film[] temp = filma.siftProducer(tfInput.getText());

		String str = tekstgr.visFilmer(temp, 0, temp.length);

		mainVinduTA.setText("");
		mainVinduTA.append(str);
		// vise resultat av listen
		// ta tabell fra Filmarkiv.sok og kjøre igjennom tekstgrensesnitt metode kalt
		// visFilmer

	}

	public void refresh() {

		mainVinduTA.setText(""); // fjerne tekst fra TextArea i hoved vindu
		mainVinduTA.append(tekstgr.printFilmCategory());
		mainVinduTA.append(tekstgr.visFilmer(filma.retrieveFilmArray(), 0, filma.retrieveFilmArray().length));

		mb.remove(hjelp); // fjerner Hjelp meny, legger til Arkiv meny, legger til Hjelp meny
		mb.add(arkiv);
		mb.add(hjelp);
		mainVindu.setTitle("Arkiv: " + filNavn);
		SwingUtilities.updateComponentTreeUI(mainVindu); // oppdaterer mainVindu slik at Arkiv meny vises
	}

	public void info() {
		mainVinduTA.setText("");
		mainVinduTA.append(tekstgr.skrivUtStatistikk(filma) + "\n\n\n\n");
		mainVinduTA.append(tekstgr.visFilmer(filma.retrieveFilmArray(), 0, filma.retrieveFilmArray().length));

	}

	public JTextArea getMainVinduTA() {
		return mainVinduTA;
	}

}
