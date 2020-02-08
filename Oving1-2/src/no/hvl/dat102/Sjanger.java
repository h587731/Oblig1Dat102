package no.hvl.dat102;

public enum Sjanger {
	ACTION, DRAMA, HISTORY, SCFI, NONE;
	
/*
 * Konstruktøren er gitt implisitt og er private
	
*/

	public static Sjanger finnSjanger(String navn) {
		Sjanger sjang = NONE;
		for (Sjanger sj : Sjanger.values()) {
			if (sj.toString().equals(navn.toUpperCase())) {
				sjang = sj;
				break;
			}
		}
		return sjang;
	}// metode

}// class
