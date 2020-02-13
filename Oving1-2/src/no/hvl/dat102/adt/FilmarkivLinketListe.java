package no.hvl.dat102.adt;

import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;

/**
 * Linket liste klasse som implementerer interfacet FilmarkivADT
 * 
 * 
 * @authors Eskil Oscar Ehrensvärd, Per Otto Sande Furre
 *
 */
public class FilmarkivLinketListe implements FilmarkivADT {

	/**
	 * Generisk klasse med enkel linket liste struktur. Har enkle getters og
	 * setters.
	 * 
	 * @author Eskil Oscar Ehrensvärd, Per Otto Sande Furre
	 *
	 * @param <T> Generisk object
	 */
	public class LinearNode<T> {

		private LinearNode<T> neste;
		private T element;

		public LinearNode(T element) {
			this.element = element;
			neste = null;

		}

		public LinearNode() {

			neste = null;
		}

		public LinearNode<T> getNeste() {
			return neste;
		}

		public T getElement() {
			return element;
		}

		public void setNeste(LinearNode<T> neste) {
			this.neste = neste;
		}

	}

	private LinearNode<Film> start; // start
	private int antall;

	// i motsettning til tabell hvor vi ønsker å vite hvor stor tabell vi trenger
	// setter vi her antall = 0-
	public FilmarkivLinketListe(int antall) {

		this.antall = 0;
		start = null;

	}

	@Override
	public void leggTilFilm(Film nyFilm) {

		// tilfelde hvor listen er tom.
		if (start == null) {
			start = new LinearNode<Film>(nyFilm);
			antall++;
			return;
		}

		// her har vi to metode implementeringer,
		// hvor forskjellen er om nytt element blir lagt bak eller foran
		// I listen. Siden vi ikke bryr oss om dette er det å sette start til å peke på
		// nye objecktet
		// raskere en å gå gjennom helle listen per object,

		LinearNode<Film> temp = start;
		LinearNode<Film> ny = new LinearNode<Film>(nyFilm);

//		Her itererer vi gjennom listen til vi kommer til slutten og setter inn nyeste object.
//		om vi har 5000 elementer vil dette bety 5000*n som er en O=(n) 
//		while (temp.getNeste() != null) {
//			temp = temp.getNeste();
//		}
//		temp.setNeste(ny);

		// logikken er, over har vi satt temp til start slik at vi under ikke mister
		// når vi sier start=ny. Setter så start.neste til forige start og er ferdig.
		// Siden det kun er konstanter her er O=(1)
		start = ny;
		start.setNeste(temp);
		antall++;

	}

	@Override
	public boolean slettFilm(int filmNr) {

		LinearNode<Film> temp = start;
		LinearNode<Film> prev = null;

		// om vi skal slette første posisjon gjør vi special case og setter ny start.
		if (temp != null && temp.getElement().getFilmID() == filmNr) {
			start = temp.getNeste();
			antall--;
			return true;
		}
		// itererer til vi finner riktig også fjerner referense.
		while (temp != null && temp.getElement().getFilmID() != filmNr) {
			prev = temp;
			temp = temp.getNeste();
		}
		// om tabellen er tom
		if (temp == null)
			return false;

		prev.setNeste(temp.getNeste());
		antall--;
		// prev.neste = temp.neste;
		return true;
	}

	@Override
	public Film[] sokTittel(String delstreng) {

		Film searchTab[] = new Film[antall];
		LinearNode<Film> temp = start;

		int sokTall = 0;

		while (temp != null) {

			if (temp.getElement().getTittel().toLowerCase().contains(delstreng.toLowerCase())) {

				searchTab[sokTall] = temp.getElement();
				sokTall++;

			}

			temp = temp.getNeste();

		}
		return searchTab;

	}

	@Override
	public Film[] sokProdusent(String delstreng) {

		Film searchTab[] = new Film[antall];
		LinearNode<Film> temp = start;

		int sokTall = 0;

		while (temp != null) {

			if (temp.getElement().getProdusent().toLowerCase().contains(delstreng.toLowerCase())) {

				searchTab[sokTall] = temp.getElement();
				sokTall++;

			}

			temp = temp.getNeste();

		}
		return searchTab;

	}

	@Override
	public int antallSjanger(Sjanger sjanger) {
		int antallSjanger = 0;
		LinearNode<Film> temp = start;

		while (temp != null) {
			if (temp.getElement().getSjanger().equals(sjanger)) {

				antallSjanger++;
			}
			temp = temp.getNeste();
		}
		return antallSjanger;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public Film[] hentFilmTabell() {

		if (antall == 0) {
			return new Film[0];
		}

		LinearNode<Film> current = start;
		// pga vi setter hvert nye objekt som start ender vi opp med start på siste
		// elementet i listen
		// Så for å få listen ut riktig vei setter vi inn i tabell fra høyeste nr i
		// tabellen og itererer motsatvei mot 0.
		Film tab[] = new Film[antall];
		for (int i = antall - 1; i >= 0; i--) {

			tab[i] = current.getElement();
			current = current.getNeste();

		}

		return tab;
	}

}
