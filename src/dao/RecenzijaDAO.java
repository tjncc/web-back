package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import beans.Oglas;
import beans.Recenzija;

public class RecenzijaDAO {
	
	private HashMap<String, Recenzija> recenzije = new HashMap<>();
	
	public RecenzijaDAO() {
		
	}

	public HashMap<String, Recenzija> getRecenzije() {
		return recenzije;
	}

	public void setRecenzije(HashMap<String, Recenzija> recenzije) {
		this.recenzije = recenzije;
	}
	
	public Recenzija findID(String idOneRec) {
		System.out.println("Prosledjen idOne: " + idOneRec);
		for(Recenzija rec: recenzije.values()) {
			System.out.println("ovaj iz recenzije id: " + rec.getIdRec());
			if(idOneRec.equals(rec.getIdRec())) {
				
				return rec;
			}
			
		}
		return null;
	}
	
	public ArrayList<Recenzija> recenzijePrikazProdavac(String prodavac){
		ArrayList<Recenzija> sveRec = new ArrayList<Recenzija>();

		for(Recenzija recenzija: recenzije.values()) {
			if(recenzija.getProdavac().equals(prodavac)) {
				if(recenzija.isAktivna() && !recenzija.getOglas().equals("RECENZIJAPRODAVCA")) {
					sveRec.add(recenzija);
				}
			}
		}
		
		return sveRec;
	}
	
	
	public ArrayList<Recenzija> recenzijePrikazProdavacNjegove(String prodavac){
		ArrayList<Recenzija> sveRec = new ArrayList<Recenzija>();

		for(Recenzija recenzija: recenzije.values()) {
			if(recenzija.getProdavac().equals(prodavac)) {
				if(recenzija.isAktivna()  && recenzija.getOglas().equals("RECENZIJAPRODAVCA")) {
					sveRec.add(recenzija);
				}
			}
		}
		
		return sveRec;
	}
	
	
	public ArrayList<Recenzija> recenzijePrikazOglas(String oglasNaziv){
		ArrayList<Recenzija> sveRec = new ArrayList<Recenzija>();
		
		for(Recenzija recenzija: recenzije.values()) {
			if(recenzija.getOglas().equals(oglasNaziv)) {
				if(recenzija.isAktivna()) {
					sveRec.add(recenzija);
				}
			}
		}
		
		return sveRec;
	}
	
	

}
