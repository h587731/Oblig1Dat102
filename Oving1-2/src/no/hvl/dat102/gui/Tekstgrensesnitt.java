package no.hvl.dat102.gui;

import java.util.Scanner;

import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;
import no.hvl.dat102.adt.FilmArchiveADT;

public class Tekstgrensesnitt {
	Scanner input = new Scanner(System.in);

	// lese opplysningene om en FILMfra tastatur
	public Film lesFilm() {

		// skrive inn ny film
		//

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

	// vise en film med alle opplysninger p� skjerm (husk tekst for sjanger)

	public String printFilmCategory() {

		return String.format("%-45s  %-4s   %-35s   %-25s   %-15s   %-4s%n", "Film:", "År:", "Filmselskap:",
				"Produsent:", "Sjanger:", "ID:");

	}

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

//		for (Film film : filmene) {
//			if (film == null)
//				continue;
//			str = str + String.format("%-45s  %4d   %-35s   %-25s   %-15s   %4d%n", film.getTittel(), film.getAar(),
//					film.getFilmselskap(), film.getProdusent(),
//					film.getSjanger().toString().substring(0, 1).toUpperCase()
//							+ film.getSjanger().toString().toLowerCase().substring(1),
//					film.getFilmID());
//
//		}

		return str;

	}

	// Skrive ut alle Filmermed en spesiell delstreng i tittelen
	// TODO
	public void skrivUtFilmDelstrengITittel(FilmArchiveADT filma, String delstreng) {
		Film[] utTittel = filma.searchTitle(delstreng);
		visFilmer(utTittel, 0, utTittel.length);
	}

// TODO
	// Skriver ut alle Filmerav en produsent/ en gruppe
	public void skrivUtFilmProdusent(FilmArchiveADT filma, String delstreng) {
		Film[] utProd = filma.searchProducer(delstreng);
		visFilmer(utProd, 0, utProd.length);

	}
	// notasjon O er=1+ 4n

	// 1 + 4n = O(n)
	//
	// Skrive ut en enkel statistikk som inneholder antall Filmertotalt
	// og hvor mange det er i hver sjanger
	public String skrivUtStatistikk(FilmArchiveADT filma) {
		String str = "Filmer  : " + filma.size() + "\n";
		str = str + "Action  : " + filma.genreAmount(Sjanger.ACTION) + "\n";
		str = str + "Drama   : " + filma.genreAmount(Sjanger.DRAMA) + "\n";
		str = str + "Historie: " + filma.genreAmount(Sjanger.HISTORY) + "\n";
		str = str + "Scfi    : " + filma.genreAmount(Sjanger.SCFI) + "\n";

		return str;

	}

	// ... Ev. andre metoder}
	// class

}
