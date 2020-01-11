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

import beans.Oglas;
import beans.Recenzija;
import dao.OglasDAO;
import dao.RecenzijaDAO;

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
		System.out.println(naziv);
		RecenzijaDAO recenzije = (RecenzijaDAO) context.getAttribute("RecenzijaDAO");
		return recenzije.recenzijePrikazOglas(naziv);
		
	}
	
	
	@POST
	@Path("/addreview")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response dodajRecenziju(Recenzija recenzija, @Context HttpServletRequest request)
	{
		RecenzijaDAO recenzije = (RecenzijaDAO) context.getAttribute("RecenzijaDAO");
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		
		Oglas o = oglasi.findID(recenzija.getOglas());
		o.getRecenzije().add(recenzija.getIdOneRec());
		
		recenzije.getRecenzije().put(recenzija.getIdOneRec(), recenzija);
		
		context.setAttribute("OglasDAO", oglasi);
		context.setAttribute("RecenzijaDAO", recenzije);
		
		return Response.ok().build();		
	}
	
}
