package no.hvl.dat102.gui;

import java.util.Scanner;

import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;
import no.hvl.dat102.adt.FilmarkivADT;

/**
 * Klasse som jobber med Meny object og FilmarkivADT og sender represasjon av
 * film arkiv til Meny klassen som en string.
 * 
 * 
 * @author Eskil Oscar Ehrensvärd, Per Otto Sande Furre
 *
 */

public class Tekstgrensesnitt {
	Scanner input = new Scanner(System.in);

	/**
	 * Denne metoden er en legacy metode som ikke brukes av gui eller andre klasser.
	 * Men ble brukt i tidlig del av utvikling når terminal var hovedinterface med
	 * koden. Grei å ha om man ønsker å legge til filmer uten gui
	 * 
	 * @return Returnerer et Film object som en bruker har skrevet inn via consoll
	 *         vha Scanner object.
	 */
	public Film lesFilm() {

		System.out.println("Skriv inn filmens tittel:");
		String tittel = input.nextLine();

		System.out.println("Skriv inn filmens selskap:");
		String selskap = input.nextLine();

		System.out.println("Skriv inn filems produsent:");
		String produsent = input.nextLine();

		System.out.println("Hvilken sjanger har filmen:");
		String sj = input.nextLine();

		System.out.println("Skriv inn unik filmID :");
		int id = input.nextInt();

		System.out.println("Skriv inn år filmen kom ut:");
		int aar = input.nextInt();

		return new Film(id, produsent, tittel, aar, Sjanger.finnSjanger(sj), selskap);

	}

	/**
	 * Enkel tekst string metode Skriver ut: Film: År: Filmselskap: Produsent:
	 * Sjanger: ID:
	 * 
	 * @return String med formaterte kategorier.
	 */
	public String printFilmCategory() {

		return String.format("%-45s  %-4s   %-35s   %-25s   %-15s   %-4s%n", "Film:", "År:", "Filmselskap:",
				"Produsent:", "Sjanger:", "ID:\n");

	}

	/**
	 * Metode som tar inn en tabell med filmobject. Start og slutt posision Kan
	 * skrive ut fra pos x til y. Robusthet for null objecter. Disse blir ignorert.
	 * Tar inn et enumobject to ganger som begge blir gjort til string. Første
	 * enumstring blir gjort til string, strippet for all bokstaver uten om første
	 * som så blir kapatalisert. Andre enumstring fjernes første bokstav også gjøres
	 * resten til små bokstaver. disse blir så satt sammen. Feks: DRAMA + DRAMA ->
	 * D____ + _RAMA -> D + rama -> Drama
	 * 
	 * Grunn for start og end posisjon er pga mulighet for gjenbruk av gui og
	 * utvideles. Blir ikke brukt i programes master branch i githuben.
	 * 
	 * @param filmene tabell med film object
	 * @param start
	 * @param end
	 * @return String En stor string med alle filmer retuners og printes ut i
	 *         tekstvindu av Meny
	 */
	public String visFilmer(Film[] filmene, int start, int end) {
		String str = "";

		for (int i = start; i < end; i++) {
			if (filmene[i] != null) {
				str = str + String.format("%-45s  %4d   %-35s   %-25s   %-15s   %4d%n", filmene[i].getTittel(),
						filmene[i].getAar(), filmene[i].getFilmselskap(), filmene[i].getProdusent(),
						filmene[i].getSjanger().toString().substring(0, 1).toUpperCase()
								+ filmene[i].getSjanger().toString().toLowerCase().substring(1),
						filmene[i].getFilmID());

			}
		}

		return str;

	}

	/**
	 * Skrive ut alle Filmermed en spesiell delstreng i tittelen. Får sokeresultat
	 * fra FilmarkivADT i tabell. bruker så visFilmer() til å returnere string som s
	 * så returneres til meny for utskrift
	 * 
	 * @param filma     FilmarkivADT Filmarkivet vi jobber med
	 * @param delstreng String som skal søkes på
	 */
	public String skrivUtFilmDelstrengITittel(FilmarkivADT filma, String delstreng) {
		Film[] utTittel = filma.sokTittel(delstreng);
		return visFilmer(utTittel, 0, utTittel.length);
	}

	/**
	 * Skrive ut alle Filmermed en spesiell delstreng i produsent. Får sokeresultat
	 * fra FilmarkivADT i tabell. bruker så visFilmer() til å returnere string som s
	 * så returneres til meny for utskrift
	 * 
	 * @param filma     FilmarkivADT Filmarkivet vi jobber med
	 * @param delstreng String som skal søkes på
	 */
	public String skrivUtFilmProdusent(FilmarkivADT filma, String delstreng) {
		Film[] utProd = filma.sokProdusent(delstreng);
		return visFilmer(utProd, 0, utProd.length);

	}

	// notasjon O er=1+ 4n

	//
	// Skrive ut en enkel statistikk som inneholder antall Filmertotalt
	// og hvor mange det er i hver sjanger
	/**
	 * Bruker metoder for å hente riktig antall filmer. og sjanger. antall() og
	 * antallSjanger()
	 * 
	 * @param filma FilmarkivADT Filmarkivet vi jobber med
	 * @return String med statstik over filmarkiv objectet
	 */
	public String skrivUtStatistikk(FilmarkivADT filma) {
		String str = "Filmer  : " + filma.antall() + "\n";
		str = str + "Action  : " + filma.antallSjanger(Sjanger.ACTION) + "\n";
		str = str + "Drama   : " + filma.antallSjanger(Sjanger.DRAMA) + "\n";
		str = str + "Historie: " + filma.antallSjanger(Sjanger.HISTORY) + "\n";
		str = str + "Scfi    : " + filma.antallSjanger(Sjanger.SCFI) + "\n";
		str = str + "Ingen   : " + filma.antallSjanger(Sjanger.NONE) + "\n";

		return str;

	}

}
