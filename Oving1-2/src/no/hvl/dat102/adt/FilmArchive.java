package no.hvl.dat102.adt;

import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;

public class FilmArchive implements FilmArchiveADT {

	private Film[] filmArray;
	private int size;

	public FilmArchive(int antall) {
		this.size = antall;
		this.filmArray = new Film[antall];
	}

	@Override
	public Film[] retrieveFilmArray() {
		return filmArray;
	}

	private void utvidKapasitet() {
		// eks. utvide 10

		Film[] hjelpetabell = new Film[(int) Math.ceil(2 * filmArray.length)];

		for (int i = 0; i < filmArray.length; i++) {
			hjelpetabell[i] = filmArray[i];
		}

		size = size + hjelpetabell.length - filmArray.length;
		filmArray = hjelpetabell;

	}

	@Override
	public void addMovie(Film nyFilm) {
		boolean ikkePlass = true;

		// F�rst skjekk om tabellen er tom
		if (filmArray.length == 0) {
			Film[] tempTab = new Film[1];

			filmArray = tempTab;
			filmArray[0] = nyFilm;
			size = 1;
			ikkePlass = false;
		} else {

			// finne ledig plass
			for (int i = 0; i < filmArray.length; i++) {
				if (filmArray[i] == null) {
					filmArray[i] = nyFilm;

					ikkePlass = false;
					break;
				}
			}
		}

		// lage ny tabell om ikke plass
		if (ikkePlass) {

			utvidKapasitet();
			addMovie(nyFilm);

		}

	}

	// We want to assume the archive only has unique filmIDs,
	// but to increase robustness we keep searching and removing
	// matching filmIDs. After removing the reference to the film object
	// we store array position in pos. Then we call removeFilm() which will continue
	// from where we left off.

	private int pos = 0;
	private int removed = 0;
	private boolean hasRemoved = false;
	private boolean isDone = false;

	@Override
	public boolean removeFilm(int filmNr) {

		// If array is empty there are no films to remove.
		if (filmArray.length == 0) {
			return false;
		}

		// set i = pos
		for (int i = pos; i < filmArray.length - removed; i++) {

			// Check if filmIDs match and that film != null
			if (filmNr == filmArray[i].getFilmID() && filmArray[i] != null) {

				// If a match is made. we change current reference to point to last object
				// object can be null without critical failure.
				hasRemoved = true;
				// [5] = size -removed;
				// [5]
				// [6] = size - removed;

				filmArray[i] = filmArray[size - removed]; // per iteration during the recursive calls we make sure not
				removed++; // to check the same object twice
				pos = i;
				removeFilm(filmNr);

				// When done iterating through archive set pos to 0 for futere removals and
				// break out to
				pos = 0;
				break;

			}

		}

		if (hasRemoved) {

			Film[] kopi = new Film[size - removed];
			for (int k = 0; k < kopi.length; k++) {
				if (filmArray[k] != null) {
					kopi[k] = filmArray[k];
					filmArray = kopi;

				}
			}
			return true;
		}

		// kopier over gammel tabell til nye tabell som er 1 mindre

		return hasRemoved;
	}

	@Override
	public Film[] searchTitle(String delstreng) {

		// Oppretter tabell hvor vi legger funn fra s�k
		Film[] sokTab = new Film[size];
		int sokTall = 0;

		// itererer igjennom filmarkivet og s�ker p� delstreng
		for (Film obj : filmArray) {

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
	public Film[] searchProducer(String delstreng) {
		Film[] sokTab = new Film[size];
		int sokTall = 0;

		for (Film obj : filmArray) {

			if (obj.getProdusent().contains(delstreng)) {
				sokTab[sokTall] = obj;
				utvidKapasitet();
				sokTall++;

			}

		}

		return sokTab;
	}

	@Override
	public int genreAmount(Sjanger sjanger) {
		int sjangAnt = 0;

		for (Film obj : filmArray) {
			if (obj.getSjanger() == sjanger) {

				sjangAnt++;
			}
		}

		return sjangAnt;

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
