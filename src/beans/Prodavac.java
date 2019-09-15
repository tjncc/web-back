package beans;

import java.util.ArrayList;

public class Prodavac {

	private String naziv;
	private ArrayList<Oglas> objavljeniOglasi = new ArrayList<Oglas>();
	private ArrayList<Oglas> isporuceniProizvodi = new ArrayList<Oglas>();
	private ArrayList<Oglas> listaPoruka = new ArrayList<Oglas>();
	private int brLajkova;
	private int brDislajkova;
	
	public Prodavac() {
		this.objavljeniOglasi = new ArrayList<Oglas>();
		this.isporuceniProizvodi = new ArrayList<Oglas>();
		this.listaPoruka = new ArrayList<Oglas>();
		this.brLajkova = 0;
		this.brDislajkova = 0;
		this.naziv = "Prodavac";
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public ArrayList<Oglas> getObjavljeniOglasi() {
		return objavljeniOglasi;
	}

	public void setObjavljeniOglasi(ArrayList<Oglas> objavljeniOglasi) {
		this.objavljeniOglasi = objavljeniOglasi;
	}

	public ArrayList<Oglas> getIsporuceniProizvodi() {
		return isporuceniProizvodi;
	}

	public void setIsporuceniProizvodi(ArrayList<Oglas> isporuceniProizvodi) {
		this.isporuceniProizvodi = isporuceniProizvodi;
	}

	public ArrayList<Oglas> getListaPoruka() {
		return listaPoruka;
	}

	public void setListaPoruka(ArrayList<Oglas> listaPoruka) {
		this.listaPoruka = listaPoruka;
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

	@Override
	public String toString() {
		return "Prodavac";
	}
	
	
}
