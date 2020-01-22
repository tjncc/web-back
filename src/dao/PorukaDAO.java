package dao;

import java.util.ArrayList;
import java.util.HashMap;

import beans.Poruka;

public class PorukaDAO {
	
	private HashMap<String, Poruka> poruke = new HashMap<>();
	
	
	public PorukaDAO() {
		
	}


	public HashMap<String, Poruka> getPoruke() {
		return poruke;
	}


	public void setPoruke(HashMap<String, Poruka> poruke) {
		this.poruke = poruke;
	}
	
	public ArrayList<Poruka> porukePrikaz(String ime){
		ArrayList<Poruka> prikazPoruka = new ArrayList<Poruka>();
		
		for(Poruka poruka: poruke.values()) {
			if(poruka.getPrimalac().equals(ime))
				if(poruka.getAktivna() == true) {
					prikazPoruka.add(poruka);
				}
		}
		return prikazPoruka;
	}
	
	public Poruka findID(String id) {
		for(Poruka poruka: poruke.values()) {
			if(poruka.getIdPoruka().equals(id)) {
				if(poruka.getAktivna() == true) {
					return poruka;
				}
			}			
		}
		return null;
	}
	
	
}
