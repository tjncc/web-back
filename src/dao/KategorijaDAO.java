package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import beans.Kategorija;
import beans.Oglas;


public class KategorijaDAO {
	
	private HashMap<String, Kategorija> kategorije = new HashMap<>();
	
	public KategorijaDAO() {
		//Kategorija jakne = new Kategorija("Jakne");
		Kategorija patike = new Kategorija("Patike");
		Kategorija haljine = new Kategorija("Haljine");
		Kategorija knjige = new Kategorija("Knjige");
		Kategorija telefoni = new Kategorija("Telefoni");
		Kategorija lampe = new Kategorija("Lampe");
		Kategorija lekovi = new Kategorija("Lekovi");
		Kategorija laptopovi = new Kategorija("Laptopovi");
		Kategorija ogledala = new Kategorija("Ogledala");
		Kategorija prozori = new Kategorija("Prozori");
		Kategorija stolice = new Kategorija("Stolice");
		Kategorija svece = new Kategorija("Svece");
		Kategorija parfemi = new Kategorija("Parfemi");
		Kategorija satovi = new Kategorija("Satovi");
		
		kategorije.put("Jakne", new Kategorija("Jakne"));
		kategorije.put(patike.getNaziv(), patike);
		kategorije.put(haljine.getNaziv(), haljine);
		kategorije.put(knjige.getNaziv(), knjige);
		kategorije.put(telefoni.getNaziv(), telefoni);
		kategorije.put(lampe.getNaziv(), lampe);
		kategorije.put(lekovi.getNaziv(), lekovi);
		kategorije.put(laptopovi.getNaziv(), laptopovi);
		kategorije.put(ogledala.getNaziv(), ogledala);
		kategorije.put(prozori.getNaziv(), prozori);
		kategorije.put(stolice.getNaziv(), stolice);
		kategorije.put(svece.getNaziv(), svece);
		kategorije.put(parfemi.getNaziv(), parfemi);
		kategorije.put(satovi.getNaziv(), satovi);
		
		
	}

	public HashMap<String, Kategorija> getKategorije() {
		return kategorije;
	}
	
	public ArrayList<Kategorija> kategorijePrikaz(){
		ArrayList<Kategorija> aktuelne = new ArrayList<Kategorija>();
		
		for(Kategorija kat: kategorije.values()) {
			if(kat.aktivna == true) {
				aktuelne.add(kat);
			}
		}
		return aktuelne;
	}

	public void setKategorije(HashMap<String, Kategorija> kategorije) {
		this.kategorije = kategorije;
	}
	
	public Kategorija find(Kategorija k) {
		for(Kategorija kat: kategorije.values()) {
			if(k.getNaziv().equals(kat.getNaziv())) {
				return kat;
			}
		}
		return null;
	}
	
	public Kategorija findByName(String naziv) {
		for(Kategorija kat: kategorije.values()) {
			if(naziv.equals(kat.getNaziv())) {
				return kat;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		String retVal = "";
		for(Kategorija kat: kategorije.values()) {
			retVal = kat.getNaziv() + "\n";
		}
		
		return retVal;
	}
	
	
	

}
