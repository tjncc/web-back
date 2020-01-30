package services;

import java.util.ArrayList;
import java.util.Collection;

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

import beans.Kategorija;
import beans.Oglas;
import beans.User;
import beans.Oglas.Aktivan;
import beans.Poruka;
import dao.KategorijaDAO;
import dao.OglasDAO;
import dao.PorukaDAO;
import dao.UserDAO;


@Path("")
public class OglasService {

	@Context
	ServletContext context;
	
	@PostConstruct 
	public void init() {
		
		if(context.getAttribute("OglasDAO") == null) {
			String contextPath = context.getRealPath("");
			context.setAttribute("OglasDAO", new OglasDAO(contextPath));
		}
		
	}
	
	@GET
	@Path("/articleinfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Oglas> getOglasiPrikaz(@Context HttpServletRequest request)
	{
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		//System.out.println(oglasi.toString());
		oglasi.oglasiPrikaz();
		
		return oglasi.oglasiPrikaz();
		
	}
	
	@GET
	@Path("/toparticleinfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Oglas> getOglasiTopPrikaz(@Context HttpServletRequest request)
	{
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");

		
		return oglasi.oglasiPrikazTop();
		
	}
	
	
	@POST
	@Path("/addarticle")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response dodajOglas(Oglas o, @Context HttpServletRequest request)
	{
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
				
		if(oglasi.getOglasi().containsKey(o.getNaziv())) {
			return Response.status(400).build();
		}
		
		User u = users.getUsers().get(o.getProdavac());
		u.getObjavljeniOglasi().add(o.getNaziv());

		oglasi.getOglasi().put(o.getNaziv(), o);
		context.setAttribute("OglasDAO", oglasi);
		context.setAttribute("UserDAO", users);
		
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		users.saveUser(context.getRealPath(""), users);
		
		return Response.ok().build();
		
	}
	
	
	
	@GET
	@Path("/articleinfo/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Oglas getJedanOglas(@PathParam("naziv") String naziv,@Context HttpServletRequest request)
	{
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		//System.out.println(oglasi.toString());
		
		Oglas o = oglasi.getOglasi().get(naziv);

		
		return o;	
		
	}
	
	@GET
	@Path("/favarticles")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getFavOglasi(String idOne, @Context HttpServletRequest request)
	{
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		
		User u = users.findID(idOne);
		
		ArrayList<String> omiljeni = new ArrayList<String>();
		omiljeni.addAll(u.getOmiljeniOglasi());
		
		return omiljeni;
		
	}
	
	@POST
	@Path("/article/like/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response likeArticle(@PathParam("naziv") String naziv,String idOne, @Context HttpServletRequest request) {
		
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		
		User u = users.findID(idOne);
		
		Oglas oglas = oglasi.getOglasi().get(naziv);
		
		oglas.setBrLajkova(oglas.getBrLajkova()+1);
		oglas.getLajkovali().add(u.getKorisnickoIme());
		
		context.setAttribute("OglasDAO", oglasi);
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		
		return Response.ok().build();
		
	}
	
	
	@POST
	@Path("/article/dislike/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response dislikeArticle(@PathParam("naziv") String naziv, String idOne, @Context HttpServletRequest request) {
		
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		
		User u = users.findID(idOne);

		
		Oglas oglas = oglasi.getOglasi().get(naziv);
		
		oglas.setBrDislajkova(oglas.getBrDislajkova()+1);
		oglas.getLajkovali().add(u.getKorisnickoIme());
		
		context.setAttribute("OglasDAO", oglasi);
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		
		return Response.ok().build();
		
	}
	
	
	@GET
	@Path("/article-list-admin/poruceni")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Oglas> PoruceniOglasi(@Context HttpServletRequest request) {
		
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");	
		
		return oglasi.oglasiPrikazPoruceni();
	}
	
	
	@GET
	@Path("/article-list-admin/dostavljeni")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Oglas> DostavljeniOglasi(@Context HttpServletRequest request) {
		
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");		
		
		return oglasi.oglasiPrikazDostavljeni();
	}
	
	
	@POST
	@Path("/article/delete/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteArticle(@PathParam("naziv") String naziv, @Context HttpServletRequest request) {
	
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		PorukaDAO poruke = (PorukaDAO) context.getAttribute("PorukaDAO");
		Poruka p1 = new Poruka();
		
		Oglas oglas = oglasi.getOglasi().get(naziv);
		
		if(oglas.getStanje().equals(Aktivan.DOSTAVLJEN) || oglas.getStanje().equals(Aktivan.REALIZACIJA)) {
			Poruka p2 = new Poruka();
			p2.setPrimalac(oglas.getKupac());
			p2.setNaslov("Obrisan oglas");
			p2.setPosiljalac("Automatska poruka");
			p2.setSadrzaj("Admin je obrisao oglas sa nazivom " + oglas.getNaziv() + " koji ste porucili.");

			poruke.getPoruke().put(p2.getIdPoruka(), p2);
		}
		
		
		oglas.setStanje(Aktivan.OBRISAN);
		users.pretragaListi(naziv);
		
		p1.setNaslov("Obrisan oglas");
		p1.setPosiljalac("Automatska poruka");
		p1.setPrimalac(oglas.getProdavac());
		p1.setSadrzaj("Admin je obrisao vaš oglas sa nazivom " + oglas.getNaziv() + ".");
		
		
		poruke.getPoruke().put(p1.getIdPoruka(), p1);
		

		context.setAttribute("PorukaDAO", poruke);
		context.setAttribute("OglasDAO", oglasi);
		context.setAttribute("UserDAO", users);
		
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		users.saveUser(context.getRealPath(""), users);
		poruke.savePoruka(context.getRealPath(""), poruke);
		
		return Response.ok().build();

	
	}
	
	@POST
	@Path("/article/seller-delete/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteArticleSeller(@PathParam("naziv") String naziv, @Context HttpServletRequest request) {
	
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");
		PorukaDAO poruke = (PorukaDAO) context.getAttribute("PorukaDAO");
		Poruka p = new Poruka();
		
		Oglas oglas = oglasi.getOglasi().get(naziv);		
		
		if(oglas.getStanje().equals(Aktivan.AKTUELAN)) {
			oglas.setStanje(Aktivan.OBRISAN);
			users.pretragaListi(naziv);
		}
		
		p.setNaslov("Obrisan oglas");
		p.setPosiljalac("Automatska poruka");
		p.setPrimalac("admin");
		p.setSadrzaj("Prodavac " + oglas.getProdavac() + " je obrisao oglas sa nazivom " + oglas.getNaziv() + ".");
		
		poruke.getPoruke().put(p.getIdPoruka(), p);
		
		context.setAttribute("OglasDAO", oglasi);
		context.setAttribute("UserDAO", users);
		context.setAttribute("PorukaDAO", poruke);
		
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		poruke.savePoruka(context.getRealPath(""), poruke);
		users.saveUser(context.getRealPath(""), users);
		
		
		return Response.ok().build();

	
	}
	
	@POST
	@Path("/article/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editArticle(Oglas o, @Context HttpServletRequest request) {
		
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		PorukaDAO poruke = (PorukaDAO) context.getAttribute("PorukaDAO");
		Poruka p1 = new Poruka();
		
		oglasi.getOglasi().put(o.getNaziv(), o);
		
		if(o.getStanje().equals(Aktivan.DOSTAVLJEN) || o.getStanje().equals(Aktivan.REALIZACIJA)) {
			Poruka p2 = new Poruka();
			p2.setPrimalac(o.getKupac());
			p2.setNaslov("Izmenjen oglas");
			p2.setPosiljalac("Automatska poruka");
			p2.setNaziv(o.getNaziv());
			p2.setSadrzaj("Admin je izmenio gore naveden oglas koji ste porucili.");

			poruke.getPoruke().put(p2.getIdPoruka(), p2);
		}
		
		p1.setNaslov("Izmenjen oglas");
		p1.setPosiljalac("Automatska poruka");
		p1.setPrimalac(o.getProdavac());
		p1.setSadrzaj("Admin je izmenio vaš gore naveden oglas.");
		
		poruke.getPoruke().put(p1.getIdPoruka(), p1);

		context.setAttribute("PorukaDAO", poruke);		
		context.setAttribute("OglasDAO", oglasi);
		
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		poruke.savePoruka(context.getRealPath(""), poruke);
		
		return Response.ok().build();
		
	}
	

	
	@POST
	@Path("/article/category/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCateg(@PathParam("naziv") String naziv, String o, @Context HttpServletRequest request) {
		
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		KategorijaDAO kategorije = (KategorijaDAO) context.getAttribute("KategorijaDAO");
		
		Oglas oglas = oglasi.getOglasi().get(o);
		Kategorija kat = kategorije.findByName(naziv);
		
		for(String s: oglas.getKategorije()) {
			if(s.equals(naziv)) {
				return Response.status(400).build();
			}
		}
		oglas.getKategorije().add(kat.getNaziv());
		kat.getOglasi().add(oglas);
		
		
		context.setAttribute("OglasDAO", oglasi);
		context.setAttribute("KategorijaDAO", kategorije);
		
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		kategorije.saveKategorija(context.getRealPath(""), kategorije);
		
		
		return Response.ok().build();
	}
	
	
	@GET
	@Path("/category/articles/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Oglas> allArticlesinCat(@PathParam("naziv") String naziv, @Context HttpServletRequest request) {
		
		KategorijaDAO kategorije = (KategorijaDAO) context.getAttribute("KategorijaDAO");
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		
		Kategorija kat = kategorije.findByName(naziv);
		
		//ArrayList<Oglas> o = new ArrayList<Oglas>();
		return oglasi.oglasiPrikazKateg(kat);
		
	}
	
	
	@POST
	@Path("/report/article/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportArticle(@PathParam("naziv") String naziv, String idOne, @Context HttpServletRequest request) {
		
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		PorukaDAO poruke = (PorukaDAO) context.getAttribute("PorukaDAO");
		UserDAO users = (UserDAO) context.getAttribute("UserDAO");

		Poruka p = new Poruka();
		
		Oglas oglas = oglasi.getOglasi().get(naziv);
		User user = users.getUsers().get(oglas.getProdavac());
		User ulogovan = users.findID(idOne);

		for(String ime: oglas.getPrijavili()) {
			if(ime.equals(ulogovan.getKorisnickoIme())) {
				return Response.status(400).build();
			}
		}
		
		oglas.setPrijave(oglas.getPrijave() + 1);
		oglas.getPrijavili().add(ulogovan.getKorisnickoIme());
		
		p.setNaslov("Upozorenje");
		p.setNaziv(oglas.getNaziv());
		p.setPosiljalac("Automatska poruka");
		p.setPrimalac(user.getKorisnickoIme());
		p.setSadrzaj("Kupac je prijavio vaš gore navedeni oglas zbog prevare. Upozoravamo vas da ne bi došlo do ponovne prijave.");
		
		poruke.getPoruke().put(p.getIdPoruka(), p);

		context.setAttribute("PorukaDAO", poruke);
		context.setAttribute("OglasDAO", oglasi);
		context.setAttribute("UserDAO", users);
		
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		poruke.savePoruka(context.getRealPath(""), poruke);
		users.saveUser(context.getRealPath(""), users);
				
		return Response.ok().build();
	}

}
