package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.User;
import beans.User.Uloga;
import beans.Oglas;
import beans.Poruka;
import beans.Oglas.Aktivan;
import dao.UserDAO;
import dao.OglasDAO;
import dao.PorukaDAO;


@Path("")
public class UserService {
	@Context
	ServletContext context;
	
	@PostConstruct 
	public void init() {
		
		if(context.getAttribute("UserDAO") == null) {
			String contextPath = context.getRealPath("");
			context.setAttribute("UserDAO", new UserDAO(contextPath));
		}
	}
	
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(User u, @Context HttpServletRequest request) {

		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		if(users.getUsers().containsKey(u.getKorisnickoIme())) {
			return Response.status(400).build();
		}
		
		users.getUsers().put(u.getKorisnickoIme(), u);
		context.setAttribute("UserDAO", users);
		
		users.saveUser(context.getRealPath(""), users);
		
		return Response.ok().build();
		
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User u, @Context HttpServletRequest request) {
	
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		
		User user = users.find(u);
		
		if(user == null) {
			return Response.status(400).build();
		}
		
		String idOne = UUID.randomUUID().toString();
		user.setIdOne(idOne);		
		
		context.setAttribute("UserDAO", users);
		
		users.saveUser(context.getRealPath(""), users);
		
		return Response.ok(idOne).build();		
		
	}
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void logout(String idOne, @Context HttpServletRequest request) {
		
		UserDAO users = (UserDAO) context.getAttribute("UserDAO"); 		
		
		User user = users.findID(idOne);
		//System.out.println(user.toString());
		user.setIdOne("");
		
		context.setAttribute("UserDAO", users);
		
		users.saveUser(context.getRealPath(""), users);
		
		//System.out.println(users.toString());
		
	}
	
	@POST
	@Path("/userinfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserData(String idOne,  @Context HttpServletRequest request) {
		
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		
		User user = users.findID(idOne);
		

		
		if(user == null)
		{
			return Response.status(400).build();
		}
		
		return Response.ok(user).build();	
	}
	
	
	@POST
	@Path("/sellerinfo/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSellerData(@PathParam("naziv") String naziv,  @Context HttpServletRequest request) {
		
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		
		User user = users.getUsers().get(naziv);
			
		if(user == null)
		{
			return Response.status(400).build();
		}
		
		return Response.ok(user).build();	
	}
	
	@POST
	@Path("/user/fav/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response favourite(@PathParam("naziv") String naziv, String idOne,  @Context HttpServletRequest request) {
		
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		
		Oglas oglas = oglasi.getOglasi().get(naziv);
		User u = (User)users.findID(idOne);
		
		oglas.setOmiljen(oglas.getOmiljen()+1);
		u.getOmiljeniOglasi().add(oglas.getNaziv());
		context.setAttribute("UserDAO", users);
		context.setAttribute("OglasDAO", oglasi);
		
		users.saveUser(context.getRealPath(""), users);
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		
		return Response.ok().build();
		
	}
	
	@POST
	@Path("/user/fav-remove/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response Removefavourite(@PathParam("naziv") String naziv, String idOne,  @Context HttpServletRequest request) {
		
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		
		Oglas oglas = oglasi.getOglasi().get(naziv);
		User u = (User)users.findID(idOne);
		
		oglas.setOmiljen(oglas.getOmiljen()-1);
		u.getOmiljeniOglasi().remove(oglas.getNaziv());
		
		context.setAttribute("UserDAO", users);
		context.setAttribute("OglasDAO", oglasi);
		
		users.saveUser(context.getRealPath(""), users);
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		
		return Response.ok().build();
		
	}
	
	
	@POST
	@Path("/user/view-article/{list}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response favouriteView(@PathParam("list") String list, String idOne,  @Context HttpServletRequest request) {
		
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		
		User u = users.findID(idOne);
		ArrayList<Oglas> listaOglasa = new ArrayList<Oglas>();
		
		if(list.equals("omiljeni")) {
			listaOglasa = oglasi.findOglasi(u.getOmiljeniOglasi());
		} else if (list.equals("poruceni")) {
			listaOglasa = oglasi.findOglasi(u.getPoruceniProizvodi());
		} else if(list.equals("dostavljeni")) {
			listaOglasa = oglasi.findOglasi(u.getDostavljeniProizvodi());
		} else {
			return Response.status(400).build();
		}
		
		return Response.ok(listaOglasa).build();

	}
	
	
	@POST
	@Path("/user/order/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response orderArticle(@PathParam("naziv") String naziv, String idOne,  @Context HttpServletRequest request) {
	
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		
		Oglas oglas = oglasi.getOglasi().get(naziv);
		User u = (User)users.findID(idOne);
		oglas.setStanje(Aktivan.REALIZACIJA);
		oglas.setKupac(u.getKorisnickoIme());
		
		u.getPoruceniProizvodi().add(oglas.getNaziv());
		context.setAttribute("UserDAO", users);
		context.setAttribute("OglasDAO", oglasi);
		
		users.saveUser(context.getRealPath(""), users);
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		
		return Response.ok().build();

	
	}
	
	@POST
	@Path("/user/delivered/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deliveredArticle(@PathParam("naziv") String naziv, String idOne,  @Context HttpServletRequest request) {
	
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		PorukaDAO poruke = (PorukaDAO) context.getAttribute("PorukaDAO");
		Poruka p = new Poruka();

		
		Oglas oglas = oglasi.getOglasi().get(naziv);
		User u = (User)users.findID(idOne);
		User prodavac = (User)users.getUsers().get(oglas.getProdavac());
		
		oglas.setStanje(Aktivan.DOSTAVLJEN);
		oglas.setKupac(u.getKorisnickoIme());
		
		u.getDostavljeniProizvodi().add(oglas.getNaziv());
		u.getPoruceniProizvodi().remove(oglas.getNaziv());
		
		if(prodavac != null) {
			prodavac.getIsporuceniOglasi().add(oglas.getNaziv());			
		}
		
		p.setNaziv(oglas.getNaziv());
		p.setNaslov("Dostavljen proizvod");
		p.setPosiljalac("Automatska poruka");
		p.setPrimalac(oglas.getProdavac());
		p.setSadrzaj("Kupac " + oglas.getKupac() + " je oznacio da mu je dostavljen gore naveden oglas.");
		
		poruke.getPoruke().put(p.getIdPoruka(), p);
		
		context.setAttribute("PorukaDAO", poruke);
		context.setAttribute("OglasDAO", oglasi);
		context.setAttribute("UserDAO", users);
		
		users.saveUser(context.getRealPath(""), users);
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		poruke.savePoruka(context.getRealPath(""), poruke);
		
		return Response.ok().build();

	
	}
	

	
	
	@POST
	@Path("/usersinfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getUsersData(String idOne,  @Context HttpServletRequest request) {
		
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");

		
		return users.getUsers().values();
	}
	
	
	@POST
	@Path("/change-user/{korisnickoIme}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeUser(@PathParam("korisnickoIme") String korisnickoIme,@Context HttpServletRequest request) {
		
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		
		
		User u = users.pretraga(korisnickoIme);

		
		if(u.getUloga().equals(Uloga.KUPAC)) {
			u.setUloga(Uloga.PRODAVAC);
		} else if (u.getUloga().equals(Uloga.PRODAVAC)) {
			u.setUloga(Uloga.KUPAC);
		}
		context.setAttribute("UserDAO", users);
		
		users.saveUser(context.getRealPath(""), users);
		
		return Response.ok().build();
	}
	
	
	@POST
	@Path("/userreport/{korisnickoIme}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportUser(@PathParam("korisnickoIme") String korisnickoIme, String idOne,  @Context HttpServletRequest request) {
		
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		PorukaDAO poruke = (PorukaDAO) context.getAttribute("PorukaDAO");
		Poruka p = new Poruka();

		
		User user = users.getUsers().get(korisnickoIme);
		User ulogovan = users.findID(idOne);

		
		for(String ime: user.getPrijavili()) {
			if(ime.equals(ulogovan.getKorisnickoIme())) {
				return Response.status(400).build();
			}
		}

		
		user.setPrijave(user.getPrijave() + 1);
		
		user.getPrijavili().add(ulogovan.getKorisnickoIme());
		
		p.setNaslov("Upozorenje");
		p.setPosiljalac("Automatska poruka");
		p.setPrimalac(user.getKorisnickoIme());
		p.setSadrzaj("Kupac je prijavio vaš profil zbog prevare. Upozoravamo vas da ne bi došlo do ponovne prijave.");
		
		poruke.getPoruke().put(p.getIdPoruka(), p);

		context.setAttribute("PorukaDAO", poruke);			
		context.setAttribute("UserDAO", users);
		
		users.saveUser(context.getRealPath(""), users);
		poruke.savePoruka(context.getRealPath(""), poruke);
				
		return Response.ok().build();
	}
	
	@POST
	@Path("/user/like/{korisnickoIme}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response likeUser(@PathParam("korisnickoIme") String korisnickoIme, String idOne,  @Context HttpServletRequest request) {
		
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		
		User user = users.getUsers().get(korisnickoIme);
		User ulogovan = users.findID(idOne);
		
		if(user == null)
		{
			return Response.status(400).build();
		}
		
		user.setBrLajkova(user.getBrLajkova() + 1);
		user.getLajkovali().add(ulogovan.getKorisnickoIme());
		
		context.setAttribute("UserDAO", users);
		
		users.saveUser(context.getRealPath(""), users);
				
		return Response.ok().build();
	}

	@POST
	@Path("/user/dislike/{korisnickoIme}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response dislikeUser(@PathParam("korisnickoIme") String korisnickoIme, String idOne,  @Context HttpServletRequest request) {
		
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		
		User user = users.getUsers().get(korisnickoIme);
		User ulogovan = users.findID(idOne);
		
		if(user == null)
		{
			return Response.status(400).build();
		}
		
		user.setBrDislajkova(user.getBrDislajkova() + 1);
		user.getLajkovali().add(ulogovan.getKorisnickoIme());
		
		context.setAttribute("UserDAO", users);
		
		users.saveUser(context.getRealPath(""), users);

		return Response.ok().build();
	}
	
	
	
	@GET
	@Path("/allusers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> allUsers(@Context HttpServletRequest request) {
		
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		
		return users.allUsersList();
		
	}
	
	
}



