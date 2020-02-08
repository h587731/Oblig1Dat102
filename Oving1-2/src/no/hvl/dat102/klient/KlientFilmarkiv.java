package no.hvl.dat102.klient;

import no.hvl.dat102.adt.Filmarkiv;
import no.hvl.dat102.adt.Filmarkiv2;
import no.hvl.dat102.adt.FilmarkivADT;
import no.hvl.dat102.gui.Meny;
import no.hvl.dat102.gui.Tekstgrensesnitt;

public class KlientFilmarkiv {
	
	
	public static void main(String[] args) {
		
		
		
		FilmarkivADT filma = sendNytt(0);


		Meny meny = new Meny(filma);

	
		meny.start();
		
		
		
	}

	public static FilmarkivADT sendNytt(int size) {

		FilmarkivADT nyFilma = new Filmarkiv2(size);
		return nyFilma;

	}
	
	
	

}
