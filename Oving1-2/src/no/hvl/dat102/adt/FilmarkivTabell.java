package no.hvl.dat102.adt;

import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;

public class FilmarkivTabell implements FilmarkivADT {

	private Film[] filmtabell;
	private int tabellAntall;
	private int filmAntall;

	public FilmarkivTabell(int antall) {
		this.tabellAntall = antall;
		this.filmtabell = new Film[antall];
		this.filmAntall = 0;
	}

	@Override
	public Film[] hentFilmTabell() {
		return filmtabell;
	}

	private void utvidKapasitet() {
		// eks. utvide 10

		Film[] hjelpetabell = new Film[(int) Math.ceil(1.5 * filmtabell.length + 1)]; // + 1 i tilfelde for tabell
																						// størrelse = 0. gange med null
																						// er tull

		for (int i = 0; i < filmtabell.length; i++) {
			hjelpetabell[i] = filmtabell[i];
		}

		filmtabell = hjelpetabell;
		tabellAntall = filmtabell.length;
	}

	@Override
	public void leggTilFilm(Film nyFilm) {
		boolean ikkePlass = true;

		// F�rst skjekk om tabellen er tom
		if (filmtabell.length == 0) {
			Film[] tempTab = new Film[1];

			filmtabell = tempTab;
			filmtabell[0] = nyFilm;
			tabellAntall = 1;
			filmAntall++;
			ikkePlass = false;
		} else {

			// finne ledig plass
			for (int i = 0; i < filmtabell.length; i++) {
				if (filmtabell[i] == null) {
					filmtabell[i] = nyFilm;
					filmAntall++;
					ikkePlass = false;
					break;
				}
			}
		}

		// utvidtabell om ikke plass og prøv igjen
		if (ikkePlass) {

			utvidKapasitet();
			leggTilFilm(nyFilm);

		}

	}

	// først håndter special case om tabbel har 1 i størrelse
	// dette vil ikke bli noe problem med for løkke iterering,
	// men på linjen under lager vi ny tabell med størrekse -1
	// Kan ikke ha tabell med størrelse -1

	@Override
	public boolean slettFilm(int filmNr) {

		boolean hasRemoved = false;

		int fjernet = 0;
		// If array is empty there are no films to remove.
		if (filmAntall == 0) {
			return false;
		}

		// Om lengden er 1 må gjøre et special case ellers
		// peker objectets peker til seg selv igjen etter sletting.
		if (filmtabell.length == 1) {
			if (filmNr == filmtabell[0].getFilmID() && filmtabell[0] != null) {

				filmtabell[0] = null;
				hasRemoved = true;
				filmAntall--;
				return true;
			}

		}

		for (int i = 0; i < filmtabell.length - fjernet; i++) {

			// Check if filmIDs match and that film != null
			if (filmtabell[i] != null && filmNr == filmtabell[i].getFilmID()) {

				// If a match is made. we change current reference to point to last object
				// object can be null without critical failure.
				hasRemoved = true;

				// ved siste plass minus de vi har fjernet.
				// elementene i slutten av tabbelen er no midlertidig duplikater
				if (i == tabellAntall - 1 - fjernet) {
					filmtabell[i] = null;
					fjernet++;
					filmAntall--;
					break;
				}
				// per iteration ønsker vi ikke å lage duplikater.
				// derfor for hvert funn(fjernet) peker vi en mindre av tabell størrelse
				// null objecter er ikke problem og blir hoppet over av logikken i if() over
				filmtabell[i] = filmtabell[tabellAntall - 1 - fjernet];
				fjernet++;
				filmAntall--;
				i--;

			}

		}
		// trimming og sortering( kun fjerner nullpunkter i mellom filmer) og fjerning
		// av duplikat
		if (hasRemoved) {

			Film[] kopi = new Film[filmtabell.length - fjernet];
			for (int k = 0; k < kopi.length; k++) {
				if (filmtabell[k] != null) {
					kopi[k] = filmtabell[k];
				}

			}
			tabellAntall = tabellAntall - fjernet;
			filmtabell = kopi;
			return true;
		}

		// kopier over gammel tabell til nye tabell som er 1 mindre

		return hasRemoved;
	}

	@Override
	public Film[] sokTittel(String delstreng) {

		// Oppretter tabell hvor vi legger funn
		// lengde lik størrelse
		Film[] sokTab = new Film[tabellAntall];
		int sokTall = 0;

		// itererer igjennom filmarkivet og s�ker p� delstreng

		for (int i = 0; i < sokTab.length; i++) {

			// Filter for null object
			if (filmtabell[i] != null) {

				// Tar begge strings til lower case, for bredere søkeresultat og matcher med
				// String metoden contains()
				if (filmtabell[i].getTittel().toLowerCase().contains(delstreng.toLowerCase())) {

					// legg til fun i søktabell
					sokTab[sokTall] = filmtabell[i];
					// sokTall må adderes slik at neste potensielle funn blir lagt 1 opp.
					sokTall++;

				}

			}

		}

		return sokTab;
	}

	@Override
	public Film[] sokProdusent(String delstreng) {

		// Oppretter tabell hvor vi legger funn
		// lengde lik størrelse
		Film[] sokTab = new Film[tabellAntall];
		int sokTall = 0;

		// itererer igjennom filmarkivet og s�ker p� delstreng

		for (int i = 0; i < sokTab.length; i++) {

			// Filter for null object
			if (filmtabell[i] != null) {

				// Tar begge strings til lower case, for bredere søkeresultat og matcher med
				// String metoden contains()
				if (filmtabell[i].getProdusent().toLowerCase().contains(delstreng.toLowerCase())) {

					// legg til fun i søktabell
					sokTab[sokTall] = filmtabell[i];
					// sokTall må adderes slik at neste potensielle funn blir lagt 1 opp.
					sokTall++;

				}

			}

		}

		return sokTab;
	}

	@Override
	public int antallSjanger(Sjanger sjanger) {
		int sjangAnt = 0;

		for (Film obj : filmtabell) {
			if (obj != null && obj.getSjanger() == sjanger) {

				sjangAnt++;
			}
		}

		return sjangAnt;

	}

	@Override
	public int antall() {
		return filmAntall;
	}

	@Override
	public String toString() {
		return "Antall filmer:" + antall() + " \nFilmarkiv med tabell struktur.";

	}

}
