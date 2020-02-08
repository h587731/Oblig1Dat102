package no.hvl.dat102;


public class Film {

	private int filmID;
	private String produsent;
	private String tittel;
	private int aar;
	private Sjanger sjanger;
	private String filmselskap;
	
	
	
	
	public Film () {
		filmID = 0;
		produsent = null;
		tittel  = null;
		aar  = 0;
		sjanger = null;
		filmselskap = null;
	}
	
	public Film (int filmID, String produsent, String tittel, int aar, Sjanger sjanger, String filmselskap ) {
		this.setFilmID(filmID);
		this.setProdusent(produsent);
		this.setTittel(tittel);
		this.setAar(aar);
		this.setSjanger(sjanger);
		this.setFilmselskap(filmselskap);
		
		
		
	}

	
	
	public int getFilmID() {
		return filmID;
	}

	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}

	public String getProdusent() {
		return produsent;
	}

	public void setProdusent(String produsent) {
		this.produsent = produsent;
	}

	public String getTittel() {
		return tittel;
	}

	public void setTittel(String tittel) {
		this.tittel = tittel;
	}

	public int getAar() {
		return aar;
	}

	public void setAar(int aar) {
		this.aar = aar;
	}

	public Sjanger getSjanger() {
		return sjanger;
	}

	public void setSjanger(Sjanger sjanger) {
		this.sjanger = sjanger;
	}

	public String getFilmselskap() {
		return filmselskap;
	}

	public void setFilmselskap(String filmselskap) {
		this.filmselskap = filmselskap;
	}
	
}
