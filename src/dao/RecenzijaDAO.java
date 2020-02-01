package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import beans.Oglas;
import beans.Poruka;
import beans.Recenzija;

public class RecenzijaDAO {
	
	private HashMap<String, Recenzija> recenzije = new HashMap<>();
	
	public RecenzijaDAO(String contextPath) {
		recenzije = new HashMap<String, Recenzija>();
		loadRecenzija(contextPath);
	}

	public HashMap<String, Recenzija> getRecenzije() {
		return recenzije;
	}

	public void setRecenzije(HashMap<String, Recenzija> recenzije) {
		this.recenzije = recenzije;
	}
	
	public Recenzija findID(String idOneRec) {
		//System.out.println("Prosledjen idOne: " + idOneRec);
		for(Recenzija rec: recenzije.values()) {
			//System.out.println("ovaj iz recenzije id: " + rec.getIdRec());
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
	
	
	@SuppressWarnings("unchecked")
	public void loadRecenzija(String contextPath) {
		FileWriter fileWriter = null;
		BufferedReader in = null;
		File file = null;
		try {
			file = new File(contextPath + "/recenzije.txt");
			in = new BufferedReader(new FileReader(file));

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			TypeFactory factory = TypeFactory.defaultInstance();
			MapType type = factory.constructMapType(HashMap.class, String.class, Recenzija.class);
			mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			recenzije = ((HashMap<String, Recenzija>) mapper.readValue(file, type));

		} catch (FileNotFoundException fnfe) {
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String kategString = objectMapper.writeValueAsString(recenzije);

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
	
	
	public void saveRecenzija(String path, RecenzijaDAO recenzije) {
		
		
		File f = new File(path + "/recenzije.txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(f);

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			String kategString = objectMapper.writeValueAsString(recenzije.getRecenzije());
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
