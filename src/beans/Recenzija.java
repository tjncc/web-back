package beans;

import java.util.UUID;

public class Recenzija {
	
	UUID idOneRec = UUID.randomUUID();
	
	private String oglas;
	private String recezent;
	private String naziv;
	private String sadrzaj;
	private String slika;
	private boolean tacanOglas;
	private boolean ispostovan;
	
	private String prodavac;
	private boolean aktivna;
	private String idRec = UUID.randomUUID().toString();
	
	public Recenzija(String oglas, String naziv, String sadrzaj, String slika, boolean tacanOglas,
			boolean ispostovan, String prodavac) {
		super();
		this.idRec = UUID.randomUUID().toString();
		this.oglas = oglas;
		this.naziv = naziv;
		this.sadrzaj = sadrzaj;
		this.slika = slika;
		this.tacanOglas = tacanOglas;
		this.ispostovan = ispostovan;
		this.aktivna = true;
		this.prodavac = prodavac;
	}
	
	public Recenzija(String oglas, String naziv, String sadrzaj, boolean tacanOglas,
			boolean ispostovan, String prodavac) {
		super();
		this.idRec = UUID.randomUUID().toString();
		this.oglas = oglas;
		this.naziv = naziv;
		this.sadrzaj = sadrzaj;
		this.tacanOglas = tacanOglas;
		this.ispostovan = ispostovan;
		this.prodavac = prodavac;
		this.aktivna = true;
	}
	
	public Recenzija(String oglas, String naziv, String sadrzaj, String prodavac) {
		super();
		this.idRec = UUID.randomUUID().toString();
		this.oglas = oglas;
		this.naziv = naziv;
		this.sadrzaj = sadrzaj;
		this.prodavac = prodavac;
		this.tacanOglas = true;
		this.ispostovan = true;
		this.aktivna = true;
	}
	
	
	public Recenzija() {
		super();
		this.idRec = UUID.randomUUID().toString();
		this.aktivna = true;
	}
	

	public String getOglas() {
		return oglas;
	}

	public void setOglas(String oglas) {
		this.oglas = oglas;
	}

	public String getRecezent() {
		return recezent;
	}

	public void setRecezent(String recezent) {
		this.recezent = recezent;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public boolean isTacanOglas() {
		return tacanOglas;
	}

	public void setTacanOglas(boolean tacanOglas) {
		this.tacanOglas = tacanOglas;
	}

	public boolean isIspostovan() {
		return ispostovan;
	}

	public void setIspostovan(boolean ispostovan) {
		this.ispostovan = ispostovan;
	}

	public boolean isAktivna() {
		return aktivna;
	}

	public void setAktivna(boolean aktivna) {
		this.aktivna = aktivna;
	}

	public String getIdRec() {
		return idRec;
	}

	public void setIdRec(String idRec) {
		this.idRec = idRec;
	}

	public String getProdavac() {
		return prodavac;
	}

	public void setProdavac(String prodavac) {
		this.prodavac = prodavac;
	}
	
	
	
	
	
	
	

}
