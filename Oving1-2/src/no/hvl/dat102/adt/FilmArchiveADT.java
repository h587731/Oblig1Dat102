package no.hvl.dat102.adt;

import no.hvl.dat102.Film;
import no.hvl.dat102.Sjanger;

public interface FilmArchiveADT {
	
	// Bruk gjerne javadoc her i
	// stedet for vanlige 
	// kommentarlinjer som her  
	
	
	
	// Legger til en ny Film
	void addMovie(Film nyFilm);
	
	// Sletter en Filmhvis den fins 
	boolean removeFilm(int filmNr);
	
	// Søker og henter Filmermed en gitt delstreng
	
	Film[] searchTitle(String delstreng);
	
	// Søker og henter produsenter med en gitt delstreng
	
	Film[] searchProducer(String delstreng);
	
	// Henter antall Filmerfor en gitt sjanger
	int genreAmount(Sjanger sjanger);

	// Returnerer antall Filmer
	int size();
	
	// Returnere en tabell av Filmer
	Film[] retrieveFilmArray();
	
}

