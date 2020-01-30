package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import beans.Poruka;
import beans.User;

public class PorukaDAO {
	
	private HashMap<String, Poruka> poruke = new HashMap<>();
	
	
	public PorukaDAO(String contextPath) {
		poruke = new HashMap<String, Poruka>();
		loadPoruka(contextPath);
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
	
	@SuppressWarnings("unchecked")
	public void loadPoruka(String contextPath) {
		FileWriter fileWriter = null;
		BufferedReader in = null;
		File file = null;
		try {
			file = new File(contextPath + "/poruke.txt");
			in = new BufferedReader(new FileReader(file));

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			TypeFactory factory = TypeFactory.defaultInstance();
			MapType type = factory.constructMapType(HashMap.class, String.class, Poruka.class);
			mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			poruke = ((HashMap<String, Poruka>) mapper.readValue(file, type));

		} catch (FileNotFoundException fnfe) {
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String kategString = objectMapper.writeValueAsString(poruke);

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
	
	
	public void savePoruka(String path, PorukaDAO poruke) {
		
		
		File f = new File(path + "/poruke.txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(f);

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			String kategString = objectMapper.writeValueAsString(poruke.getPoruke());
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
