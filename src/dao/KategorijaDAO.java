package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import beans.Kategorija;
import beans.Oglas;


public class KategorijaDAO {
	
	private HashMap<String, Kategorija> kategorije = new HashMap<>();
	
	public KategorijaDAO(){};
	
	public KategorijaDAO(String contextPath) {
		/*
		//Kategorija jakne = new Kategorija("Jakne");
		Kategorija patike = new Kategorija("Patike");
		Kategorija haljine = new Kategorija("Haljine");
		Kategorija knjige = new Kategorija("Knjige");
		Kategorija telefoni = new Kategorija("Telefoni");
		Kategorija lampe = new Kategorija("Lampe");
		Kategorija lekovi = new Kategorija("Lekovi");
		Kategorija laptopovi = new Kategorija("Laptopovi");
		Kategorija ogledala = new Kategorija("Ogledala");
		Kategorija prozori = new Kategorija("Prozori");
		Kategorija stolice = new Kategorija("Stolice");
		Kategorija svece = new Kategorija("Svece");
		Kategorija parfemi = new Kategorija("Parfemi");
		Kategorija satovi = new Kategorija("Satovi");
		
		kategorije.put("Jakne", new Kategorija("Jakne"));
		kategorije.put(patike.getNaziv(), patike);
		kategorije.put(haljine.getNaziv(), haljine);
		kategorije.put(knjige.getNaziv(), knjige);
		kategorije.put(telefoni.getNaziv(), telefoni);
		kategorije.put(lampe.getNaziv(), lampe);
		kategorije.put(lekovi.getNaziv(), lekovi);
		kategorije.put(laptopovi.getNaziv(), laptopovi);
		kategorije.put(ogledala.getNaziv(), ogledala);
		kategorije.put(prozori.getNaziv(), prozori);
		kategorije.put(stolice.getNaziv(), stolice);
		kategorije.put(svece.getNaziv(), svece);
		kategorije.put(parfemi.getNaziv(), parfemi);
		kategorije.put(satovi.getNaziv(), satovi);
		
		*/
		
		kategorije = new HashMap<String, Kategorija>();
		loadKategorija(contextPath);
		
		
	}

	public HashMap<String, Kategorija> getKategorije() {
		return kategorije;
	}
	
	public ArrayList<Kategorija> kategorijePrikaz(){
		ArrayList<Kategorija> aktuelne = new ArrayList<Kategorija>();
		
		for(Kategorija kat: kategorije.values()) {
			if(kat.isAktivna() == true) {
				aktuelne.add(kat);
			}
		}
		return aktuelne;
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
	
	public Kategorija findByName(String naziv) {
		for(Kategorija kat: kategorije.values()) {
			if(naziv.equals(kat.getNaziv())) {
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
	
	
	@SuppressWarnings("unchecked")
	public void loadKategorija(String contextPath) {
		FileWriter fileWriter = null;
		BufferedReader in = null;
		File file = null;
		try {
			file = new File(contextPath + "/kategorije.txt");
			in = new BufferedReader(new FileReader(file));

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			TypeFactory factory = TypeFactory.defaultInstance();
			MapType type = factory.constructMapType(HashMap.class, String.class, Kategorija.class);
			mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			kategorije = ((HashMap<String, Kategorija>) mapper.readValue(file, type));
		} catch (FileNotFoundException fnfe) {
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String kategString = objectMapper.writeValueAsString(kategorije);

				fileWriter.write(kategString);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fileWriter != null) {
					try {
						fileWriter.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void saveKategorija(String path, KategorijaDAO kategorije) {
		
		
		File f = new File(path + "/kategorije.txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(f);

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			String kategString = objectMapper.writeValueAsString(kategorije.getKategorije());
			fileWriter.write(kategString);
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


}
