package no.hvl.dat102.adt;

import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;

public class FilmArchiveLL implements FilmArchiveADT {

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

	public FilmArchiveLL(int antall) {

		this.antall = 0;
		start = null;

	}

	@Override
	public void addMovie(Film nyFilm) {

		if (start == null) {

			start = new LinearNode<Film>(nyFilm);
			antall++;
			return;
		}

//		LinearNode<Film> current = start;
//		LinearNode<Film> newFilm = new LinearNode<Film>(nyFilm);
//
//		while (current.getNeste() != null) {
//			current = current.getNeste();
//		}
//		current.setNeste(newFilm);
		// add legg

		LinearNode<Film> ny = new LinearNode<Film>(nyFilm);
		LinearNode<Film> temp = start;
		start = ny;
		start.setNeste(temp);
		antall++;

	}

	@Override
	public boolean removeFilm(int filmNr) {

		LinearNode<Film> temp = start;
		LinearNode<Film> prev = null;

		if (temp != null && temp.getElement().getFilmID() == filmNr) {
			start = temp.getNeste();
			antall--;
			return true;
		}

		while (temp != null && temp.getElement().getFilmID() != filmNr) {
			prev = temp;
			temp = temp.getNeste();
		}

		if (temp == null)
			return false;

		prev.setNeste(temp.getNeste());
		antall--;
		// prev.neste = temp.neste;
		return true;
	}

	@Override
	public Film[] siftTitle(String delstreng) {

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
	public Film[] siftProducer(String delstreng) {

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {

		return antall;
	}

	@Override
	public Film[] retrieveFilmArray() {

		if (antall == 0) {
			return new Film[0];
		}

		LinearNode<Film> current = start;

		Film tab[] = new Film[antall];
		for (int i = antall - 1; i >= 0; i--) {

			tab[i] = current.getElement();
			current = current.getNeste();

		}

		// TODO Auto-generated method stub
		return tab;
	}

}
