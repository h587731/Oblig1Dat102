package no.hvl.dat102.klient;

import no.hvl.dat102.adt.FilmarkivADT;
import no.hvl.dat102.adt.FilmarkivLinketListe;
import no.hvl.dat102.adt.FilmarkivTabell;
import no.hvl.dat102.gui.Meny;

/**
 * Denne klassen er hvor vi har main meny. Vi oppretter her et meny objekt som
 * forventer et FilmarkivADT type.
 * 
 * Dette er _eneste_ stede i koden hvor klassene {@link FilmarkivTabell} og
 * {@link FilmarkivLinketListe} skal kjøres fra.
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
	 * Dette er _Eneste_ sted i programmet hvor de to klassene FilmarkivTabell og
	 * FilmarkivLinketListe blir referert til
	 * 
	 * Dette betyr at mulig het for å bytte up med en enda ny struktur krever kun å
	 * bytte koden her og ikke i noen andre klasser i programmet.
	 * 
	 * 
	 * @param antall Størrelse på filmarkiv
	 * @return filmarkiv med riktig struktur
	 * 
	 */

	public static FilmarkivADT sendNytt(int antall, boolean valg) {

		if (valg)
			return new FilmarkivLinketListe(antall);
		return new FilmarkivTabell(antall);

	}

}
