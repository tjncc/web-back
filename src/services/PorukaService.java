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

import beans.Poruka;
import beans.Recenzija;
import dao.PorukaDAO;



@Path("")
public class PorukaService {

	@Context
	ServletContext context;
	
	@PostConstruct 
	public void init() {
		
		if(context.getAttribute("PorukaDAO") == null) {
			context.setAttribute("PorukaDAO", new PorukaDAO());
		}		
	}
	
	
	@GET
	@Path("/messages/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Poruka> getPoruke(@PathParam("naziv") String naziv, @Context HttpServletRequest request)
	{
		PorukaDAO poruke = (PorukaDAO) context.getAttribute("PorukaDAO");
		return poruke.porukePrikaz(naziv);
		
	}
	

	@POST
	@Path("/sendmessage")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response posaljiPoruku(Poruka poruka, @Context HttpServletRequest request)
	{
		PorukaDAO poruke = (PorukaDAO) context.getAttribute("PorukaDAO");
		
		poruke.getPoruke().put(poruka.getIdPoruka(), poruka);
		
		context.setAttribute("PorukaDAO", poruke);
		
		return Response.ok().build();
	}
	
	
	@GET
	@Path("/message/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Poruka getJednuPoruku(@PathParam("naziv") String naziv, @Context HttpServletRequest request)
	{
		PorukaDAO poruke = (PorukaDAO) context.getAttribute("PorukaDAO");
		
		Poruka p = poruke.findID(naziv);
		p.setProcitana(true);

		return p;
		
	}
	
	
	
	
	@POST
	@Path("/deletemessage/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obrisiPoruku(@PathParam("naziv") String naziv, @Context HttpServletRequest request)
	{
		PorukaDAO poruke = (PorukaDAO) context.getAttribute("PorukaDAO");
		
		Poruka p = poruke.findID(naziv);
		
		p.setAktivna(false);
		
		return Response.ok().build();
	}
	
	
}
