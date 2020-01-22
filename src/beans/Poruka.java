package beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Poruka {

	UUID idOne = UUID.randomUUID();
	
	private String idPoruka;
	private String naziv;
	private String posiljalac;
	private String naslov;
	private String sadrzaj;
	private LocalDateTime dt;
	private String dateAndTime;
	private String primalac;
	
	private Boolean aktivna;
	private Boolean procitana;
	
	public Poruka(String naziv, String posiljalac, String naslov, String sadrzaj, String datumIVreme, String primalac) {
		super();
		this.idPoruka = UUID.randomUUID().toString();
		this.naziv = naziv;
		this.posiljalac = posiljalac;
		this.naslov = naslov;
		this.sadrzaj = sadrzaj;
		this.dt = LocalDateTime.now();
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    this.dateAndTime = dt.format(myFormatObj);
		
		this.primalac = primalac;
		this.aktivna = true;
		this.procitana = false;
	}
	
	public Poruka() {
		super();
		this.idPoruka = UUID.randomUUID().toString();
		this.dt = LocalDateTime.now();
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 this.dateAndTime = dt.format(myFormatObj);
		 
		 this.aktivna = true;
		 this.procitana = false;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPosiljalac() {
		return posiljalac;
	}

	public void setPosiljalac(String posiljalac) {
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


	

	public LocalDateTime getDt() {
		return dt;
	}

	public void setDt(LocalDateTime dt) {
		this.dt = dt;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getIdPoruka() {
		return idPoruka;
	}

	public void setIdPoruka(String idPoruka) {
		this.idPoruka = idPoruka;
	}

	public String getPrimalac() {
		return primalac;
	}

	public void setPrimalac(String primalac) {
		this.primalac = primalac;
	}

	public Boolean getAktivna() {
		return aktivna;
	}

	public void setAktivna(Boolean aktivna) {
		this.aktivna = aktivna;
	}

	public Boolean getProcitana() {
		return procitana;
	}

	public void setProcitana(Boolean procitana) {
		this.procitana = procitana;
	}

	
	
	
	
	
}
