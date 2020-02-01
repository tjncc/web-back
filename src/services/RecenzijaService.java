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
import beans.Poruka;
import dao.KategorijaDAO;
import dao.OglasDAO;
import dao.PorukaDAO;
import dao.RecenzijaDAO;
import dao.UserDAO;

@Path("")
public class RecenzijaService {

	@Context
	ServletContext context;
	
	@PostConstruct 
	public void init() {
		
		if(context.getAttribute("RecenzijaDAO") == null) {
			String contextPath = context.getRealPath("");
			context.setAttribute("RecenzijaDAO", new RecenzijaDAO(contextPath));
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
		PorukaDAO poruke = (PorukaDAO) context.getAttribute("PorukaDAO");
		
		Poruka p = new Poruka();
		
		if(!recenzija.getOglas().contentEquals("RECENZIJAPRODAVCA")) {
			Oglas o = oglasi.findID(recenzija.getOglas());
			o.getRecenzije().add(recenzija.getIdRec());
			recenzija.setProdavac(o.getProdavac());
			p.setNaziv(o.getNaziv());
			p.setSadrzaj("Kupac " + recenzija.getRecezent() + " je ostavio recenziju za gore naveden oglas.");
		} else {
			p.setSadrzaj("Kupac " + recenzija.getRecezent() + " je ostavio recenziju za vaš profil.");
		}
		
		
		p.setNaslov("Dodata recenzija");
		p.setPosiljalac("Automatska poruka");
		p.setPrimalac(recenzija.getProdavac());
		
		
		poruke.getPoruke().put(p.getIdPoruka(), p);	
		recenzije.getRecenzije().put(recenzija.getIdRec(), recenzija);
		
		context.setAttribute("PorukaDAO", poruke);
		context.setAttribute("OglasDAO", oglasi);
		context.setAttribute("RecenzijaDAO", recenzije);
		
		poruke.savePoruka(context.getRealPath(""), poruke);
		recenzije.saveRecenzija(context.getRealPath(""), recenzije);
		oglasi.saveOglas(context.getRealPath(""), oglasi);
		
		return Response.ok().build();		
	}
	
	@GET
	@Path("/reviewinfo/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Recenzija getJednuRecenziju(@PathParam("naziv") String naziv,@Context HttpServletRequest request)
	{
		RecenzijaDAO recenzije = (RecenzijaDAO) context.getAttribute("RecenzijaDAO");
		//System.out.println(naziv);
		Recenzija rec = recenzije.findID(naziv);
		//System.out.println(rec.getNaziv());
		return rec;			
	}
	
	
	@POST
	@Path("/review/delete/{naziv}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteArticle(@PathParam("naziv") String naziv, @Context HttpServletRequest request) {
	

		RecenzijaDAO recenzije = (RecenzijaDAO) context.getAttribute("RecenzijaDAO");

		Recenzija recenzija = recenzije.findID(naziv);

		recenzija.setAktivna(false);

		context.setAttribute("RecenzijaDAO", recenzije);
		
		recenzije.saveRecenzija(context.getRealPath(""), recenzije);
		
		return Response.ok().build();	
	}
	
	@POST
	@Path("/review/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editArticle(Recenzija recenzija, @Context HttpServletRequest request) {
		
		RecenzijaDAO recenzije = (RecenzijaDAO) context.getAttribute("RecenzijaDAO");
		PorukaDAO poruke = (PorukaDAO) context.getAttribute("PorukaDAO");
		
		Poruka p = new Poruka();
		
		recenzije.getRecenzije().put(recenzija.getIdRec(), recenzija);
		
		if(recenzija.getOglas().contentEquals("RECENZIJAPRODAVCA")){
			p.setSadrzaj("Kupac " + recenzija.getRecezent() + " je izmenio recenziju vašeg profila.");
		} else {
			p.setNaziv(recenzija.getOglas());
			p.setSadrzaj("Kupac " + recenzija.getRecezent() + " je izmenio recenziju navedenog oglasa.");
		}
		
		p.setNaslov("Izmenjena recenzija");
		p.setPosiljalac("Automatska poruka");
		p.setPrimalac(recenzija.getProdavac());
		
		
		poruke.getPoruke().put(p.getIdPoruka(), p);	
		recenzije.getRecenzije().put(recenzija.getIdRec(), recenzija);
		
		context.setAttribute("PorukaDAO", poruke);		
		context.setAttribute("RecenzijaDAO", recenzije);
		
		recenzije.saveRecenzija(context.getRealPath(""), recenzije);
		poruke.savePoruka(context.getRealPath(""), poruke);
		
		return Response.ok().build();
		
	}
	
}
