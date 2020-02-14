package no.hvl.dat102.adt;

import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;

/**
 * Interface for arkiv som håndterer objekter av typen Film. Har vanlig legg til
 * og slett metoder for arkiv struktur. Samt søk og hent tabell metoder.
 * 
 * @author Eskil Oscar Ehrensvärd, Per Otto Sande Furre
 *
 *
 */
public interface FilmarkivADT {

	/**
	 * Metode som legger til filmobjekt i arkivet
	 * 
	 * @param nyFilm Film objekt som skal settes inn.
	 * 
	 */
	void leggTilFilm(Film nyFilm);

	/**
	 * Metode som finner node med match av filmID og fjerner fra listen Søker på
	 * match med filmNR i filmarkinv
	 * 
	 * @param filmNr Int som skal slettes om den finnes
	 * 
	 * @return true eller false. om slettet eller ikke.
	 */
	boolean slettFilm(int filmNr);

	// Søker og henter Filmermed en gitt delstreng

	/**
	 * Metode som returnerer film tabell med søkeresultat fra delstreng Søker på
	 * match med delstreng i filmarkinv
	 * 
	 * @param delstreng String som skal søkes på
	 * @return Tabell av match resultat
	 */
	Film[] sokTittel(String delstreng);

	/**
	 * Metode som returnerer filmtabell med søkeresultat fra delstreng. Søker på
	 * match med delstreng i filmarkinv
	 * 
	 * @param delstreng String som skal søkes på
	 * @return Tabell av match resultat
	 */
	Film[] sokProdusent(String delstreng);

	/**
	 * Metode som returnerer antall sjangre i en filmarkiv.
	 *
	 * @param sjanger Enum objekt som skal telles for
	 * @return Int. Antall forekomster
	 */
	int antallSjanger(Sjanger sjanger);

	/**
	 * @return Int antal film objekt i Arkiv
	 */
	int antall();

	/**
	 * Metode som returnerer filmtabell fra filmarkiv.
	 * 
	 * @return
	 */
	Film[] hentFilmTabell();

}
