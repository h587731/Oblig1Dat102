package no.hvl.dat102.klient;

import no.hvl.dat102.adt.FilmArchiveADT;
import no.hvl.dat102.adt.FilmArchiveLL;
import no.hvl.dat102.gui.Meny;

public class KlientFilmarkiv {

	public static void main(String[] args) {

		FilmArchiveADT filma = sendNewArchive(0);

		Meny meny = new Meny(filma);

		meny.start();

	}

	public static FilmArchiveADT sendNewArchive(int size) {

		return new FilmArchiveLL(size);

	}

}
