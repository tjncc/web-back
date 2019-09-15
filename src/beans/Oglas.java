package beans;

import java.util.ArrayList;
import java.util.UUID;

public class Oglas implements Comparable<Oglas> {
	
	UUID idOne = UUID.randomUUID();

	private String naziv;
	private double cena;
	private String opis;
	private int brLajkova;
	private int brDislajkova;
	private String slika;
	private String datumPostavljanja;
	private String datumIsticanja;
	private ArrayList<Recenzija> recenzije = new ArrayList<Recenzija>();
	private String grad;
	private Aktivan stanje;
	
	private String prodavac;	
	private int omiljen;
	
	
	
	public static enum Aktivan{
	    AKTUELAN,
		REALIZACIJA,
		DOSTAVLJEN,
		OBRISAN
	}
	
	public Oglas(String naziv, double cena, String opis, int brLajkova, int brDislajkova, String slika,
			String datumPostavljanja, String datumIsticanja, ArrayList<Recenzija> recenzije,
			String grad) {
		super();
		this.naziv = naziv;
		this.cena = cena;
		this.opis = opis;
		this.brLajkova = brLajkova;
		this.brDislajkova = brDislajkova;
		this.slika = slika;
		this.datumPostavljanja = datumPostavljanja;
		this.datumIsticanja = datumIsticanja;
		this.stanje = Aktivan.AKTUELAN;
		this.recenzije = recenzije;
		this.grad = grad;
		this.omiljen = 0;

	}
	
	public Oglas() {
		this.datumPostavljanja = java.time.LocalDate.now().toString();
		this.brLajkova = 0;
		this.brDislajkova = 0;
		this.omiljen = 0;
		this.recenzije = new ArrayList<Recenzija>();
		this.stanje = Aktivan.AKTUELAN;
		
	}
	
	public Oglas(String naziv, double cena, int omiljen, String slika) {
		this.naziv = naziv;
		this.cena = cena;
		this.slika = slika;
		this.stanje = Aktivan.AKTUELAN;
		this.omiljen = omiljen;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getBrLajkova() {
		return brLajkova;
	}

	public void setBrLajkova(int brLajkova) {
		this.brLajkova = brLajkova;
	}

	public int getBrDislajkova() {
		return brDislajkova;
	}

	public void setBrDislajkova(int brDislajkova) {
		this.brDislajkova = brDislajkova;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public String getDatumPostavljanja() {
		return datumPostavljanja;
	}

	public void setDatumPostavljanja(String datumPostavljanja) {
		this.datumPostavljanja = datumPostavljanja;
	}

	public String getDatumIsticanja() {
		return datumIsticanja;
	}

	public void setDatumIsticanja(String datumIsticanja) {
		this.datumIsticanja = datumIsticanja;
	}



	public ArrayList<Recenzija> getRecenzije() {
		return recenzije;
	}

	public void setRecenzije(ArrayList<Recenzija> recenzije) {
		this.recenzije = recenzije;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public Aktivan getStanje() {
		return stanje;
	}

	public void setStanje(Aktivan stanje) {
		this.stanje = stanje;
	}

	public int getOmiljen() {
		return omiljen;
	}

	public void setOmiljen(int omiljen) {
		this.omiljen = omiljen;
	}

	public String getProdavac() {
		return prodavac;
	}

	public void setProdavac(String prodavac) {
		this.prodavac = prodavac;
	}

	@Override
	public int compareTo(Oglas oglas) {
		return (omiljen > oglas.omiljen) ? -1 : (omiljen == oglas.omiljen ? 0 : 1);
	}
	
	
	
	
	
}
