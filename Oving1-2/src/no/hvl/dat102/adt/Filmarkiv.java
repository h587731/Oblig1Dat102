package no.hvl.dat102.adt;

import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;

public class Filmarkiv implements FilmarkivADT {

	private Film[] filmtabell;
	private int antall;

	public Filmarkiv(int antall) {
		this.antall = antall;
		this.filmtabell = new Film[antall];
	}

	@Override
	public Film[] hentFilmTabell() {
		// TODO Auto-generated method stub
		return filmtabell;
	}

	private void utvidKapasitet() {
		// eks. utvide 10

		Film[] hjelpetabell = new Film[(filmtabell.length) + 1];

		for (int i = 0; i < filmtabell.length; i++) {
			hjelpetabell[i] = filmtabell[i];
		}

		antall = antall + hjelpetabell.length - filmtabell.length;
		filmtabell = hjelpetabell;

	}

	@Override
	public void leggTilFilm(Film nyFilm) {
		boolean ikkePlass = true;

		// F�rst skjekk om tabellen er tom
		if (filmtabell.length == 0) {
			Film[] tempTab = new Film[1];

			filmtabell = tempTab;
			filmtabell[0] = nyFilm;
			antall = 1;
			ikkePlass = false;
		} else {

			// finne ledig plass
			for (int i = 0; i < filmtabell.length; i++) {
				if (filmtabell[i] == null) {
					filmtabell[i] = nyFilm;

					ikkePlass = false;
					break;
				}
			}
		}

		// lage ny tabell om ikke plass
		if (ikkePlass) {

			utvidKapasitet();
			leggTilFilm(nyFilm);

		}

	}

	private int pos = 0;

	@Override
	public boolean slettFilm(int filmNr) {

		boolean harSlettet = false;

		// først håndter special case om tabbel har 1 i størrelse
		if (filmtabell.length == 0) {
			return false;
		}

		Film[] kopi = new Film[antall - 1];

// finn filmID

		// set i = pos
		for (int i = pos; i < filmtabell.length; i++) {
			// lete etter filmNR
			if (filmNr == filmtabell[i].getFilmID()) {

				// match slette lage ny tabell - alts� skifter referansen til � peke p� samme
				// object som siste referance peker p�
				filmtabell[i] = filmtabell[antall - 1];
				pos = i;
				// kopier over gammel tabell til nye tabell som er 1 mindre
				for (int k = 0; k < filmtabell.length - 1; k++) {
					kopi[k] = filmtabell[k];

				}

				// oppdaterer antall og ny filmtabell og ser etter flere filmer � slette
				antall--;
				filmtabell = kopi;
				slettFilm(filmNr);
				pos = 0;
				return true;

			}

		}

		return harSlettet;
	}

	@Override
	public Film[] sokTittel(String delstreng) {

		// Oppretter tabell hvor vi legger funn fra s�k
		Film[] sokTab = new Film[antall];
		int sokTall = 0;

		// itererer igjennom filmarkivet og s�ker p� delstreng
		for (Film obj : filmtabell) {

			// om fokusert film.sinTittel har delstreng
			if (obj.getTittel().contains(delstreng)) {
				// legg til fun i s�ketabell
				sokTab[sokTall] = obj;
				// utvid siden vi satt den til � bare v�re 1 stor til � begynne med, blir s� 2 i
				// st�rresle. utvides ved vert funn som er en d�rlig l�sning i hindsight

				sokTall++;

			}

		}

		return sokTab;
	}

	@Override
	public Film[] sokProdusent(String delstreng) {
		Film[] sokTab = new Film[antall];
		int sokTall = 0;

		for (Film obj : filmtabell) {

			if (obj.getProdusent().contains(delstreng)) {
				sokTab[sokTall] = obj;
				utvidKapasitet();
				sokTall++;

			}

		}

		return sokTab;
	}

	@Override
	public int antallSjanger(Sjanger sjanger) {
		int sjangAnt = 0;

		for (Film obj : filmtabell) {
			if (obj.getSjanger() == sjanger) {

				sjangAnt++;
			}
		}

		return sjangAnt;

	}

	@Override
	public int antall() {
		// TODO Auto-generated method stub
		return antall;
	}

}
