package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import beans.Oglas;
import beans.Recenzija;

public class RecenzijaDAO {
	
	private HashMap<UUID, Recenzija> recenzije = new HashMap<>();
	
	public RecenzijaDAO() {
		
	}

	public HashMap<UUID, Recenzija> getRecenzije() {
		return recenzije;
	}

	public void setRecenzije(HashMap<UUID, Recenzija> recenzije) {
		this.recenzije = recenzije;
	}
	
	public Recenzija findID(UUID idOneRec) {
		for(Recenzija rec: recenzije.values()) {
			if(idOneRec.equals(rec.getIdOneRec())) {
				return rec;
			}
			
		}
		return null;
	}
	
	
	public ArrayList<Recenzija> recenzijePrikazOglas(String oglasNaziv){
		ArrayList<Recenzija> sveRec = new ArrayList<Recenzija>();
		
		for(Recenzija recenzija: recenzije.values()) {
			if(recenzija.getOglas().equals(oglasNaziv)) {
				sveRec.add(recenzija);
			}
		}
		
		return sveRec;
	}
	
	

}
