package services;

import java.util.ArrayList;
import java.util.Collection;
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

import beans.Kategorija;
import beans.Oglas;
import beans.Recenzija;
import beans.User;
import beans.Oglas.Aktivan;
import dao.KategorijaDAO;
import dao.OglasDAO;
import dao.RecenzijaDAO;
import dao.UserDAO;

@Path("")
public class RecenzijaService {

	@Context
	ServletContext context;
	
	@PostConstruct 
	public void init() {
		
		if(context.getAttribute("RecenzijaDAO") == null) {
			context.setAttribute("RecenzijaDAO", new RecenzijaDAO());
		}
		
	}
	
	
	@GET
	@Path("/reviews/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Recenzija> getRecenzije(@PathParam("naziv") String naziv, @Context HttpServletRequest request)
	{
		RecenzijaDAO recenzije = (RecenzijaDAO) context.getAttribute("RecenzijaDAO");
		return recenzije.recenzijePrikazOglas(naziv);
		
	}
	
	@GET
	@Path("/reviews-seller/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Recenzija> getProdavacRecenzije(@PathParam("naziv") String naziv, @Context HttpServletRequest request)
	{
		RecenzijaDAO recenzije = (RecenzijaDAO) context.getAttribute("RecenzijaDAO");
		
		
		return recenzije.recenzijePrikazProdavac(naziv);
		
	}
	
	@GET
	@Path("/reviews-seller-his/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Recenzija> getProdavacRecenzijeNjegove(@PathParam("naziv") String naziv, @Context HttpServletRequest request)
	{
		RecenzijaDAO recenzije = (RecenzijaDAO) context.getAttribute("RecenzijaDAO");
		
		
		return recenzije.recenzijePrikazProdavacNjegove(naziv);
		
	}
	
	
	@POST
	@Path("/addreview")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response dodajRecenziju(Recenzija recenzija, @Context HttpServletRequest request)
	{
		RecenzijaDAO recenzije = (RecenzijaDAO) context.getAttribute("RecenzijaDAO");
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		System.out.println(recenzija.getOglas());
		
		if(!recenzija.getOglas().contentEquals("RECENZIJAPRODAVCA")) {
			Oglas o = oglasi.findID(recenzija.getOglas());
			o.getRecenzije().add(recenzija.getIdRec());
			recenzija.setProdavac(o.getProdavac());
		}
		//recenzija.setAktivna(true);
		//recenzija.setIdRec(UUID.randomUUID().toString());
		
		
		recenzije.getRecenzije().put(recenzija.getIdRec(), recenzija);
		
		context.setAttribute("OglasDAO", oglasi);
		context.setAttribute("RecenzijaDAO", recenzije);
		
		return Response.ok().build();		
	}
	
	@GET
	@Path("/reviewinfo/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Recenzija getJednuRecenziju(@PathParam("naziv") String naziv,@Context HttpServletRequest request)
	{
		RecenzijaDAO recenzije = (RecenzijaDAO) context.getAttribute("RecenzijaDAO");
		System.out.println(naziv);
		Recenzija rec = recenzije.findID(naziv);
		System.out.println(rec.getNaziv());
		return rec;			
	}
	
	
	@POST
	@Path("/review/delete/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteArticle(@PathParam("naziv") String naziv, @Context HttpServletRequest request) {
	
		//OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		RecenzijaDAO recenzije = (RecenzijaDAO) context.getAttribute("RecenzijaDAO");
		System.out.println(naziv);
		Recenzija recenzija = recenzije.findID(naziv);
		System.out.println("ovo je recenzija dobijena: " + recenzija);
		//Oglas oglas = oglasi.getOglasi().get(recenzija.getOglas());		
		
		//oglas.getRecenzije().remove(recenzija.getIdOneRec());
		recenzija.setAktivna(false);
		
		//context.setAttribute("OglasDAO", oglasi);
		context.setAttribute("RecenzijaDAO", recenzije);
		
		return Response.ok().build();	
	}
	
	@POST
	@Path("/review/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editArticle(Recenzija rec, @Context HttpServletRequest request) {
		
		RecenzijaDAO recenzije = (RecenzijaDAO) context.getAttribute("RecenzijaDAO");
		
		recenzije.getRecenzije().put(rec.getIdRec(), rec);
		
		context.setAttribute("RecenzijaDAO", recenzije);
		
		return Response.ok().build();
		
	}
	
}
