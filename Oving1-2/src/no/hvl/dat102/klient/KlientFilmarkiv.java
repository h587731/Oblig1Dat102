package no.hvl.dat102.klient;

import no.hvl.dat102.adt.Filmarkiv;
import no.hvl.dat102.adt.Filmarkiv2;
import no.hvl.dat102.adt.FilmarkivADT;
import no.hvl.dat102.gui.Meny;

/**
 * Denne klassen er hvor vi har main meny. Vi oppretter her et meny objekt som
 * forventer et FilmarkivADT type.
 * 
 * Dette er _eneste_ stede i koden hvor klassene {@link Filmarkiv} og
 * {@link Filmarkiv2} skal kjøres fra.
 * 
 * _Alle_ andre steder bruker FilmarkivADT. Om de trenger et nytt film arkiv har
 * vi derfor laget en metode kallt sendNytt som returner
 * 
 * @author Eskil, Per Otto
 *
 */

public class KlientFilmarkiv {

	public static void main(String[] args) {

		FilmarkivADT filma = sendNytt(0, false);

		Meny meny = new Meny(filma);

		meny.start();

		// meny.start();

	}

	/**
	 * @param antall Størrelse på filmarkiv
	 * @return filmarkiv med riktig struktur
	 * 
	 */

	public static FilmarkivADT sendNytt(int antall, boolean valg) {

		if (valg)
			return new Filmarkiv2(antall);
		return new Filmarkiv(antall);

	}

}
