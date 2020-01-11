package beans;

import java.util.UUID;

public class Recenzija {
	
	UUID idOneRec = UUID.randomUUID();
	
	public String oglas;
	public String recezent;
	public String naziv;
	public String sadrzaj;
	public String slika;
	public boolean tacanOglas;
	public boolean ispostovan;
	
	public Recenzija(String oglas, String naziv, String sadrzaj, String slika, boolean tacanOglas,
			boolean ispostovan) {
		super();
		this.idOneRec = UUID.randomUUID();
		this.oglas = oglas;
		this.naziv = naziv;
		this.sadrzaj = sadrzaj;
		this.slika = slika;
		this.tacanOglas = tacanOglas;
		this.ispostovan = ispostovan;
	}
	
	public Recenzija(String oglas, String naziv, String sadrzaj, boolean tacanOglas,
			boolean ispostovan) {
		super();
		this.idOneRec = UUID.randomUUID();
		this.oglas = oglas;
		this.naziv = naziv;
		this.sadrzaj = sadrzaj;
		this.tacanOglas = tacanOglas;
		this.ispostovan = ispostovan;
	}
	
	public Recenzija(String oglas, String naziv, String sadrzaj) {
		super();
		this.idOneRec = UUID.randomUUID();
		this.oglas = oglas;
		this.naziv = naziv;
		this.sadrzaj = sadrzaj;
		this.tacanOglas = true;
		this.ispostovan = true;
	}
	
	
	public Recenzija() {
		super();
		UUID.randomUUID();
	}
	
	

	public UUID getIdOneRec() {
		return idOneRec;
	}

	public void setIdOneRec(UUID idOneRec) {
		this.idOneRec = idOneRec;
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
	
	

}
