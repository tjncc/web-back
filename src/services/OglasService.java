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
import dao.KategorijaDAO;
import dao.OglasDAO;
import dao.UserDAO;


@Path("")
public class OglasService {

	@Context
	ServletContext context;
	
	@PostConstruct 
	public void init() {
		
		if(context.getAttribute("OglasDAO") == null) {
			context.setAttribute("OglasDAO", new OglasDAO());
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
		if(oglasi.getOglasi().containsKey(o.getNaziv())) {
			return Response.status(400).build();
		}
		
		oglasi.getOglasi().put(o.getNaziv(), o);
		context.setAttribute("OglasDAO", oglasi);
		
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
	public Response likeArticle(@PathParam("naziv") String naziv, @Context HttpServletRequest request) {
		
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		
		Oglas oglas = oglasi.getOglasi().get(naziv);
		
		oglas.setBrLajkova(oglas.getBrLajkova()+1);
		context.setAttribute("OglasDAO", oglasi);
		
		return Response.ok().build();
		
	}
	
	
	@GET
	@Path("/article/dislike/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response dislikeArticle(@PathParam("naziv") String naziv, @Context HttpServletRequest request) {
		
		OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
		
		Oglas oglas = oglasi.getOglasi().get(naziv);
		
		oglas.setBrDislajkova(oglas.getBrDislajkova()+1);
		context.setAttribute("OglasDAO", oglasi);
		
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
		
		Oglas oglas = oglasi.getOglasi().get(naziv);		
		
		oglas.setStanje(Aktivan.OBRISAN);
		users.pretragaListi(naziv);
		
		context.setAttribute("OglasDAO", oglasi);
		context.setAttribute("UserDAO", users);
		
		return Response.ok().build();

	
	}
	

}
