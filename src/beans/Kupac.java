package beans;

import java.util.ArrayList;

public class Kupac {
	
	//private ArrayList<User> user = new ArrayList<User>();
	public String naziv;
	public ArrayList<Oglas> poruceniProizvodi = new ArrayList<Oglas>();
	public ArrayList<Oglas> dostavljeniProizvodi = new ArrayList<Oglas>();
	public ArrayList<Oglas> omiljeniOglasi = new ArrayList<Oglas>();
	
	public Kupac(){
		this.poruceniProizvodi = new ArrayList<Oglas>();
		this.dostavljeniProizvodi = new ArrayList<Oglas>();
		this.omiljeniOglasi = new ArrayList<Oglas>();
		this.naziv = "Kupac";
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public ArrayList<Oglas> getPoruceniProizvodi() {
		return poruceniProizvodi;
	}

	public void setPoruceniProizvodi(ArrayList<Oglas> poruceniProizvodi) {
		this.poruceniProizvodi = poruceniProizvodi;
	}

	public ArrayList<Oglas> getDostavljeniProizvodi() {
		return dostavljeniProizvodi;
	}

	public void setDostavljeniProizvodi(ArrayList<Oglas> dostavljeniProizvodi) {
		this.dostavljeniProizvodi = dostavljeniProizvodi;
	}

	public ArrayList<Oglas> getOmiljeniOglasi() {
		return omiljeniOglasi;
	}

	public void setOmiljeniOglasi(ArrayList<Oglas> omiljeniOglasi) {
		this.omiljeniOglasi = omiljeniOglasi;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Kupac";
	}
	
	

}
