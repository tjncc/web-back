package beans;

import java.util.ArrayList;
import java.util.UUID;

public class Kategorija {
	
	UUID idOne = UUID.randomUUID();
	private String naziv;
	private String opis;
	private ArrayList<Oglas> oglasi = new ArrayList<Oglas>();
	public boolean aktivna;
	
	

	public Kategorija(String naziv, String opis, ArrayList<Oglas> oglasi) {

		this.naziv = naziv;
		this.opis = opis;
		this.oglasi = oglasi;
	}
	
	public Kategorija(String naziv) {

		this.naziv = naziv;
	}
	
	public Kategorija() {
		
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public ArrayList<Oglas> getOglasi() {
		return oglasi;
	}

	public void setOglasi(ArrayList<Oglas> oglasi) {
		this.oglasi = oglasi;
	}
	
	public boolean isAktivna() {
		return aktivna;
	}

	public void setAktivna(boolean aktivna) {
		this.aktivna = aktivna;
	}


	
	

}
