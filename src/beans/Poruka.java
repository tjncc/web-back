package beans;

import java.util.UUID;

public class Poruka {

	UUID idOne = UUID.randomUUID();
	
	private String naziv;
	private User posiljalac;
	private String naslov;
	private String sadrzaj;
	private String datumIVreme;
	
	public Poruka(String naziv, User posiljalac, String naslov, String sadrzaj, String datumIVreme) {
		super();
		this.naziv = naziv;
		this.posiljalac = posiljalac;
		this.naslov = naslov;
		this.sadrzaj = sadrzaj;
		this.datumIVreme = datumIVreme;
	}
	
	public Poruka() {
		super();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public User getPosiljalac() {
		return posiljalac;
	}

	public void setPosiljalac(User posiljalac) {
		this.posiljalac = posiljalac;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public String getDatumIVreme() {
		return datumIVreme;
	}

	public void setDatumIVreme(String datumIVreme) {
		this.datumIVreme = datumIVreme;
	}
	
	
	
}
