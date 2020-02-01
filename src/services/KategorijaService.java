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

import dao.KategorijaDAO;
import dao.OglasDAO;
import dao.UserDAO;
import beans.Kategorija;
import beans.Oglas;

@Path("")
public class KategorijaService {

		@Context
		ServletContext context;
		
		@PostConstruct 
		public void init() {
			
			if(context.getAttribute("KategorijaDAO") == null) {
				String contextPath = context.getRealPath("");
				context.setAttribute("KategorijaDAO", new KategorijaDAO(contextPath));
			}
		}
		
		@GET
		@Path("/categoryinfo")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Kategorija> getKategorije(@Context HttpServletRequest request)
		{
			KategorijaDAO kategorije = (KategorijaDAO) context.getAttribute("KategorijaDAO");
			//System.out.println(context.getRealPath(""));
			
			return kategorije.kategorijePrikaz();
		}
		
		@POST
		@Path("/addcategory")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response dodajKategoriju(Kategorija k, @Context HttpServletRequest request) {
			
			KategorijaDAO kategorije = (KategorijaDAO) context.getAttribute("KategorijaDAO");
			UserDAO users = (UserDAO) context.getAttribute("UserDAO");
			
			if(kategorije.getKategorije().containsKey(k.getNaziv())) {
				return Response.status(400).build();
			}
			
			kategorije.getKategorije().put(k.getNaziv(), k);
			
			context.setAttribute("KategorijaDAO", kategorije);
			
			kategorije.saveKategorija(context.getRealPath(""), kategorije);
			
			return Response.ok().build();
			
		}
		
		@GET
		@Path("/categoryinfo/{naziv}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Kategorija getJednaKat(@PathParam("naziv") String naziv,@Context HttpServletRequest request)	{
			
			KategorijaDAO kategorije = (KategorijaDAO) context.getAttribute("KategorijaDAO");
			Kategorija kat = kategorije.findByName(naziv);
			
			return kat;
		}
		
		@POST
		@Path("/category/delete/{naziv}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteArticle(@PathParam("naziv") String naziv, @Context HttpServletRequest request) {
			KategorijaDAO kategorije = (KategorijaDAO) context.getAttribute("KategorijaDAO");
			Kategorija kat = kategorije.findByName(naziv);
			
			if(kat == null) {
				return Response.status(400).build();
			}
			
			kat.setAktivna(false);
			
			context.setAttribute("KategorijaDAO", kategorije);
			kategorije.saveKategorija(context.getRealPath(""), kategorije);

			return Response.ok().build();
		}
		
		@POST
		@Path("/category/edit")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response editArticle(Kategorija k, @Context HttpServletRequest request) {
			
			KategorijaDAO kategorije = (KategorijaDAO) context.getAttribute("KategorijaDAO");
			
			kategorije.getKategorije().put(k.getNaziv(), k);
			
			context.setAttribute("KategorijaDAO", kategorije);
			kategorije.saveKategorija(context.getRealPath(""), kategorije);
			
			return Response.ok().build();
			
		}

		
		@POST
		@Path("/category/deletearticle/{naziv}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<Oglas> deleteArticleCategory(@PathParam("naziv") String naziv, Kategorija k, @Context HttpServletRequest request) {
			
			KategorijaDAO kategorije = (KategorijaDAO) context.getAttribute("KategorijaDAO");
			OglasDAO oglasi = (OglasDAO) context.getAttribute("OglasDAO");
			
			Kategorija kat = kategorije.findByName(k.getNaziv());
			Oglas o = oglasi.findID(naziv);
			
			kat.getOglasi().remove(o);
			o.getKategorije().remove(k.getNaziv());
			
			context.setAttribute("KategorijaDAO", kategorije);
			context.setAttribute("OglasDAO", oglasi);
			
			kategorije.saveKategorija(context.getRealPath(""), kategorije);
			oglasi.saveOglas(context.getRealPath(""), oglasi);
			
			return oglasi.oglasiPrikazKateg(kat);
			
		}
		
		
	
}

