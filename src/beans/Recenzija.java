package beans;

import java.util.UUID;

public class Recenzija {
	
	UUID idOne = UUID.randomUUID();
	
	public Oglas oglas;
	public User recezent;
	public String naziv;
	public String sadrzaj;
	public String slika;
	public boolean tacanOglas;
	public boolean ispostovan;
	
	public Recenzija(Oglas oglas, User recezent, String naziv, String sadrzaj, String slika, boolean tacanOglas,
			boolean ispostovan) {
		super();
		this.oglas = oglas;
		this.recezent = recezent;
		this.naziv = naziv;
		this.sadrzaj = sadrzaj;
		this.slika = slika;
		this.tacanOglas = tacanOglas;
		this.ispostovan = ispostovan;
	}
	
	public Recenzija() {
		super();
	}

	public Oglas getOglas() {
		return oglas;
	}

	public void setOglas(Oglas oglas) {
		this.oglas = oglas;
	}

	public User getRecezent() {
		return recezent;
	}

	public void setRecezent(User recezent) {
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
