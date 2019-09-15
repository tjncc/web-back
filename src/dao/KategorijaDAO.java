package dao;

import java.util.Collections;
import java.util.HashMap;

import beans.Kategorija;


public class KategorijaDAO {
	
	private HashMap<String, Kategorija> kategorije = new HashMap<>();
	
	public KategorijaDAO() {
		//Kategorija jakne = new Kategorija("Jakne");
		Kategorija patike = new Kategorija("Patike");
		Kategorija haljine = new Kategorija("Haljine");
		Kategorija knjige = new Kategorija("Knjige");
		Kategorija telefoni = new Kategorija("Telefoni");
		Kategorija lampe = new Kategorija("Lampe");
		
		kategorije.put("Jakne", new Kategorija("Jakne"));
		kategorije.put(patike.getNaziv(), patike);
		kategorije.put(haljine.getNaziv(), haljine);
		kategorije.put(knjige.getNaziv(), knjige);
		kategorije.put(telefoni.getNaziv(), telefoni);
		kategorije.put(lampe.getNaziv(), lampe);
		
		
	}

	public HashMap<String, Kategorija> getKategorije() {
		return kategorije;
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

	@Override
	public String toString() {
		String retVal = "";
		for(Kategorija kat: kategorije.values()) {
			retVal = kat.getNaziv() + "\n";
		}
		
		return retVal;
	}
	
	
	

}
