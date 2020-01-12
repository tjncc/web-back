package beans;

import java.util.ArrayList;

public class User{
	
	//UUID idOne = UUID.randomUUID();
	private String idOne;
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private Uloga uloga;
	private String telefon;
	private String grad;
	private String email;
	private String datumRegistracije;
	
	private ArrayList<String> poruceniProizvodi = new ArrayList<String>();
	private ArrayList<String> dostavljeniProizvodi = new ArrayList<String>();
	private ArrayList<String> omiljeniOglasi = new ArrayList<String>();
	
	private ArrayList<String> objavljeniOglasi = new ArrayList<String>();
	private ArrayList<String> isporuceniOglasi = new ArrayList<String>();
	private ArrayList<Poruka> poruke = new ArrayList<Poruka>();
	private int brLajkova;
	private int brDislajkova;
	private int prijave;
	
	private ArrayList<String> lajkovali = new ArrayList<String>();
	
	
	public static enum Uloga{
		KUPAC,
		PRODAVAC,
		ADMINISTRATOR
	};
	
	public User() {
		this.uloga = Uloga.KUPAC;
		this.datumRegistracije = java.time.LocalDate.now().toString();
		this.idOne = "";
		this.prijave = 0;
		this.brLajkova = 0;
		this.brDislajkova = 0;
		this.lajkovali = new ArrayList<String>();
	}
	


	public User(String korisnickoIme, String lozinka, String ime, String prezime, Uloga uloga, String telefon,
			String grad, String email) {

		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.uloga = uloga;
		this.telefon = telefon;
		this.grad = grad;
		this.email = email;
		this.brLajkova = 0;
		this.brDislajkova = 0;
		this.datumRegistracije = java.time.LocalDate.now().toString();
		this.prijave = 0;
		this.lajkovali = new ArrayList<String>();

	}
	



	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}



	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(String datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}


	public String getIdOne() {
		return idOne;
	}


	public void setIdOne(String idOne) {
		this.idOne = idOne;
	}
	
	
	


	public Uloga getUloga() {
		return uloga;
	}


	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}


	public ArrayList<String> getPoruceniProizvodi() {
		return poruceniProizvodi;
	}


	public void setPoruceniProizvodi(ArrayList<String> poruceniProizvodi) {
		this.poruceniProizvodi = poruceniProizvodi;
	}


	public ArrayList<String> getDostavljeniProizvodi() {
		return dostavljeniProizvodi;
	}


	public void setDostavljeniProizvodi(ArrayList<String> dostavljeniProizvodi) {
		this.dostavljeniProizvodi = dostavljeniProizvodi;
	}


	public ArrayList<String> getOmiljeniOglasi() {
		return omiljeniOglasi;
	}


	public void setOmiljeniOglasi(ArrayList<String> omiljeniOglasi) {
		this.omiljeniOglasi = omiljeniOglasi;
	}


	public ArrayList<String> getObjavljeniOglasi() {
		return objavljeniOglasi;
	}


	public void setObjavljeniOglasi(ArrayList<String> objavljeniOglasi) {
		this.objavljeniOglasi = objavljeniOglasi;
	}


	public ArrayList<String> getIsporuceniOglasi() {
		return isporuceniOglasi;
	}


	public void setIsporuceniOglasi(ArrayList<String> isporuceniOglasi) {
		this.isporuceniOglasi = isporuceniOglasi;
	}


	public ArrayList<Poruka> getPoruke() {
		return poruke;
	}


	public void setPoruke(ArrayList<Poruka> poruke) {
		this.poruke = poruke;
	}


	public int getBrLajkova() {
		return brLajkova;
	}


	public void setBrLajkova(int brLajkova) {
		this.brLajkova = brLajkova;
	}


	public int getBrDislajkova() {
		return brDislajkova;
	}


	public void setBrDislajkova(int brDislajkova) {
		this.brDislajkova = brDislajkova;
	}

	
	

	public int getPrijave() {
		return prijave;
	}



	public void setPrijave(int prijave) {
		this.prijave = prijave;
	}



	public ArrayList<String> getLajkovali() {
		return lajkovali;
	}



	public void setLajkovali(ArrayList<String> lajkovali) {
		this.lajkovali = lajkovali;
	}



	@Override
	public String toString() {
		String retVal = "";
		
		retVal += "\nKorisnicko ime: " + this.korisnickoIme;
		retVal += "\nLozinka: " + this.lozinka;
		retVal += "\nIme: " + this.ime;
		retVal += "\nPrezime: " + this.prezime;
		retVal += "\nUloga: " + this.uloga;
		retVal += "\nTelefon: " + this.telefon;
		retVal += "\nGrad: " + this.grad;
		retVal += "\nE-mail: " + this.email;
		retVal += "\nDatum registracije: " + this.datumRegistracije;
		retVal += "\nID: " + this.idOne;
		
		return retVal;
	}

	

	
	
}
