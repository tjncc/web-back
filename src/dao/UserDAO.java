package dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import beans.User;
import beans.User.Uloga;

public class UserDAO {
	
	
	
	private HashMap<String, User> users = new HashMap<>();
	
	public UserDAO() {
		User u1 = new User("user","user","Tamara","Jancic",Uloga.KUPAC,"063377237","Novi Sad","tamaraa.jancic@gmail.com");
		users.put("user", u1);
		
		User admin = new User("admin","admin","Admin","Admin",Uloga.ADMINISTRATOR,"","","");
		users.put("admin", admin);
		
		User seller = new User("seller","seller","Seller","Seller",Uloga.PRODAVAC,"","","");
		users.put("seller", seller);
	}
	
	public UserDAO(String contextPath) {
		
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
	
	public ArrayList<User> getUsersList() {
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
	
	public ArrayList<User> getAllUsers() {
		ArrayList<User> sviKupci = new ArrayList<User>();
		
		for(User user: users.values()) {
			if(user.getUloga().equals(Uloga.KUPAC)) {
				sviKupci.add(user);
			}
		}
		
		return sviKupci;
	}
	
	public ArrayList<User> getAllSellers() {
		ArrayList<User> sviProdavci = new ArrayList<User>();
		
		for(User user: users.values()) {
			if(user.getUloga().equals(Uloga.PRODAVAC)) {
				sviProdavci.add(user);
			}
		}
		
		return sviProdavci;
	}
	
	public ArrayList<User> getAllAdmins() {
		ArrayList<User> sviAdmini = new ArrayList<User>();
		
		for(User user: users.values()) {
			if(user.getUloga().equals(Uloga.ADMINISTRATOR)) {
				sviAdmini.add(user);
			}
		}
		
		return sviAdmini;
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
	
	/*
	public Collection<User> findAll() {
		
		return users.values();
	}
	*/
	
	

}
