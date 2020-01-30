package dao;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import beans.Oglas;
import beans.User;
import beans.User.Uloga;

public class UserDAO {
	
	
	
	private HashMap<String, User> users = new HashMap<>();
	
	public UserDAO() {
	}
	
	public UserDAO(String contextPath) {
		users = new HashMap<String, User>();
		loadUser(contextPath);
		
		User u1 = new User("user","user","Tamara","Jancic",Uloga.KUPAC,"063377237","Novi Sad","tamaraa.jancic@gmail.com");
		users.put("user", u1);
		
		User admin = new User("admin","admin","Admin","Admin",Uloga.ADMINISTRATOR,"","","");
		users.put("admin", admin);
		
		User seller = new User("seller","seller","Seller","Seller",Uloga.PRODAVAC,"","","");
		users.put("seller", seller);
	}
	
	public User find(User u) {
		for(User user: users.values()) {
			if(u.getKorisnickoIme().equals(user.getKorisnickoIme())) {
				if(u.getLozinka().equals(user.getLozinka())) {
					return user;
				} else {
					return null;
				}
			}
		}
		return null;
	}
	
	public HashMap<String,User> getUsers() {
		return users;
	}
	
	public ArrayList<User> allUsersList() {
		ArrayList<User> listaSvi = new ArrayList<User>();
		
		for(User user: users.values()) {
			listaSvi.add(user);
		}
		
		return listaSvi;
	}
	
	
	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}
	
	
	public User findID(String idOne) {
		for(User user: users.values()) {
			if(idOne.equals(user.getIdOne())) {
				return user;
			}
			
		}
		return null;
	}
	
	public User pretraga(String user) {
		if(!users.containsKey(user))
		{
			return null;
		}
		
		User u = users.get(user);
		return u;
	}
	
	public void pretragaListi(String naziv) {
		
		for(User user: users.values()) {
			if(user.getDostavljeniProizvodi().contains(naziv)) {
				user.getDostavljeniProizvodi().remove(naziv);
			} else if(user.getOmiljeniOglasi().contains(naziv)) {
				user.getOmiljeniOglasi().remove(naziv);
			} else if(user.getPoruceniProizvodi().contains(naziv)) {
				user.getPoruceniProizvodi().remove(naziv);
			}
		}
	}
	
	

	@Override
	public String toString() {
		String retVal = "";
		for(User user: users.values()) {
			retVal += user.getKorisnickoIme() + " ";
			retVal += user.getIdOne() + "\n";
		}
		return retVal;
	}
	
	
	@SuppressWarnings("unchecked")
	public void loadUser(String contextPath) {
		FileWriter fileWriter = null;
		BufferedReader in = null;
		File file = null;
		try {
			file = new File(contextPath + "/users.txt");
			in = new BufferedReader(new FileReader(file));

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			TypeFactory factory = TypeFactory.defaultInstance();
			MapType type = factory.constructMapType(HashMap.class, String.class, User.class);
			mapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			users = ((HashMap<String, User>) mapper.readValue(file, type));
			for(User u : users.values()) {
				System.out.println(u.toString());
			}
		} catch (FileNotFoundException fnfe) {
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String kategString = objectMapper.writeValueAsString(users);

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
	
	
	public void saveUser(String path, UserDAO users) {
		
		
		File f = new File(path + "/users.txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(f);

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			String kategString = objectMapper.writeValueAsString(users.getUsers());
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
